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


app.controller('MangaDetailCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TitulosFactory', 'TituloCreateFactory', 'VolumesFactory', '$location',
	 function($scope, $rootScope, $routeParams, MangaFactory, TitulosFactory, TituloCreateFactory, VolumesFactory, $location) {
    
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulos = TitulosFactory.query({id: $routeParams.id});
	
	$scope.refresh = function(){
		$scope.titulos = TitulosFactory.query({id: $routeParams.id});
	}
	
	// callback for ng-click 'createNewTitulo':
    $scope.createNewTitulo = function () {
    	$scope.titulo.manga = $scope.manga.id;
    	TituloCreateFactory.create($scope.titulo);
    }
    
    $scope.$on('refresh', function () {
        $scope.refresh();
    });
}]);

app.controller('MangaCreateCtrl', ['$scope', 'MangasFactory', '$location',
    function ($scope, MangasFactory, $location) {

    // callback for ng-click 'createNewManga':
    $scope.createNewManga = function () {
        MangasFactory.create($scope.manga);
        $location.path('/manga-list');
    }
}]);

app.controller('TituloDetailCtrl', ['$scope', '$routeParams', 'MangaFactory', 'TituloFactory', 'VolumesFactory', 'CapitulosFactory', '$location',
  	 function($scope, $routeParams, MangaFactory, TituloFactory, VolumesFactory, CapitulosFactory, $location) {
      
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulo = TituloFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo});
      
	$scope.volumes = VolumesFactory.query({id: $routeParams.id , idTitulo: $routeParams.idTitulo});
     
	//$scope.capitulos = CapitulosFactory.query({id: $routeParams.id , idTitulo: $routeParams.idTitulo, idVolume: $routeParams.idVolume});
}]);