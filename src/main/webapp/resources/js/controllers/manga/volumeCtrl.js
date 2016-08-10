'use strict';

var app = angular.module('projetoHobbyApp.volume.controllers', []);

app.controller('VolumeCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TituloFactory', 'VolumesFactory', 'VolumeCreateFactory', 'VolumeFactory', '$mdToast', '$mdDialog', '$location', '$filter',
  	 function($scope, $rootScope, $routeParams, MangaFactory, TituloFactory, VolumesFactory, VolumeCreateFactory, VolumeFactory, $mdToast, $mdDialog, $location, $filter) {
    
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulo = TituloFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo});
	
	$scope.volumes = VolumesFactory.query({id: $routeParams.id , idTitulo: $routeParams.idTitulo});
	
	
	$scope.capituloPage = capituloPage;
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
  				nome: data.nome,
  				arco: data.arco,
  				paginas: data.paginas,
  				anoPublicacaoJP: new Date(data.anoPublicacaoJP),
  				anoPublicacaoBR: new Date(data.anoPublicacaoBR),
  				status: data.status,
  				preco: data.preco,
  				capitulos: data.capitulos,
  				imagem: data.imagem
  			};
  		}
  		$mdDialog.show({
  			templateUrl: 'editor.html',
  			targetEvent: event,
  			locals: {
  				selectedItem: tempData,
  				dataTable: $scope.volumes,
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
  	
  	function capituloPage(element) {
		return $location.path("manga/" + $scope.manga.id + "/" + $scope.titulo.id + "/" + element.id);
	}
  	
  	// Controller de dialog
  	function DialogController($scope, $mdDialog, operaction, selectedItem, dataTable) {
  		$scope.titulo = TituloFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo});
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
  		   { category: true, name: 'Já li' },
  		   { category: false, name: 'Estou lendo' }         
         ];
  		//$scope.listStatus = ('Já li,Estou lendo').split(',').map(function (status) { return { name: status }; });  		
  		
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
  			$scope.view.selectedItem.titulo = $scope.titulo.id;
  			$scope.view.selectedItem.imagem = $scope.result.substr(22, $scope.result.length);
  	    	VolumeCreateFactory.create($scope.view.selectedItem).$promise.then(function(data) {    		
  	    		$scope.view.dataTable.push(data);
  				$mdDialog.hide('O volume adicionado com sucesso.');
  	    	}, function() {
  	    		$mdDialog.hide('O volume já foi gravado ou ocorreu algum erro.');
  	    	});	    	
  		}
  		
  		// Permite modificar um registro
  		function modificar() {
  			var indexArr;
  			$scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
  			$scope.view.selectedItem.titulo = $scope.titulo.id;
  			if($scope.result != null)
  				$scope.view.selectedItem.imagem = $scope.result.substr(22, $scope.result.length);
  			VolumeFactory.update({id: $scope.titulo.manga, idTitulo: $scope.titulo.id, idVolume: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
  				$scope.view.dataTable[indexArr].nome = $scope.view.selectedItem.nome;
  				$scope.view.dataTable[indexArr].imagem = $scope.view.selectedItem.imagem;
  				$scope.view.dataTable[indexArr].arco = $scope.view.selectedItem.arco;
  				$scope.view.dataTable[indexArr].status = $scope.view.selectedItem.status;
  				$scope.view.dataTable[indexArr].capitulos = $scope.view.selectedItem.capitulos;
  				$scope.view.dataTable[indexArr].capitulos.status = $scope.view.selectedItem.capitulos.status;
  				$mdDialog.hide('O volume alterado com sucesso.');
  			}, function() {
  				$mdDialog.hide('Ocorreu algum error, ao alterar o volume.');
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