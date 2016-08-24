'use strict';

var services = angular.module('projetoHobbyApp.comic.services', ['ngResource']); 

services.factory('ComicsFactory', function ComicsFactory($resource) {
	return $resource('rest/comics', {}, {
		query: { method: 'GET', isArray: true },
		create: { method: 'POST' }
	})
});

services.factory('ComicFactory', function ComicFactory($resource) {
	return $resource('rest/comics/:id', {}, {
		show: { method: 'GET' },
		update: { method: 'PUT', params: {id: '@id'} },
		delete: { method: 'DELETE', params: {id: '@id'} }
	})
});

services.factory('TitulosCFactory', function TitulosCFactory($resource) {
	return $resource('rest/comic/:id/titulos', {}, {
		query: { method: 'GET', isArray: true }
	})
});

services.factory('TituloCCreateFactory', function TitulosCCreateFactory($resource) {
	return $resource('rest/comic/titulos', {}, {
		create: { method: 'POST' }
	})
});

services.factory('TituloCFactory', function TituloCFactory($resource) {
	return $resource('rest/comic/:id/titulos/:idTituloC', {}, {
		show: { method: 'GET', params: {id: '@comic.id', idTituloC: '@id'}  },
		update: { method: 'PUT', params: {id: '@comic.id', idTituloC: '@id'} },
		delete: { method: 'DELETE', params: {id: '@comic.id', idTituloC: '@id'} }
	})
});

services.factory('CapitulosCFactory', function CapitulosCFactory($resource) {
	return $resource('rest/comic/:id/titulo/:idTituloC/capitulosC', {}, {
		query: { method: 'GET', params: {id: '@comic.id', idTituloC: '@id'}, isArray: true }
	})
});

services.factory('CapituloCCreateFactory', function CapituloCCreateFactory($resource) {
	return $resource('rest/comic/titulo/capitulosC', {}, {
		create: { method: 'POST' }
	})
});

services.factory('CapituloCFactory', function CapituloCFactory($resource) {
	return $resource('rest/comic/:id/titulo/:idTituloC/capitulosC/:idCapituloC', {}, {
		show: { method: 'GET', params: {id: '@comic.id', idTituloC: '@titulo.id', idCapituloC: '@id'} },
		update: { method: 'PUT', params: {id: '@comic.id', idTituloC: '@titulo.id', idCapituloC: '@id'} },
		delete: { method: 'DELETE', params: {id: '@comic.id', idTituloC: '@titulo.id', idCapituloC: '@id'} },
	})
});