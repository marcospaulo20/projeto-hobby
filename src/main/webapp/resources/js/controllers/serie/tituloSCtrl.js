'use strict';

var app = angular.module('projetoHobbyApp.tituloS.controllers', []);

app.controller('TituloSCtrl', ['$scope', '$rootScope', '$routeParams', 'SerieFactory', 'TitulosSFactory', 'TituloSFactory','TituloSCreateFactory', 'ComicsFactory', '$mdToast', '$mdDialog', '$location', '$filter', '$timeout',
  	function($scope, $rootScope, $routeParams, SerieFactory, TitulosSFactory, TituloSFactory, TituloSCreateFactory, ComicsFactory, $mdToast, $mdDialog, $location, $filter, $timeout) {
	
	
  	$scope.serie = SerieFactory.show({id: $routeParams.id});
  	$scope.titulos = TitulosSFactory.query({id: $routeParams.id});
  	
  	$scope.temporadaPage = temporadaPage;
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
  				serie: data.serie,
  				nome: data.nome,
  				classificacao: data.classificacao,
  				emissora: data.emissora,
  				anoOriginal: new Date(data.anoOriginal),
  				paisOrigem: data.paisOrigem,
  				status: data.status,
  				generos: data.generos,
  				comic: data.comic,
  				temporadas: data.temporadas
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
  	
  	function temporadaPage(element) {
		return $location.path("series/" + element.serie + "/" + element.id);
	}
  	
  	// Controller de dialog
  	function DialogController($scope, $mdDialog, operaction, selectedItem, dataTable) {
  		$scope.serie = SerieFactory.show({id: $routeParams.id});
  		
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
  		  {name: 'The CW'}
  		];  		  	
  		
  		// Status
  		$scope.status = '';
  		$scope.listStatus = [
  		 {category: 'CAN', name: 'Cancelada' },
  		 {category: 'EEX', name: 'Em Exibição' },
  		 {category: 'FIN', name: 'Finalizada' }
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
  		
  		// Generos
  		$scope.listGeneros = [         
         { name: 'Ação' },
         { name: 'Animação' },
         { name: 'Artes Marciais' },
         { name: 'Aventura' },
         { name: 'Comédia' },
         { name: 'Criminal' },
         { name: 'Demônios' },
         { name: 'Desenho' },
         { name: 'Drama' },
         { name: 'Infantil' },
         { name: 'Investigação' },
         { name: 'Escolar' },
         { name: 'Espaço' },
         { name: 'Esportes' },
         { name: 'Fantasia' },
         { name: 'Ficção Científica' },
         { name: 'Faroeste' },
         { name: 'Histórico' },
         { name: 'Horror' },
         { name: 'Jogo' },
         { name: 'Mistério' },
         { name: 'Mitologia' },
         { name: 'Paródia' },
         { name: 'Policial' },
         { name: 'Psicológico' },
         { name: 'Romance' },
         { name: 'Samurai' },
         { name: 'Sobrenatural' },
         { name: 'Super-Heróis' },
         { name: 'Suspense' },
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
  			$scope.view.selectedItem.serie = $scope.serie.id;
  	    	TituloSCreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {    		
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

  			TituloSFactory.update({id: $scope.serie.id, idTituloS: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].emissora = $scope.view.selectedItem.emissora;
  				$scope.view.dataTable[indexArr].anoOriginal = $scope.view.selectedItem.anoOriginal;
  				$scope.view.dataTable[indexArr].generos = $scope.view.selectedItem.generos;
  				$mdDialog.hide('O titulo alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o titulo.');
  		  	});
  		}
  		
  		$scope.comic = null;
  		$scope.comics = null;
  		
  		$scope.loadComics = function() {
  			// Use timeout to simulate a 650ms request.
  			return $timeout(function() {
  				$scope.comics = ComicsFactory.query();
  			}, 650);
  		};  		  	
  	}  	 
}]);