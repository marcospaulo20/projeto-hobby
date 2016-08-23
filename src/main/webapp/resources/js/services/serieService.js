'use strict';

var services = angular.module('projetoHobbyApp.serie.services', ['ngResource']); 

services.factory('SeriesFactory', function SeriesFactory($resource) {
	return $resource('rest/series', {}, {
		query: { method: 'GET', isArray: true },
		create: { method: 'POST' }
	})
});

services.factory('SerieFactory', function SerieFactory($resource) {
	return $resource('rest/series/:id', {}, {
		show: { method: 'GET' },
		update: { method: 'PUT', params: {id: '@id'} },
		delete: { method: 'DELETE', params: {id: '@id'} }
	})
});

services.factory('TitulosSFactory', function TitulosSFactory($resource) {
	return $resource('rest/serie/:id/titulos', {}, {
		query: { method: 'GET', isArray: true }
	})
});

services.factory('TituloSCreateFactory', function TitulosSCreateFactory($resource) {
	return $resource('rest/serie/titulos', {}, {
		create: { method: 'POST' }
	})
});

services.factory('TituloSFactory', function TituloSFactory($resource) {
	return $resource('rest/serie/:id/titulos/:idTituloS', {}, {
		show: { method: 'GET', params: {id: '@serie.id', idTituloA: '@id'}  },
		update: { method: 'PUT', params: {id: '@serie.id', idTituloA: '@id'} },
		delete: { method: 'DELETE', params: {id: '@serie.id', idTituloA: '@id'} }
	})
});

services.factory('TemporadasFactory', function TemporadasFactory($resource) {
	return $resource('rest/serie/:id/titulo/:idTituloS/temporadas', {}, {
		query: { method: 'GET', params: {id: '@serie.id', idTituloS: '@id'}, isArray: true }
	})
});

services.factory('TemporadaCreateFactory', function TemporadaFactory($resource) {
	return $resource('rest/serie/titulo/temporadas', {}, {
		create: { method: 'POST' }
	})
});

services.factory('TemporadaFactory', function TemporadaFactory($resource) {
	return $resource('rest/serie/:id/titulo/:idTituloS/temporadas/:idTemporada', {}, {
		show: { method: 'GET', params: {id: '@serie.id', idTituloS: '@titulo.id', idTemporada: '@id'} },
		update: { method: 'PUT', params: {id: '@serie.id', idTituloS: '@tituo.id', idTemporada: '@id'} },
		delete: { method: 'DELETE', params: {id: '@serie.id', idTituloS: '@tituo.id', idTemporada: '@id'} },
	})
});

services.factory('EpisodiosSFactory', function EpisodiosSFactory($resource) {
	return $resource('rest/serie/:id/titulo/:idTituloS/temporada/:idTemporada/episodios', {}, {
		query: { method: 'GET', params: {id: '@serie.id', idTituloS: '@titulo.id', idTemporada: '@id'}, isArray: true }
	})
});

services.factory('EpisodioSCreateFactory', function EpisodioSFactory($resource) {
	return $resource('rest/serie/titulo/temporada/episodios', {}, {
		create: { method: 'POST' }
	})
});

services.factory('EpisodioSFactory', function EpisodioSFactory($resource) {
	return $resource('rest/serie/:id/titulo/:idTituloS/temporada/:idTemporada/episodios/:idEpisodioS', {}, {
		show: { method: 'GET', params: {id: '@serie.id', idTituloS: '@titulo.id', idTemporada: '@temporada.id', idEpisodioS: '@id'} },
		update: { method: 'PUT', params: {id: '@serie.id', idTituloS: '@titulo.id', idTemporada: '@temporada.id', idEpisodioS: '@id'} },
		delete: { method: 'DELETE', params: {id: '@serie.id', idTituloS: '@titulo.id', idTemporada: '@temporada.id', idEpisodioS: '@id'} },
	})
});