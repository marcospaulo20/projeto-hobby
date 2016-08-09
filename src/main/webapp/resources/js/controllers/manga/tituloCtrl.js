'use strict';

var app = angular.module('projetoHobbyApp.titulo.controllers', []);

app.controller('TituloCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TitulosFactory', 'TituloFactory','TituloCreateFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	function($scope, $rootScope, $routeParams, MangaFactory, TitulosFactory, TituloFactory, TituloCreateFactory, $mdToast, $mdDialog, $location, $filter) {
	
  	$scope.manga = MangaFactory.show({id: $routeParams.id});
  	$scope.titulos = TitulosFactory.query({id: $routeParams.id});
  	
  	$scope.volumePage = volumePage;
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
  				autor: data.autor,
  				desenhista: data.desenhista,
  				status: data.status,
  				ano: data.ano,  				
  				categorias: data.categorias,
  				volumes: data.volumes,
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
  	
  	function volumePage(element) {
		return $location.path("manga/" + element.manga + "/" + element.id);
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
  		
  		// Categorias
  		$scope.listCategorias = [
         { category: 'catg', name: 'Komodo' },
         { category: 'catg', name: 'Shonen' },
         { category: 'catg', name: 'Shoujo' },
         { category: 'catg', name: 'Seinen' },
         { category: 'catg', name: 'Josei' },
         { category: 'genr', name: 'Ação' },
         { category: 'genr', name: 'Adulto' },
         { category: 'genr', name: 'Artes Marciais' },
         { category: 'genr', name: 'Aventura' },
         { category: 'genr', name: 'Comédia' },
         { category: 'genr', name: 'Demônios' },
         { category: 'genr', name: 'Drama' },
         { category: 'genr', name: 'Ecchi' },
         { category: 'genr', name: 'Escolar' },
         { category: 'genr', name: 'Espaço' },
         { category: 'genr', name: 'Esportes' },
         { category: 'genr', name: 'Fantasia' },
         { category: 'genr', name: 'Ficção' },
         { category: 'genr', name: 'Harem' },
         { category: 'genr', name: 'Histórico' },
         { category: 'genr', name: 'Horror' },
         { category: 'genr', name: 'Jogo' },
         { category: 'genr', name: 'Mistério' },
         { category: 'genr', name: 'Paródia' },
         { category: 'genr', name: 'Policial' },
         { category: 'genr', name: 'Psicológico' },
         { category: 'genr', name: 'Romance' },
         { category: 'genr', name: 'Samurai' },
         { category: 'genr', name: 'Sobrenatural' },
         { category: 'genr', name: 'Slice of Life' },
         { category: 'genr', name: 'Thriler' },
         { category: 'genr', name: 'Vampiros' }
        ];  		
  		
  		// Status
  		$scope.status = '';
  		$scope.listStatus = [
  		 {category: 'ATV', name: 'Ativo' },
  		 {category: 'COM', name: 'Completo' },
  		 {category: 'PAR', name: 'Parado' }
  		];
  		
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
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].categorias = $scope.view.selectedItem.categorias;
  				$mdDialog.hide('O titulo alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o titulo.');
  		  	});
  		}  		  		  	
  	}  	 
}]);