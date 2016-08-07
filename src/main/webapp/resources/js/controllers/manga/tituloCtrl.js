'use strict';

var app = angular.module('projetoHobbyApp.titulo.controllers', []);

app.controller('TituloCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TitulosFactory', 'TituloFactory','TituloCreateFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	function($scope, $rootScope, $routeParams, MangaFactory, TitulosFactory, TituloFactory, TituloCreateFactory, $mdToast, $mdDialog, $location, $filter) {
	
  	$scope.manga = MangaFactory.show({id: $routeParams.id});
  	$scope.titulos = TitulosFactory.query({id: $routeParams.id});
  	
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
  				titulo: data.titulo,
  				autor: data.autor,
  				desenhista: data.desenhista,
  				status: data.status,
  				termo: data.termo,
  				ano: new Date(data.ano),
  				categorias: data.categorias,
  				imagem: data.imagem
  			};
  		}
  		$mdDialog.show({
  			templateUrl: 'editor.html',
  			targetEvent: event,
  			locals: {
  				selectedItem: tempData,
  				dataTable: $scope.titulos,
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
  		$scope.manga = MangaFactory.show({id: $routeParams.id});
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
  		
  		$scope.view.selectedItem.ano = new Date();
  		
  		// Categorias		
  		$scope.listCategorias = [{name: 'Kodomo'}, {name: 'Shonen'}, {name: 'Shoujo'}, {name: 'Seinen'}, {name: 'Josei'}];
  		
  		// Status
  		$scope.status = '';
  		$scope.listStatus = ('Ativo Completo Parado').split(' ').map(function (status) { return { name: status }; });
  		// Termos
  		$scope.termo = '';
  		$scope.termos = ('Gaiden').split(' ').map(function (termo) { return { name: termo}; });
  		
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
  			$scope.view.selectedItem.manga = $scope.manga.id;
  			$scope.view.selectedItem.imagem = $scope.result.substr(22, $scope.result.length); 
  	    	TituloCreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {    		
  	    		$scope.view.dataTable.push(data);
  				$mdDialog.hide('O titulo adicionado com sucesso.');
  	    	}, function() {
  	    		$mdDialog.hide('O titulo já foi gravado.');
  	    	});	    	
  		}
  		
  		// Permite modificar um registro
  		function modificar() {
  			var indexArr;
  			$scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
  			$scope.view.selectedItem.manga = $scope.manga.id;
  			TituloFactory.update({id: $scope.manga.id, idTitulo: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].titulo = $scope.view.selectedItem.titulo;
  				$mdDialog.hide('O titulo alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o titulo.');
  		  	});
  		}
  		
  		$scope.$watch('files', function () {
  	        var files = $scope.files;		

  			var fileReader = new FileReader();
  			if(files != null)
  				fileReader.readAsDataURL(files);		
  		
  			fileReader.onload = function(e) {
  				$scope.imgSrc = this.result;
  				$scope.$apply();
  			};
  	    });
  		
  		$scope.clear = function() {
  			 $scope.imageCropStep = 1;
  			 delete $scope.imgSrc;
  			 delete $scope.result;
  			 delete $scope.resultBlob;
  		};
  	}  	 
}]);
