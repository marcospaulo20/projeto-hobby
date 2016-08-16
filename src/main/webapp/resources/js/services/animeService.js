'use strict';

var services = angular.module('projetoHobbyApp.anime.services', ['ngResource']); 

services.factory('AnimesFactory', function AnimesFactory($resource) {
	return $resource('rest/animes', {}, {
		query: { method: 'GET', isArray: true },
		create: { method: 'POST' }
	})
});

services.factory('AnimeFactory', function AnimeFactory($resource) {
	return $resource('rest/animes/:id', {}, {
		show: { method: 'GET' },
		update: { method: 'PUT', params: {id: '@id'} },
		delete: { method: 'DELETE', params: {id: '@id'} }
	})
});

services.factory('TitulosAFactory', function TitulosAFactory($resource) {
	return $resource('rest/anime/:id/titulos', {}, {
		query: { method: 'GET', isArray: true }
	})
});

services.factory('TituloACreateFactory', function TitulosACreateFactory($resource) {
	return $resource('rest/anime/titulos', {}, {
		create: { method: 'POST' }
	})
});

services.factory('TituloAFactory', function TituloAFactory($resource) {
	return $resource('rest/anime/:id/titulos/:idTituloA', {}, {
		show: { method: 'GET', params: {id: '@anime.id', idTituloA: '@id'}  },
		update: { method: 'PUT', params: {id: '@anime.id', idTituloA: '@id'} },
		delete: { method: 'DELETE', params: {id: '@anime.id', idTituloA: '@id'} }
	})
});

services.factory('ArcosFactory', function ArcosFactory($resource) {
	return $resource('rest/anime/:id/titulo/:idTituloA/arcos', {}, {
		query: { method: 'GET', params: {id: '@anime.id', idTituloA: '@id'}, isArray: true }
	})
});

services.factory('ArcoCreateFactory', function ArcoFactory($resource) {
	return $resource('rest/anime/titulo/arcos', {}, {
		create: { method: 'POST' }
	})
});

services.factory('ArcoFactory', function ArcoFactory($resource) {
	return $resource('rest/anime/:id/titulo/:idTituloA/arcos/:idArco', {}, {
		show: { method: 'GET', params: {id: '@anime.id', idTituloA: '@titulo.id', idArco: '@id'} },
		update: { method: 'PUT', params: {id: '@anime.id', idTituloA: '@tituo.id', idArco: '@id'} },
		delete: { method: 'DELETE', params: {id: '@anime.id', idTituloA: '@tituo.id', idVolume: '@id'} },
	})
});

services.factory('EpisodiosAFactory', function EpisodiosAFactory($resource) {
	return $resource('rest/anime/:id/titulo/:idTituloA/arco/:idArco/episodios', {}, {
		query: { method: 'GET', params: {id: '@anime.id', idTituloA: '@titulo.id', idArco: '@id'}, isArray: true }
	})
});

services.factory('EpisodioACreateFactory', function EpisodioAFactory($resource) {
	return $resource('rest/anime/titulo/arco/episodios', {}, {
		create: { method: 'POST' }
	})
});

services.factory('EpisodioAFactory', function EpisodioAFactory($resource) {
	return $resource('rest/anime/:id/titulo/:idTituloA/arco/:idArco/episodios/:idEpisodioA', {}, {
		show: { method: 'GET', params: {id: '@anime.id', idTituloA: '@titulo.id', idArco: '@arco.id', idEpisodioA: '@id'} },
		update: { method: 'PUT', params: {id: '@anime.id', idTituloA: '@titulo.id', idArco: '@arco.id', idEpisodioA: '@id'} },
		delete: { method: 'DELETE', params: {id: '@manga.id', idTituloA: '@titulo.id', idArco: '@arco.id', idEpisodioA: '@id'} },
	})
});