'use strict';

var app = angular.module('projetoHobbyApp.tituloM.controllers', []);

app.controller('TituloMCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TitulosMFactory', 'TituloMFactory','TituloMCreateFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	function($scope, $rootScope, $routeParams, MangaFactory, TitulosMFactory, TituloMFactory, TituloMCreateFactory, $mdToast, $mdDialog, $location, $filter) {
	
  	$scope.titulos = TitulosMFactory.query({id: $routeParams.id});
  	  	
  	$scope.mostrarDialog = mostrarDialog;
  
  	$scope.next = function(element) {
		return $location.path("mangas/" + element.manga + "/" + element.id);
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
  	
  	$scope.roundedPercentage = function(myValue, totalValue){
   	   var result = ((myValue/totalValue)*100)
   	   return Math.round(result, 2);
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
  				manga: data.manga,
  				nome: data.nome,
  				classificacao: data.classificacao,
  				editora: data.editora,
  				escritor: data.escritor,
  				arte: data.arte,
  				pubOriginal: new Date(data.pubOriginal),
  				status: data.status,
  				categorias: data.categorias,
  				volumes: data.volumes
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
  		
  		// Classificacao
  		$scope.classificacao = '';
  		$scope.listClassificacao = [
  		 {category: 'LIVRE', name: 'Livre' },
  		 {category: 'S_10', name: '10 anos' },
  		 {category: 'S_12', name: '12 anos' },
  		 {category: 'S_14', name: '14 anos' },
  		 {category: 'S_16', name: '16 anos' },
  		 {category: 'S_18', name: '18 anos' }
  		];
  		
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
         { category: 'genr', name: 'Colegial' },
         { category: 'genr', name: 'Comédia' },
         { category: 'genr', name: 'Crossover' },
         { category: 'genr', name: 'Demônios' },
         { category: 'genr', name: 'Drama (Tragédia)' },
         { category: 'genr', name: 'Ecchi' },
         { category: 'genr', name: 'Escolar' },
         { category: 'genr', name: 'Espaço' },
         { category: 'genr', name: 'Esporte' },
         { category: 'genr', name: 'Família' },
         { category: 'genr', name: 'Fantasia' },
         { category: 'genr', name: 'Ficção' },
         { category: 'genr', name: 'Ficção Científica' },
         { category: 'genr', name: 'Harem' },
         { category: 'genr', name: 'Histórico' },
         { category: 'genr', name: 'Jogo' },
         { category: 'genr', name: 'Luta' },
         { category: 'genr', name: 'Magia' },
         { category: 'genr', name: 'Mecha' },
         { category: 'genr', name: 'Mistério' },
         { category: 'genr', name: 'Mistismo' },
         { category: 'genr', name: 'Musical (Songfic)' },
         { category: 'genr', name: 'Paródia' },
         { category: 'genr', name: 'Policial' },
         { category: 'genr', name: 'Psicológico' },
         { category: 'genr', name: 'Romance' },
         { category: 'genr', name: 'Samurai' },
         { category: 'genr', name: 'Sci-Fi' },
         { category: 'genr', name: 'Sobrenatural' },
         { category: 'genr', name: 'Slice of Life' },
         { category: 'genr', name: 'Thriler' },
         { category: 'genr', name: 'Terror e Horror' },
         { category: 'genr', name: 'Violência' },
         { category: 'genr', name: 'Vampiros' }
        ];  		
  		
  		// Status
  		$scope.status = '';
  		$scope.listStatus = [
  		 {category: 'ATV', name: 'Ativo' },
  		 {category: 'COM', name: 'Completo' },
  		 {category: 'PAR', name: 'Parado' }
  		];
  		
  		 $scope.pubOriginal = new Date();
  		
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
  	    	TituloMCreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {    		
  	    		$scope.view.dataTable.push(data);
  				$mdDialog.hide('O titulo adicionado com sucesso.');
  	    	}, function() {
  	    		$mdDialog.hide('O titulo já foi gravado ou ocorreu algum erro.');
  	    	});	    	
  		}
  		
  		// Permite modificar um registro
  		function modificar() {
  			var indexArr;
  			$scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
  			//$scope.view.selectedItem.manga = $scope.manga.id;
  			TituloMFactory.update({id: $scope.manga.id, idTitulo: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].classificacao = $scope.view.selectedItem.classificacao;
  				$scope.view.dataTable[indexArr].editora = $scope.view.selectedItem.editora;
  				$scope.view.dataTable[indexArr].escritor = $scope.view.selectedItem.escritor;
  				$scope.view.dataTable[indexArr].arte = $scope.view.selectedItem.arte;
  				$scope.view.dataTable[indexArr].pubOriginal = $scope.view.selectedItem.pubOriginal;
  				$scope.view.dataTable[indexArr].status = $scope.view.selectedItem.status;
  				$scope.view.dataTable[indexArr].categorias = $scope.view.selectedItem.categorias;
  				$mdDialog.hide('O titulo alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o titulo.');
  		  	});
  		}
  	}  	 
}]);