'use strict';

var app = angular.module('projetoHobbyApp.capituloC.controllers', []);

app.controller('CapituloCCtrl', ['$scope', '$rootScope', '$routeParams', 'ComicFactory', 'TituloCFactory', 'CapitulosCFactory', 'CapituloCCreateFactory', 'CapituloCFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	 function($scope, $rootScope, $routeParams, ComicFactory, TituloCFactory, CapitulosCFactory, CapituloCCreateFactory, CapituloCFactory, $mdToast, $mdDialog, $location, $filter) {
    
	$scope.titulo = TituloCFactory.show({id: $routeParams.id, idTituloC: $routeParams.idTituloC});
	
	$scope.flag = true;
	
	$scope.$on('$viewContentLoaded', function() {
		CapitulosCFactory.query({id: $routeParams.id, idTituloC: $routeParams.idTituloC}).$promise.then(function(data) {
			$scope.capitulos = data;
			$scope.flag = false;
		});
	});
	
  	$scope.mostrarDialog = mostrarDialog;
  	
  	$scope.comeBack = function() {
  		return $location.path("comics/" + $scope.titulo.comic);
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
  				tituloC: data.tituloC,
  				nome: data.nome,
  				numero: data.numero,
  				escritor: data.escritor,
  				arte: data.arte,
  				colorista: data.colorista,
  				editor: data.editor,
  				ano: new Date(data.ano),
  				status: data.status,
  				statusVirtual: data.statusVirtual,
  				primeiroCapitulo: data.primeiroCapitulo,
  				imagem: data.imagem
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
  		
  		$scope.titulo = TituloCFactory.show({id: $routeParams.id, idTituloC: $routeParams.idTituloC});
  		
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
  			$scope.view.selectedItem.tituloC = $scope.titulo.id;
  			if($scope.result != null)
  				$scope.view.selectedItem.imagem = $scope.result.substr(22, $scope.result.length);
  			CapituloCCreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {
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
  			if($scope.result != null)
  				$scope.view.selectedItem.imagem = $scope.result.substr(22, $scope.result.length);
  			CapituloCFactory.update({id: $scope.titulo.comic, idTituloC: $scope.titulo.id, idCapituloC: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].numero = $scope.view.selectedItem.numero;
  				$scope.view.dataTable[indexArr].escritor = $scope.view.selectedItem.escritor;
  				$scope.view.dataTable[indexArr].arte = $scope.view.selectedItem.arte;
  				$scope.view.dataTable[indexArr].colorista = $scope.view.selectedItem.colorista;
  				$scope.view.dataTable[indexArr].editor = $scope.view.selectedItem.editor;
  				$scope.view.dataTable[indexArr].ano = $scope.view.selectedItem.ano;
  				$scope.view.dataTable[indexArr].status = $scope.view.selectedItem.status;
  				$scope.view.dataTable[indexArr].statusVirtual = $scope.view.selectedItem.statusVirtual;
  				$scope.view.dataTable[indexArr].imagem = $scope.view.selectedItem.imagem;
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
  	  			CapituloCFactory.delete({id: $scope.titulo.comic, idTituloC: $scope.titulo.id, idCapituloC: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  	  				$scope.view.dataTable.pop(item);
  	  			}, function() {
  	  				$mdDialog.hide('Ocorreu algum error, ao deleta o capitulo.');
  	  		  	});
  			}  			
  		}
  		
  		$scope.showExcluir = function(ev) {
  			// Appending dialog to document.body to cover sidenav in docs app
  			var confirm = $mdDialog.confirm()
  				.title('Gostaria de eliminar o capitulo?')
  	  	        .targetEvent(ev)
  	  	        .ok('Sim')
  	  	        .cancel('Não');
  	  	    $mdDialog.show(confirm).then(function() {
  	  	    	excluir();
  	  	    	$scope.status = 'O capitulo deletado com sucesso.';
  	  	    }, function() {
  	  	    	$scope.status = 'Ocorreu algum error, ao deleta o capitulo.';
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
