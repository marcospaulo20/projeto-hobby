'use strict';

var app = angular.module('projetoHobbyApp.episodio.controllers', []);

app.controller('EpisodioACtrl', ['$scope', '$rootScope', '$routeParams', 'AnimeFactory', 'TituloAFactory', 'ArcoFactory', 'EpisodiosAFactory', 'EpisodioACreateFactory', 'EpisodioAFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	 function($scope, $rootScope, $routeParams, AnimeFactory, TituloAFactory, ArcoFactory, EpisodiosAFactory, EpisodioACreateFactory, EpisodioAFactory, $mdToast, $mdDialog, $location, $filter) {
    
	$scope.anime = AnimeFactory.show({id: $routeParams.id});
	
	$scope.titulo = TituloAFactory.show({id: $routeParams.id, idTituloA: $routeParams.idTituloA});
	
	$scope.arco = ArcoFactory.show({id: $routeParams.id, idTituloA: $routeParams.idTituloA, idArco: $routeParams.idArco});
	
	$scope.episodios = EpisodiosAFactory.query({id: $routeParams.id, idTituloA: $routeParams.idTituloA, idArco: $routeParams.idArco});	
	
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
  				arco: data.arco,
  				nome: data.nome,
  				numero: data.numero,
  				tipo: data.tipo,
  				status: data.status,
  				statusVirtual: data.statusVirtual
  			};
  		}
  		$mdDialog.show({
  			templateUrl: 'editor.html',
  			targetEvent: event,
  			locals: {
  				selectedItem: tempData,
  				dataTable: $scope.episodios,
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
  		
  		$scope.titulo = TituloAFactory.show({id: $routeParams.id, idTituloA: $routeParams.idTituloA});
  		$scope.arco = ArcoFactory.show({id: $routeParams.id, idTituloA: $routeParams.idTituloA, idArco: $routeParams.idArco});
  		
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
  		
  		// Tipo
  		$scope.tipo = '';
  		$scope.listTipos = [
  		  {name: 'Normal'},
  		  {name: 'Especial'}  		 
  		];
  		
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
  			$scope.view.selectedItem.arco = $scope.arco.id;
  			EpisodioACreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {
  	    		$scope.view.dataTable.push(data);
  				$mdDialog.hide('O episodio adicionado com sucesso.');
  	    	}, function() {
  	    		$mdDialog.hide('O episodio já foi gravado ou ocorreu algum erro.');
  	    	});	    	
  		}
  		
  		// Permite modificar um registro
  		function modificar() {
  			var indexArr;
  			$scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
  			//$scope.view.selectedItem.arco = $scope.arco.id;
  			EpisodioAFactory.update({id: $scope.titulo.anime, idTituloA: $scope.titulo.id, idArco: $scope.arco.id, idEpisodioA: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].numero = $scope.view.selectedItem.numero;
  				$scope.view.dataTable[indexArr].status = $scope.view.selectedItem.status;
  				$scope.view.dataTable[indexArr].statusVirtual = $scope.view.selectedItem.statusVirtual;
  				$mdDialog.hide('O episodio alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o episodio.');
  		  	});
  		}
  		
  		// Permite excluir um registro
  		function excluir() {
  			var indexArr;
  			var item = $scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
  			if(item !== undefined) {
  				//$scope.view.selectedItem.arco = $scope.arco.id;
  	  			EpisodioAFactory.delete({id: $scope.titulo.anime, idTituloA: $scope.titulo.id, idArco: $scope.arco.id, idEpisodioA: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  	  				$scope.view.dataTable.pop(item);
  	  				$mdDialog.hide('');
  	  			}, function() {
  	  				$mdDialog.hide('Ocorreu algum error, ao deleta o episodio.');
  	  		  	});
  			}  			
  		}
  		
  		$scope.showExcluir = function(ev) {
  			// Appending dialog to document.body to cover sidenav in docs app
  			var confirm = $mdDialog.confirm()
  				.title('Gostaria de eliminar o episodio?')
  	  	        .targetEvent(ev)
  	  	        .ok('Sim')
  	  	        .cancel('Não');
  	  	    	$mdDialog.show(confirm).then(function() {
  	  	    		excluir();
  	  	    		$scope.status = 'O episodio deletado com sucesso.';
  	  	    	}, function() {
  	  	    		$scope.status = 'Ocorreu algum error, ao deleta o episodio.';
  	  	    	});
  	  	  	}
  		}  	 

}]);
