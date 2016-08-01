'use strict';

var app = angular.module('projetoHobbyApp', ['ngRoute', 'ngAnimate', 'ngAria', 'ngMessages', 'ngMaterial', 'ngMdIcons', 'ImageCropper', 'ngFileUpload', 'projetoHobbyApp.directive', 'projetoHobbyApp.manga.services', 'projetoHobbyApp.manga.controllers']); 

	app.config(['$routeProvider', function($routeProvider) {
		var version = '?nocache=${project.version}';
		
		$routeProvider.when('/manga-list', {
			templateUrl: 'views/manga/manga-list.html',
			controller: 'MangaListCtrl'
		});
		$routeProvider.when('/manga-detail/:id', {
			templateUrl: 'views/manga/manga-detail.html',
			controller: 'MangaDetailCtrl'
		});
		$routeProvider.when('/manga/:id/:idTitulo', {
			templateUrl: 'views/manga/titulo/titulo-detail.html', 
			controller: 'TituloDetailCtrl'
		});
		$routeProvider.when('/manga/:id/:idTitulo/:idVolume', {
			templateUrl: 'views/manga/volume/volume-detail.html', 
			controller: 'VolumeDetailCtrl'
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
	  		link: projectUrl + '/manga-list',
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