'use strict';

var app = angular.module('projetoHobbyApp.manga.controllers', []);

app.controller('MangaListCtrl', ['$scope', '$rootScope', 'MangasPageFactory', 'MangasFactory', '$location', '$filter',
  	 function($scope, $rootScope, MangasPageFactory, MangasFactory, $location, $filter) {    
	
	$scope.originalList =  MangasFactory.query();
	
	$scope.list = $scope.originalList;
	
	$scope.config = {
		itemsPerPage: 5,
		maxPages: 5,
		fillLastPage: true
	}
	
	$scope.updateFilteredList = function() {
	    $scope.list = $filter("filter")($scope.originalList, $scope.query);
	};
}]);


app.controller('MangaDetailCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TitulosFactory', 'TituloCreateFactory', '$location', '$filter',
	 function($scope, $rootScope, $routeParams, MangaFactory, TitulosFactory, TituloCreateFactory, $location, $filter) {
    
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
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

app.controller('MangaCreateCtrl', ['$scope', 'MangasFactory', '$location',
    function ($scope, MangasFactory, $location) {
    $scope.createNewManga = function () {
        MangasFactory.create($scope.manga);
        $location.path('/manga-list');
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
	
	$scope.add = function() {		
		$scope.volume.titulo = $scope.titulo.id;
		$scope.volume.imagem = $scope.result.substr(22, $scope.result.length); 
    	VolumeCreateFactory.create($scope.volume).$promise.then(function(data) {    		
    		$scope.originalList.push({id: data.id, titulo: data.titulo, imagem: data.imagem, nome: data.nome, status: data.status});
    	    $scope.updateFilteredList();
    	}, function() {
    		console.log("error");
    	});
	}
	
	//$scope.capitulos = CapitulosFactory.query({id: $routeParams.id , idTitulo: $routeParams.idTitulo, idVolume: $routeParams.idVolume});
}]);

app.controller('VolumeDetailCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TituloFactory', 'VolumeFactory', '$location', '$filter',
	 	 function($scope, $rootScope, $routeParams, MangaFactory, TituloFactory, VolumeFactory, $location, $filter) {
	     
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulo = TituloFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo});
	
	$scope.volume = VolumeFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo, idVolume: $routeParams.idVolume});	
}]);