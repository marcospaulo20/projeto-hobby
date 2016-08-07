'use strict';

var app = angular.module('projetoHobbyApp.manga.controllers', []);

app.controller('MangaListCtrl', ['$scope', '$rootScope', 'MangasFactory', 'MangaFactory', '$mdToast', '$mdDialog', '$location', '$filter', 
  	 function($scope, $rootScope, MangasFactory, MangaFactory, $mdToast, $mdDialog, $location, $filter) {    
	
	$scope.mangas = MangasFactory.query();
	
	$scope.mangaDetail = mangaDetail;
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
				titulo: data.titulo
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
	
	function mangaDetail(element) {
		return $location.path("manga/" + element.id);
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
			var temp = undefined;//find($scope.view.dataTable, function(x) { return x.titulo === $scope.mangas.titulo; });
			if(temp === undefined) {
				MangasFactory.create($scope.view.selectedItem).$promise.then(function(data) {
					$scope.view.dataTable.push(data);
					$mdDialog.hide('O mangá adicionado com sucesso.');
				}, function() {
					$mdDialog.hide('O mangá já foi gravado.');
			  	});
			} else {
				$mdDialog.hide('O mangá já foi gravado ou ocorreu algum error interno.');
			}
		}
		
		// Permite modificar um registro
		function modificar() {
			var indexArr;
			$scope.view.dataTable.filter(function(elem, index, array){ if(elem.id == $scope.view.selectedItem.id) { indexArr = index; }});
			MangaFactory.update({id: $scope.view.selectedItem.id}, $scope.view.selectedItem).$promise.then(function(data) {				
				$scope.view.dataTable[indexArr].titulo = $scope.view.selectedItem.titulo;
				$mdDialog.hide('O mangá alterado com sucesso.');
			}, function() {
				$mdDialog.hide('Ocorreu algum error, ao alterar o mangá.');
		  	});
		}
	}
	
}]);

app.controller('MangaDetailCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TitulosFactory', 'TituloFactory','TituloCreateFactory', '$mdToast', '$mdDialog', '$location', '$filter',
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
				ano: data.ano,
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
	
	
	
	$scope.select = function (data) {
        $scope.titulo = TituloFactory.show({id: data.manga, idTitulo: data.id});
    }
	
	$scope.originalList = TitulosFactory.query({id: $routeParams.id});
	
	$scope.list = $scope.originalList;
	
	$scope.config = {
		itemsPerPage: 5,
		maxPages: 5,
		fillLastPage: true
	}
	
	$scope.updateFilteredList = function() {
	    $scope.list = $filter("filter")($scope.originalList, $scope.query);
	};
	
	$scope.add = function() {
		$scope.titulo.manga = $scope.manga.id;
    	TituloCreateFactory.create($scope.titulo).$promise.then(function(data) {    		
    		$scope.originalList.push({id: data.id, titulo: data.titulo, manga: data.manga});
    	    $scope.updateFilteredList();
    	}, function() {
    		console.log("error");
    	});
	}
    
}]);

app.controller('TituloDetailCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TituloFactory', 'VolumesFactory', 'VolumeCreateFactory', '$location', '$filter',
  	 function($scope, $rootScope, $routeParams, MangaFactory, TituloFactory, VolumesFactory, VolumeCreateFactory, $location, $filter) {
    
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
	
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulo = TituloFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo});
      
	$scope.originalList = VolumesFactory.query({id: $routeParams.id , idTitulo: $routeParams.idTitulo});
	
	$scope.list = $scope.originalList;
	
	$scope.config = {
		itemsPerPage: 5,
		maxPages: 5,
		fillLastPage: true
	}
	
	$scope.updateFilteredList = function() {
	    $scope.list = $filter("filter")($scope.originalList, $scope.query);
	};
	
	$scope.clear = function() {
		 $scope.imageCropStep = 1;
		 delete $scope.imgSrc;
		 delete $scope.result;
		 delete $scope.resultBlob;
	};
	
	$scope.add = function() {
		$scope.volume.titulo = $scope.titulo.id;
		$scope.volume.imagem = $scope.result.substr(22, $scope.result.length); 
    	VolumeCreateFactory.create($scope.volume).$promise.then(function(dataVolume) {
			$scope.originalList.push({id: dataVolume.id, titulo: dataVolume.titulo, imagem: dataVolume.imagem, nome: dataVolume.nome, status: dataVolume.status});
    	    $scope.updateFilteredList();
    	    $scope.clear();
    	}, function() {
    		console.log("error");
    	});
	}
	
}]);

app.controller('VolumeDetailCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TituloFactory', 'VolumeFactory', 'CapitulosFactory', 'CapituloCreateFactory', '$location', '$filter',
	 	 function($scope, $rootScope, $routeParams, MangaFactory, TituloFactory, VolumeFactory, CapitulosFactory, CapituloCreateFactory, $location, $filter) {
	     
	$scope.select = function(data) {
		$("#numero").val(data.numero);
		$("#nome").val(data.nome);
		$("#status").val(data.status);		
	}
	
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulo = TituloFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo});
	
	$scope.volume = VolumeFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo, idVolume: $routeParams.idVolume});
	
	$scope.originalList = CapitulosFactory.query({id: $routeParams.id, idTitulo: $routeParams.idTitulo, idVolume: $routeParams.idVolume});
	
	$scope.list = $scope.originalList;
	
	$scope.config = {
		itemsPerPage: 5,
		maxPages: 5,
		fillLastPage: true
	}

	$scope.updateFilteredList = function() {
	    $scope.list = $filter("filter")($scope.originalList, $scope.query);
	};
	
	$scope.add = function() {		
		$scope.capitulo.volume = $scope.volume.id;
    	CapituloCreateFactory.create($scope.capitulo).$promise.then(function(data) {
    		$scope.originalList.push({id: data.id, numero: data.numero, nome: data.nome, status: data.status});
    	    $scope.updateFilteredList();
    	}, function() {
    		console.log("error");
    	});
	}
}]);

function DialogController($scope, $mdDialog) {
	$scope.hide = function() {
		$mdDialog.hide();
	};
	$scope.cancel = function() {
		$mdDialog.cancel();		
	};
	$scope.answer = function(answer) {
		$mdDialog.hide(answer);
	};
};