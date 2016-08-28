'use strict';

var app = angular.module('projetoHobbyApp.manga.controllers', []);
app.controller('MangaCtrl', ['$scope', '$rootScope', 'MangasFactory', 'MangaFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	 function($scope, $rootScope, MangasFactory, MangaFactory, $mdToast, $mdDialog, $location, $filter) {
	
	$scope.flag = true;
	
	$scope.$on('$viewContentLoaded', function() {
		MangasFactory.query().$promise.then(function(data) {
			$scope.mangas = data;
			$scope.flag = false;
		});
	});
	
	$scope.mostrarDialog = mostrarDialog;	
	
	$scope.next = function(element) {
		return $location.path("mangas/" + element.id);
	}
	
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
				titulosM: data.titulosM
			};
		}
		$mdDialog.show({
			templateUrl: 'editor.html',
			targetEvent: event,
			locals: {
				selectedItem: tempData,
				dataTable: $scope.mangas,
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
			// Determinado existe um elemento titulo especifico
			MangasFactory.create($scope.view.selectedItem).$promise.then(function(data) {
				$scope.view.dataTable.push(data);
				$mdDialog.hide('O mangá adicionado com sucesso.');
			}, function() {
				$mdDialog.hide('O mangá já foi gravado ou ocorreu algum error interno.');
		  	});
		}
		
		// Permite modificar um registro
		function modificar() {
			var indexArr;
			$scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
			MangaFactory.update({id: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
				$mdDialog.hide('O mangá alterado com sucesso.');
			}, function() {
				$mdDialog.hide('Ocorreu algum error, ao alterar o mangá.');
		  	});
		}
	}
	
}]);