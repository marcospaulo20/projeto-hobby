'use strict';

var app = angular.module('projetoHobbyApp.manga.controllers', []);

app.controller('MangaListCtrl', ['$scope', 'MangasFactory', '$location', function($scope, MangasFactory, $location) {
	
	//$scope.mangas = MangasFactory.query();
}]);

app.controller('MangaPageListCtrl', ['$scope', '$rootScope', 'MangasPageFactory', '$location',
  	 function($scope, $rootScope, MangasPageFactory, $location) {    
	
	$scope.filterOptions = { filterText: ''};
	
	// Initialize required information: sorting, the first page to show and the grid options.
    $scope.mangas = {currentPage: 1};
 
    $scope.gridOptions = {
        data: 'mangas.list',
        useExternalSorting: true,
        filterOptions: $scope.filterOptions,
        
        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'titulo', displayName: 'Titulo' },
            { field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-edit edit" ng-click="editRow(row)"></span>' }
        ],
 
        multiSelect: false,
        selectedItems: [],
        // Broadcasts an event when a row is selected, to signal the form that it needs to load the row data.
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('personSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };
 
    // Refresh the grid, calling the appropriate rest method.
    $scope.refreshGrid = function () {
        var listMangasArgs = {
            page: $scope.mangas.currentPage
        };
 
        MangasPageFactory.get(listMangasArgs, function (data) {
            $scope.mangas = data;
        })
    };
    
    // Broadcast an event when an element in the grid is deleted. No real deletion is perfomed at this point.
    $scope.editRow = function (row) {
        $location.path('/manga-detail/' + row.entity.id);
    };
 
    // Watch the sortInfo variable. If changes are detected than we need to refresh the grid.
    // This also works for the first page access, since we assign the initial sorting in the initialize section.
    $scope.$watch('sortInfo.fields[0]', function () {
        $scope.refreshGrid();
    }, true);
 
    // Picks the event broadcasted when a person is saved or deleted to refresh the grid elements with the most
    // updated information.
    $scope.$on('refreshGrid', function () {
        $scope.refreshGrid();
    });
 
    // Picks the event broadcasted when the form is cleared to also clear the grid selection.
    $scope.$on('clear', function () {
        $scope.gridOptions.selectAll(false);
    });
}]);


app.controller('MangaDetailCtrl', ['$scope', '$rootScope', '$routeParams', 'MangaFactory', 'TitulosFactory', 'TituloCreateFactory', 'VolumesFactory', '$location',
	 function($scope, $rootScope, $routeParams, MangaFactory, TitulosFactory, TituloCreateFactory, VolumesFactory, $location) {    

	// Clears the form. Either by clicking the 'Clear' button in the form, or when a successfull save is performed.
    $scope.clearForm = function () {
        $scope.titulo = null;
        
        $scope.tituloForm.$setPristine();
        // Broadcast the event to also clear the grid selection.
        $rootScope.$broadcast('clear');
    };
	
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulos = TitulosFactory.query({id: $routeParams.id});
	
	// callback for ng-click 'createNewTitulo':
    $scope.createNewTitulo = function () {
    	$scope.titulo.manga = $scope.manga.id;
    	TituloCreateFactory.create($scope.titulo);    	
    }
 
}]);

app.controller('MangaCreateCtrl', ['$scope', 'MangasFactory', '$location',
    function ($scope, MangasFactory, $location) {

    // callback for ng-click 'createNewManga':
    $scope.createNewManga = function () {
        MangasFactory.create($scope.manga);
        $location.path('/manga-list');
    }
}]);

app.controller('TituloDetailCtrl', ['$scope', '$routeParams', 'MangaFactory', 'TituloFactory', 'VolumesFactory', '$location',
  	 function($scope, $routeParams, MangaFactory, TituloFactory, VolumesFactory, $location) {
      
	$scope.manga = MangaFactory.show({id: $routeParams.id});
	
	$scope.titulo = TituloFactory.show({id: $routeParams.id, idTitulo: $routeParams.idTitulo});
      
     $scope.volumes = VolumesFactory.query({id: $routeParams.id , idTitulo: $routeParams.idTitulo});
}]);