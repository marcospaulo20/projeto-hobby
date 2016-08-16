'use strict';

var app = angular.module('projetoHobbyApp', ['ngRoute', 'ngAnimate', 'ngAria', 'ngMessages', 'ngMaterial', 'ngMdIcons', 'ImageCropper', 'ngFileUpload', 'projetoHobbyApp.directive', 
                                             'projetoHobbyApp.manga.services', 'projetoHobbyApp.manga.controllers', 'projetoHobbyApp.tituloM.controllers', 'projetoHobbyApp.volume.controllers', 'projetoHobbyApp.capitulo.controllers',
                                             'projetoHobbyApp.anime.services', 'projetoHobbyApp.anime.controllers', 'projetoHobbyApp.tituloA.controllers', 'projetoHobbyApp.arco.controllers', 'projetoHobbyApp.episodio.controllers']); 

	app.config(['$routeProvider', function($routeProvider) {
		var version = '?nocache=${project.version}';
		
		$routeProvider.when('/mangas', {
			templateUrl: 'views/manga/index.html',
			controller: 'MangaCtrl'
		});
		$routeProvider.when('/manga/:id', {
			templateUrl: 'views/manga/titulo.html',
			controller: 'TituloMCtrl'
		});
		$routeProvider.when('/manga/:id/:idTituloM', {
			templateUrl: 'views/manga/volume.html', 
			controller: 'VolumeCtrl'
		});
		$routeProvider.when('/manga/:id/:idTituloM/:idVolume', {
			templateUrl: 'views/manga/capitulo.html', 
			controller: 'CapituloMCtrl'
		});
		
		$routeProvider.when('/animes', {
			templateUrl: 'views/anime/index.html',
			controller: 'AnimeCtrl'
		});
		$routeProvider.when('/anime/:id', {
			templateUrl: 'views/anime/titulo.html',
			controller: 'TituloACtrl'
		});
		$routeProvider.when('/anime/:id/:idTituloA', {
			templateUrl: 'views/anime/arco.html', 
			controller: 'ArcoCtrl'
		});
		$routeProvider.when('/anime/:id/:idTituloA/:idArco', {
			templateUrl: 'views/anime/episodio.html', 
			controller: 'EpisodioACtrl'
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
	  	  },
	  	  {
		  	link: projectUrl + '/animes',
		  	title: 'Animes',
		  	icon: 'img/anime-icon.png'	  		
		  },
		  {
		  	link: projectUrl + '/series',
		  	title: 'Series',
		  	icon: 'img/serie-icon.png'	  		
		  },
		  {
		  	link: projectUrl + '/comics',
		  	title: 'Comics',
		  	icon: 'img/comic-icon.png'	  		
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
	
	app.config(function($mdDateLocaleProvider) {
		$mdDateLocaleProvider.formatDate = function(date) {
			return date ? moment(date).format('DD-MM-YYYY') : '';
		};
		  
		$mdDateLocaleProvider.parseDate = function(dateString) {
		    var m = moment(dateString, 'DD-MM-YYYY', true);
		    return m.isValid() ? m.toDate() : new Date(NaN);
		};
	});