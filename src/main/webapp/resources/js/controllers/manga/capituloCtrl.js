'use strict';

var app = angular.module('projetoHobbyApp.capitulo.controllers', []);

app.controller('CapituloCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TituloFactory', 'VolumeFactory', 'CapitulosFactory', 'CapituloCreateFactory', 'CapituloFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	 function($scope, $rootScope, $routeParams, MangaFactory, TituloFactory, VolumeFactory, CapitulosFactory, CapituloCreateFactory, CapituloFactory, $mdToast, $mdDialog, $location, $filter) {
    
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulo = TituloFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo});
	
	$scope.volume = VolumeFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo, idVolume: $routeParams.idVolume});
	
	$scope.capitulos = CapitulosFactory.query({id: $routeParams.id, idTitulo: $routeParams.idTitulo, idVolume: $routeParams.idVolume});	
	
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
  				nome: data.nome,
  				numero: data.numero,
  				anoPublicacaoJP: data.anoPublicacaoJP
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
  		
  		$scope.titulo = TituloFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo});
  		$scope.volume = VolumeFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo, idVolume: $routeParams.idVolume});
  		
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
  			CapituloCreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {
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
  			$scope.view.selectedItem.volume = $scope.volume.id;
  			CapituloFactory.update({id: $scope.titulo.manga, idTitulo: $scope.titulo.id, idVolume: $scope.volume.id, idCapitulo: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
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
  				$scope.view.selectedItem.volume = $scope.volume.id;
  	  			CapituloFactory.delete({id: $scope.titulo.manga, idTitulo: $scope.titulo.id, idVolume: $scope.volume.id, idCapitulo: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  	  				$scope.view.dataTable.pop(item);
  	  				$mdDialog.hide('O capitulo deletado com sucesso.');
  	  			}, function() {
  	  				$mdDialog.hide('Ocorreu algum error, ao deleta o capitulo.');
  	  		  	});
  			}  			
  		}
  	}  	 

}]);
