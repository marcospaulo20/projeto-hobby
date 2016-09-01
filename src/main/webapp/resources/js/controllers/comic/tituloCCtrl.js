'use strict';

var app = angular.module('projetoHobbyApp.tituloC.controllers', []);

app.controller('TituloCCtrl', ['$scope', '$rootScope', '$routeParams', 'ComicFactory', 'TitulosCFactory', 'TituloCFactory','TituloCCreateFactory', '$mdToast', '$mdDialog', '$location', '$filter', '$timeout',
  	function($scope, $rootScope, $routeParams, ComicFactory, TitulosCFactory, TituloCFactory, TituloCCreateFactory, $mdToast, $mdDialog, $location, $filter, $timeout) {
	
	$scope.flag = true;
	
	$scope.$on('$viewContentLoaded', function() {
		TitulosCFactory.query({id: $routeParams.id}).$promise.then(function(data) {
			$scope.titulos = data;
			$scope.flag = false;
		});
	});
	
  	$scope.mostrarDialog = mostrarDialog;
  	
  	$scope.next = function(element) {
		return $location.path("comics/" + element.comic + "/" + element.id);
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
  		if(stringDate == '30/12/1969' || stringDate == '1970-01-01') {s
  			return null;
  		} else {
  			var dateOut = new Date(stringDate);
  			dateOut.setDate(dateOut.getDate() + 1);
  			return dateOut;
  		}
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
  				comic: data.comic,
  				nome: data.nome,
  				classificacao: data.classificacao,
  				editora: data.editora,
  				paisOrigem: data.paisOrigem,
  				pubOriginal: convertToDate(data.pubOriginal),  		
  				status: data.status,
  				generos: data.generos,
  				capitulosC: data.capitulosC
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
  		$scope.comic = ComicFactory.show({id: $routeParams.id});
  		
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
  		
  		// Paises
  		$scope.paises = '';
  		$scope.listPaises = [
         {category: 'AUS', name: 'Austrália' },
  		 {category: 'BEL', name: 'Bélgica' },
  		 {category: 'BOL', name: 'Bolívia' },
  		 {category: 'BRA', name: 'Brasil' },
  		 {category: 'CAN', name: 'Canadá' },
  		 {category: 'DEN', name: 'Dinamarca' },
  		 {category: 'ESP', name: 'Espanha' },
  		 {category: 'FRA', name: 'França' },
  		 {category: 'GBR', name: 'Reino Unido' },
  		 {category: 'GER', name: 'Alemanha' },
  		 {category: 'IND', name: 'Índia' },
  		 {category: 'ITA', name: 'Itália' },
  		 {category: 'JPN', name: 'Japão' },
  		 {category: 'MEX', name: 'México' },
  		 {category: 'POR', name: 'Portugal' },
  		 {category: 'RUS', name: 'Rússia' },
  		 {category: 'SRB', name: 'Sérvia' },
  		 {category: 'SUI', name: 'Suíça' },
  		 {category: 'SWE', name: 'Suécia' },
  		 {category: 'USA', name: 'Estados Unidos' }
  		]; 		
  		
  		// Status
  		$scope.status = '';
  		$scope.listStatus = [
  		 {category: 'ATV', name: 'Ativo' },
  		 {category: 'COM', name: 'Completo' },
  		 {category: 'PAR', name: 'Parado' }
  		];
  		
  		// Generos
  		$scope.listGeneros = [         
         { name: 'Ação' },
         { name: 'Adulto' },
         { name: 'Artes Marciais' },
         { name: 'Aventura' },
         { name: 'Comédia' },
         { name: 'Demônios' },
         { name: 'Drama' },
         { name: 'Escolar' },
         { name: 'Espaço' },
         { name: 'Esportes' },
         { name: 'Fantasia' },
         { name: 'Ficção' },
         { name: 'Histórico' },
         { name: 'Horror' },
         { name: 'Jogo' },
         { name: 'Luta' },
         { name: 'Mistério' },
         { name: 'Paródia' },
         { name: 'Policial' },
         { name: 'Psicológico' },
         { name: 'Romance' },
         { name: 'Samurai' },
         { name: 'Sobrenatural' },
         { name: 'Super Herois' },
         { name: 'Thriler' },
         { name: 'Terror' },
         { name: 'Vampiros' }
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
  			$scope.view.selectedItem.comic = $scope.comic.id;
  	    	TituloCCreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {    		
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

  			TituloCFactory.update({id: $scope.comic.id, idTitulo: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].classificacao = $scope.view.selectedItem.classificacao;
  				$scope.view.dataTable[indexArr].editora = $scope.view.selectedItem.editora;
  				$scope.view.dataTable[indexArr].paisOrigem = $scope.view.selectedItem.paisOrigem;
  				$scope.view.dataTable[indexArr].pubOriginal = $scope.view.selectedItem.pubOriginal;  		
  				$scope.view.dataTable[indexArr].status = $scope.view.selectedItem.status;
  				$scope.view.dataTable[indexArr].generos = $scope.view.selectedItem.generos;
  				$mdDialog.hide('O titulo alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o titulo.');
  		  	});
  		}
  	}
  	
  	$scope.countComplete = function(element) {
    	var cnt = 0;
    	angular.forEach(element, function(item) {
    		cnt += item.statusVirtual ? 1 : 0;
    	});
    	return cnt;
    }
  	
}]);