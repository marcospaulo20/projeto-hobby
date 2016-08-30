'use strict';

var app = angular.module('projetoHobbyApp.temporada.controllers', []);

app.controller('TemporadaCtrl', ['$scope', '$rootScope', '$routeParams', 'SerieFactory', 'TituloSFactory', 'TemporadasFactory', 'TemporadaCreateFactory', 'TemporadaFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	 function($scope, $rootScope, $routeParams, SerieFactory, TituloSFactory, TemporadasFactory, TemporadaCreateFactory, TemporadaFactory, $mdToast, $mdDialog, $location, $filter) {
    
	$scope.titulo = TituloSFactory.show({id: $routeParams.id, idTituloS: $routeParams.idTituloS});
	
	$scope.flag = true;
	
	$scope.$on('$viewContentLoaded', function() {
		TemporadasFactory.query({id: $routeParams.id , idTituloS: $routeParams.idTituloS}).$promise.then(function(data) {
			$scope.temporadas = data;
			$scope.flag = false;
		});
	});
	
	$scope.mostrarDialog = mostrarDialog;
	
	$scope.comeBack = function(){
  		return $location.path("series/" + $scope.titulo.serie);
  	}
  	
  	$scope.next = function(element) {
		return $location.path("series/" + $scope.titulo.serie+ "/" + element.tituloS + "/" + element.id);
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
				tituloS: data.tituloS,
  				nome: data.nome,
  				status: data.status,
  				ano: convertToDate(data.ano),
  				imagem: data.imagem,
  				episodiosS: data.episodiosS
  			};
  		}
  		$mdDialog.show({
  			templateUrl: 'editor.html',
  			targetEvent: event,
  			locals: {
  				selectedItem: tempData,
  				dataTable: $scope.temporadas,
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
  		$scope.titulo = TituloSFactory.show({id: $routeParams.id, idTituloS: $routeParams.idTituloS});
  		
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
  		
  		// Status
  		$scope.status = '';
  		$scope.listStatus = [
  		   { category: true, name: 'Vi esta temporada' },
  		   { category: false, name: 'Não vi esta temporada' }         
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
  			$scope.view.selectedItem.tituloS = $scope.titulo.id;
  			$scope.view.selectedItem.imagem = $scope.result.substr(22, $scope.result.length);
  	    	TemporadaCreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {    		
  	    		$scope.view.dataTable.push(data);
  				$mdDialog.hide('O temporada adicionado com sucesso.');
  	    	}, function() {
  	    		$mdDialog.hide('O temporada já foi gravado ou ocorreu algum erro.');
  	    	});	    	
  		}
  		
  		// Permite modificar um registro
  		function modificar() {
  			var indexArr;
  			$scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
  			//$scope.view.selectedItem.tituloA = $scope.titulo.id;
  			if($scope.result != null)
  				$scope.view.selectedItem.imagem = $scope.result.substr(22, $scope.result.length);
  			TemporadaFactory.update({id: $scope.titulo.serie, idTituloS: $scope.titulo.id, idTemporada: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].imagem = $scope.view.selectedItem.imagem;
  				$scope.view.dataTable[indexArr].ano = $scope.view.selectedItem.ano;
  				$scope.view.dataTable[indexArr].episodios = $scope.view.selectedItem.episodios;  				
  				$mdDialog.hide('O arco alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o arco.');
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
  	
  	$scope.numComplete = countComplete();

    $scope.$watch("temporadas", function(newValue, oldValue) {
    	$scope.temporadas = newValue;
    	$scope.numComplete = countComplete();
    }, true);

    function countComplete() {
    	var cnt = 0;
    	angular.forEach($scope.temporadas, function(item) {
    		cnt += item.episodiosS.length;
    	});
    	return cnt;
    }
}]);