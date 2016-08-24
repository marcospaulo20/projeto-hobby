'use strict';

var app = angular.module('projetoHobbyApp', ['ngRoute', 'ngAnimate', 'ngAria', 'ngMessages', 'ngMaterial', 'ngMdIcons', 'ImageCropper', 'ngFileUpload', 'projetoHobbyApp.directive', 
                                             'projetoHobbyApp.manga.services', 'projetoHobbyApp.manga.controllers', 'projetoHobbyApp.tituloM.controllers', 'projetoHobbyApp.volume.controllers', 'projetoHobbyApp.capitulo.controllers',
                                             'projetoHobbyApp.anime.services', 'projetoHobbyApp.anime.controllers', 'projetoHobbyApp.tituloA.controllers', 'projetoHobbyApp.arco.controllers', 'projetoHobbyApp.episodio.controllers',
                                             'projetoHobbyApp.comic.services', 'projetoHobbyApp.comic.controllers', 'projetoHobbyApp.tituloC.controllers', 'projetoHobbyApp.capituloC.controllers',
                                             'projetoHobbyApp.serie.services', 'projetoHobbyApp.serie.controllers', 'projetoHobbyApp.tituloS.controllers', 'projetoHobbyApp.temporada.controllers', 'projetoHobbyApp.episodioS.controllers']); 

	app.config(['$routeProvider', function($routeProvider) {
		var version = '?nocache=${project.version}';
		
		$routeProvider.when('/mangas', {
			templateUrl: 'views/manga/index.html',
			controller: 'MangaCtrl'
		});
		$routeProvider.when('/mangas/:id', {
			templateUrl: 'views/manga/titulo.html',
			controller: 'TituloMCtrl'
		});
		$routeProvider.when('/mangas/:id/:idTituloM', {
			templateUrl: 'views/manga/volume.html', 
			controller: 'VolumeCtrl'
		});
		$routeProvider.when('/mangas/:id/:idTituloM/:idVolume', {
			templateUrl: 'views/manga/capitulo.html', 
			controller: 'CapituloMCtrl'
		});
		
		$routeProvider.when('/animes', {
			templateUrl: 'views/anime/index.html',
			controller: 'AnimeCtrl'
		});
		$routeProvider.when('/animes/:id', {
			templateUrl: 'views/anime/titulo.html',
			controller: 'TituloACtrl'
		});
		$routeProvider.when('/animes/:id/:idTituloA', {
			templateUrl: 'views/anime/arco.html', 
			controller: 'ArcoCtrl'
		});
		$routeProvider.when('/animes/:id/:idTituloA/:idArco', {
			templateUrl: 'views/anime/episodio.html', 
			controller: 'EpisodioACtrl'
		});
		
		$routeProvider.when('/comics', {
			templateUrl: 'views/comic/index.html',
			controller: 'ComicCtrl'
		});
		$routeProvider.when('/comics/:id', {
			templateUrl: 'views/comic/titulo.html',
			controller: 'TituloCCtrl'
		});
		$routeProvider.when('/comics/:id/:idTituloC', {
			templateUrl: 'views/comic/capitulo.html',
			controller: 'CapituloCCtrl'
		});
		
		$routeProvider.when('/series', {
			templateUrl: 'views/serie/index.html',
			controller: 'SerieCtrl'
		});
		$routeProvider.when('/series/:id', {
			templateUrl: 'views/serie/titulo.html',
			controller: 'TituloSCtrl'
		});
		$routeProvider.when('/series/:id/:idTituloS', {
			templateUrl: 'views/serie/temporada.html', 
			controller: 'TemporadaCtrl'
		});
		$routeProvider.when('/series/:id/:idTituloS/:idTemporada', {
			templateUrl: 'views/serie/episodio.html', 
			controller: 'EpisodioSCtrl'
		});
	}]);
	
	app.controller('AppCtrl', ['$scope','$mdSidenav', '$location',
	    function($scope, $mdSidenav, $location) {
	  	
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
	    
	  	 $scope.selectHobby = function (page) {
	         var currentRoute = "/projeto-hobby/#" + $location.path().slice(0,7);
	         return page == currentRoute ? 'selected' : '';
	     };	     	    
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
			return moment(date).format('DD/MM/YYYY');
		};
		  
		$mdDateLocaleProvider.parseDate = function(dateString) {
		    var m = moment(dateString, 'DD/MM/YYYY', true);
		    return m.isValid() ? m.toDate() : new Date(NaN);
		};
	});