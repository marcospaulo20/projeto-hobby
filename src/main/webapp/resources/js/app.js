'use strict';

var app = angular.module('projetoHobbyApp', ['ngRoute', 'ngAnimate', 'ngAria', 'ngMessages', 'ngMaterial', 'ngMdIcons', 'ImageCropper', 'ngFileUpload', 'projetoHobbyApp.directive', 
                                             'projetoHobbyApp.manga.services', 'projetoHobbyApp.manga.controllers', 'projetoHobbyApp.titulo.controllers', 'projetoHobbyApp.volume.controllers', 'projetoHobbyApp.capitulo.controllers']); 

	app.config(['$routeProvider', function($routeProvider) {
		var version = '?nocache=${project.version}';
		
		$routeProvider.when('/mangas', {
			templateUrl: 'views/manga/index.html',
			controller: 'MangaCtrl'
		});
		$routeProvider.when('/manga/:id', {
			templateUrl: 'views/manga/titulo.html',
			controller: 'TituloCtrl'
		});
		$routeProvider.when('/manga/:id/:idTitulo', {
			templateUrl: 'views/manga/volume.html', 
			controller: 'VolumeCtrl'
		});
		$routeProvider.when('/manga/:id/:idTitulo/:idVolume', {
			templateUrl: 'views/manga/capitulo.html', 
			controller: 'CapituloCtrl'
		});
	}]);
	
	app.controller('AppCtrl', ['$scope','$mdSidenav', 
	    function($scope, $mdSidenav) {
	  	
		$scope.selected = null;
		$scope.selectHobby = selectHobby;
		
		var projectUrl = "/projeto-hobby/#";
		
	  	$scope.toggleSidenav = function(name) {
	  	    $mdSidenav(name).toggle();
	  	};
	  	
	  	$scope.hobbys = [
	  	  {
	  		link: projectUrl + '/mangas',
	  		title: 'Mangás',
	  		icon: 'img/manga-icon.png'	  		
	  	  }	  	  
	  	];
	  	
	  	function selectHobby(hobby) {
	  		$scope.selected = angular.isNumber(hobby) ? $scope.hobbys[hobby] : hobby;
	  		$scope.toggleSidenav('left');
	  	}
	}]);
	
	app.config(function($mdThemingProvider) {
		var customBlueMap = $mdThemingProvider.extendPalette('light-blue', {
	      'contrastDefaultColor': 'light',
	      'contrastDarkColors': ['50'],
	      '50': 'ffffff'
	    });
	    
	    $mdThemingProvider.definePalette('customBlue', customBlueMap);
	    
	    $mdThemingProvider.theme('default').primaryPalette('customBlue', {
	        'default': '500',
	        'hue-1': '50'
	    }).accentPalette('pink');
	    
	    $mdThemingProvider.theme('input', 'default').primaryPalette('grey')
	});