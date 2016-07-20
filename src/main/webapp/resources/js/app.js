'use strict';

var app = angular.module('projetoHobbyApp', ['ngRoute', 'ngAnimate', 'angular-table', 'ngMdIcons', 'ImageCropper', 'ngFileUpload', 'ui.bootstrap', 'projetoHobbyApp.directive', 'projetoHobbyApp.manga.services', 'projetoHobbyApp.manga.controllers']); 

	app.config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/', {templateUrl: 'views/show.html', controller: 'ShowCtrl'});
		$routeProvider.when('/manga-list', {
			templateUrl: 'views/manga/manga-list.html',
			controller: 'MangaListCtrl'
		});
		$routeProvider.when('/manga-detail/:id', {
			templateUrl: 'views/manga/manga-detail.html',
			controller: 'MangaDetailCtrl'
		});
		$routeProvider.when('/manga-create', {
				templateUrl: 'views/manga/manga-create.html', 
				controller: 'MangaCreateCtrl'
		});
		$routeProvider.when('/manga/:id/:idTitulo', {
			templateUrl: 'views/manga/titulo/titulo-detail.html', 
			controller: 'TituloDetailCtrl'
		});
		$routeProvider.when('/manga/:id/:idTitulo/:idVolume', {
			templateUrl: 'views/manga/volume/volume-detail.html', 
			controller: 'VolumeDetailCtrl'
		});
		$routeProvider.otherwise({redirectTo: '/'});
	}]);	