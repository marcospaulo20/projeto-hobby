'use strict';

var app = angular.module('projetoHobbyApp.capitulo.controllers', []);

app.controller('CapituloMCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TituloMFactory', 'VolumeFactory', 'CapitulosMFactory', 'CapituloMCreateFactory', 'CapituloMFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	 function($scope, $rootScope, $routeParams, MangaFactory, TituloMFactory, VolumeFactory, CapitulosMFactory, CapituloMCreateFactory, CapituloMFactory, $mdToast, $mdDialog, $location, $filter) {
    
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulo = TituloMFactory.show({id: $routeParams.id, idTituloM: $routeParams.idTituloM});
	
	$scope.volume = VolumeFactory.show({id: $routeParams.id, idTituloM: $routeParams.idTituloM, idVolume: $routeParams.idVolume});
	
	$scope.capitulos = CapitulosMFactory.query({id: $routeParams.id, idTituloM: $routeParams.idTituloM, idVolume: $routeParams.idVolume});	
	
  	$scope.mostrarDialog = mostrarDialog;
  	
  	function simpleToastBase(message, position, delay, action) {
  	    $mdToast.show(
  	        $mdToast.simple()
  	            .content(message)
  	            .position(position)
  	            .hideDelay(delay)
  	            .action(action)
  	    );
  	}
  	
  	function mostrarError(mensage) {
  		simpleToastBase(mensage, 'bottom right', 6000, 'X');
     }
  	
  	// Mostrar um dialogo
  	function mostrarDialog(operaction, data, event) {
  		// Guarda os dados a enviar
  		var tempData = undefined;
  		if(data === undefined) {
  			tempData = {};
  		} else {
  			tempData = {
  				id: data.id,
  				volume: data.volume,
  				nome: data.nome,
  				numero: data.numero,
  				status: data.status,
  				statusVirtual: data.statusVirtual
  			};
  		}
  		$mdDialog.show({
  			templateUrl: 'editor.html',
  			targetEvent: event,
  			locals: {
  				selectedItem: tempData,
  				dataTable: $scope.capitulos,
  				operaction: operaction
  			},
  			bindToController: true,
  			controller: DialogController,
  			parent: angular.element(document.body)
  		})
  		.then(function(result) {
  			mostrarError(result);
  		});
  	}
  	
  	// Controller de dialog
  	function DialogController($scope, $mdDialog, operaction, selectedItem, dataTable) {
  		
  		$scope.titulo = TituloMFactory.show({id: $routeParams.id, idTituloM: $routeParams.idTituloM});
  		$scope.volume = VolumeFactory.show({id: $routeParams.id, idTituloM: $routeParams.idTituloM, idVolume: $routeParams.idVolume});
  		
  		$scope.view = {
  			dataTable: dataTable,
  			selectedItem: selectedItem,
  			operaction: 'Adicionar'
  		};
  		
  		// Determinado tipo de operação
  		switch(operaction) {
  			case 'C': 
  				$scope.view.operaction = 'Adicionar';
  				break;
  			case 'UD':
  				$scope.view.operaction = 'Modificar';
  				break;
  			case 'R':
  				$scope.view.operaction = 'Detalhes';
  				break;
  			default:
  				$scope.view.operaction = 'Detalhes';
  				break;
  		}
  		
  		$scope.status = false;
  		$scope.statusVirtual = false;
  		
  		// Metodos do controller de dialog
  		$scope.retorno = retorno;  
  		$scope.salvar = salvar;
  		$scope.excluir = excluir;
  		
  		// Retorna a janela principal sem realizar nenhuma ação
  		function retorno() {
  			$mdDialog.cancel();
  		}
  		
  		// Seleciona a opção de adicionar um novo item ou modificar um existente
  		function salvar() {
  			if($scope.view.selectedItem.id === undefined) adicionar();
  			else modificar();
  		}
  		
  		// Permite adicionar um novo elemento
  		function adicionar() {
  			$scope.view.selectedItem.volume = $scope.volume.id;
  			CapituloMCreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {
  	    		$scope.view.dataTable.push(data);
  				$mdDialog.hide('O capitulo adicionado com sucesso.');
  	    	}, function() {
  	    		$mdDialog.hide('O capitulo já foi gravado ou ocorreu algum erro.');
  	    	});	    	
  		}
  		
  		// Permite modificar um registro
  		function modificar() {
  			var indexArr;
  			$scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
  			//$scope.view.selectedItem.volume = $scope.volume.id;
  			CapituloMFactory.update({id: $scope.titulo.manga, idTituloM: $scope.titulo.id, idVolume: $scope.volume.id, idCapituloM: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].numero = $scope.view.selectedItem.numero;
  				$scope.view.dataTable[indexArr].status = $scope.view.selectedItem.status;
  				$scope.view.dataTable[indexArr].statusVirtual = $scope.view.selectedItem.statusVirtual;
  				$mdDialog.hide('O capitulo alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o capitulo.');
  		  	});
  		}
  		
  		// Permite excluir um registro
  		function excluir() {
  			var indexArr;
  			var item = $scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
  			if(item !== undefined) {
  				//$scope.view.selectedItem.volume = $scope.volume.id;
  	  			CapituloMFactory.delete({id: $scope.titulo.manga, idTituloM: $scope.titulo.id, idVolume: $scope.volume.id, idCapituloM: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  	  				$scope.view.dataTable.pop(item);
  	  				$mdDialog.hide('');
  	  			}, function() {
  	  				$mdDialog.hide('Ocorreu algum error, ao deleta o capitulo.');
  	  		  	});
  			}  			
  		}
  		
  		$scope.showExcluir = function(ev) {
  			// Appending dialog to document.body to cover sidenav in docs app
  			var confirm = $mdDialog.confirm()
  				.title('Gostaria de eliminar o capitulo?')
  	  	        .targetEvent(ev)
  	  	        .ok('Sim')
  	  	        .cancel('Não');
  	  	    	$mdDialog.show(confirm).then(function() {
  	  	    		excluir();
  	  	    		$scope.status = 'O capitulo deletado com sucesso.';
  	  	    	}, function() {
  	  	    		$scope.status = 'Ocorreu algum error, ao deleta o capitulo.';
  	  	    	});
  	  	  	}
  		}  	 
}]);
