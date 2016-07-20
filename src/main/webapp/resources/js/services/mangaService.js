'use strict';

var services = angular.module('projetoHobbyApp.manga.services', ['ngResource']); 

services.factory('MangasPageFactory', function MangasFactory($resource) {
	return $resource('rest/mangas/page');
});

services.factory('MangasFactory', function MangasFactory($resource) {
	return $resource('rest/mangas', {}, {
		query: { method: 'GET', isArray: true },
		create: { method: 'POST' }
	})
});

services.factory('MangaFactory', function MangaFactory($resource) {
	return $resource('rest/mangas/:id', {}, {
		show: { method: 'GET' },
		update: { method: 'PUT', params: {id: '@id'} },
		delete: { method: 'DELETE', params: {id: '@id'} }
	})
});

services.factory('TitulosFactory', function TitulosFactory($resource) {
	return $resource('rest/manga/:id/titulos', {}, {
		query: { method: 'GET', isArray: true }
	})
});

services.factory('TituloCreateFactory', function TitulosFactory($resource) {
	return $resource('rest/manga/titulos', {}, {
		create: { method: 'POST' }
	})
});

services.factory('TituloFactory', function TituloFactory($resource) {
	return $resource('rest/manga/:id/titulos/:idTitulo', {}, {
		show: { method: 'GET', params: {id: '@manga.id', idTitulo: '@id'}  },
		update: { method: 'PUT', params: {id: '@id', idTitulo: '@id'} },
		delete: { method: 'DELETE', params: {id: '@manga', idTitulo: '@id'} }
	})
});


services.factory('VolumesFactory', function VolumesFactory($resource) {
	return $resource('rest/manga/:id/titulo/:idTitulo/volumes', {}, {
		query: { method: 'GET', params: {id: '@manga.id', idTitulo: '@id'}, isArray: true }
	})
});

services.factory('VolumeCreateFactory', function VolumeFactory($resource) {
	return $resource('rest/manga/titulo/volumes', {}, {
		create: { method: 'POST' }
	})
});

services.factory('VolumeFactory', function VolumeFactory($resource) {
	return $resource('rest/manga/:id/titulo/:idTitulo/volumes/:idVolume', {}, {
		show: { method: 'GET', params: {id: '@manga.id', idTitulo: '@titulo.id', idVolume: '@id'} }
	})
});

services.factory('CapitulosFactory', function CapitulosFactory($resource) {
	return $resource('rest/manga/:id/titulo/:idTitulo/volume/:idVolume/capitulos', {}, {
		query: { method: 'GET', params: {id: '@manga.id', idTitulo: '@titulo.id', idVolume: '@volume.id'} },
		create: { method: 'POST' }
	})
});