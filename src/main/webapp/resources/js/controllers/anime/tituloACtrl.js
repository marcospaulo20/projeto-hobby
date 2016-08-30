'use strict';

var app = angular.module('projetoHobbyApp.tituloA.controllers', []);

app.controller('TituloACtrl', ['$scope', '$rootScope', '$routeParams', 'AnimeFactory', 'TitulosAFactory', 'TituloAFactory','TituloACreateFactory', 'MangasFactory', '$mdToast', '$mdDialog', '$location', '$filter', '$timeout',
  	function($scope, $rootScope, $routeParams, AnimeFactory, TitulosAFactory, TituloAFactory, TituloACreateFactory, MangasFactory, $mdToast, $mdDialog, $location, $filter, $timeout) {
	
  	$scope.titulos = TitulosAFactory.query({id: $routeParams.id});
  	  	
  	$scope.mostrarDialog = mostrarDialog;
  	
  	$scope.next = function(element) {
		return $location.path("animes/" + element.anime + "/" + element.id);
	}
  	
  	$scope.baseado = function(element) {
		return $location.path("mangas/" + element.manga.id);
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
  	
  	function convertToDate(stringDate){
  		var dateOut = new Date(stringDate);
  		dateOut.setDate(dateOut.getDate() + 1);
  		return dateOut;
  	};
  	
  	// Mostrar um dialogo
  	function mostrarDialog(operaction, data, event) {
  		// Guarda os dados a enviar
  		var tempData = undefined;
  		if(data === undefined) {
  			tempData = {};
  		} else {
  			tempData = {
  				id: data.id,							
  				anime: data.anime,
  				nome: data.nome,
  				classificacao: data.classificacao,
  				emissora: data.emissora,
  				anoOriginal: convertToDate(data.anoOriginal),
  				formato: data.formato,
  				generos: data.generos,
  				manga: data.manga,
  				arcos: data.arcos
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
  		$scope.anime = AnimeFactory.show({id: $routeParams.id});
  		
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
  		
  		// Emissora
  		$scope.emissora = '';
  		$scope.listEmissora = [
  		  {name: 'Fuji TV'},
  		  {name: 'Nippon Television'},
  		  {name: 'NHK'},
  		  {name: 'TV Tokyo'},
  		  {name: 'TBS'},
  		  {name: 'Vap'}
  		];  		
  		
  		// Formato
  		$scope.formato = '';
  		$scope.listFormato = [
  		  {name: 'Normal'},
  		  {name: 'OVA'},
 		  {name: 'Filme'}
  		];
  		
  		// Generos
  		$scope.listGeneros = [         
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
  			$scope.view.selectedItem.anime = $scope.anime.id;
  	    	TituloACreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {    		
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

  			TituloAFactory.update({id: $scope.anime.id, idTitulo: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].emissora = $scope.view.selectedItem.emissora;
  				$scope.view.dataTable[indexArr].formato = $scope.view.selectedItem.formato;
  				$scope.view.dataTable[indexArr].generos = $scope.view.selectedItem.generos;
  				$mdDialog.hide('O titulo alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o titulo.');
  		  	});
  		}
  		
  		$scope.manga = null;
  		$scope.mangas = null;
  		
  		$scope.loadMangas = function() {
  			// Use timeout to simulate a 650ms request.
  			return $timeout(function() {
  				$scope.mangas = MangasFactory.query();
  			}, 650);
  		};
  		
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
  
  	$scope.countComplete = function(element) {
    	var cnt = 0;
    	angular.forEach(element, function(item) {
    		cnt += item.statusVirtual ? 1 : 0;
    	});
    	return cnt;
    }
}]);