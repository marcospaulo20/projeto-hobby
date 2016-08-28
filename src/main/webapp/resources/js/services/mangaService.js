'use strict';

var services = angular.module('projetoHobbyApp.manga.services', ['ngResource']); 

services.factory('MangasFactory', function MangasFactory($resource) {
	return $resource('rest/mangas', {}, {
		query: { method: 'GET', isArray: true, ignoreLoadingBar: true },
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

services.factory('TitulosMFactory', function TitulosMFactory($resource) {
	return $resource('rest/manga/:id/titulos', {}, {
		query: { method: 'GET', isArray: true }
	})
});

services.factory('TituloMCreateFactory', function TitulosMCreateFactory($resource) {
	return $resource('rest/manga/titulos', {}, {
		create: { method: 'POST' }
	})
});

services.factory('TituloMFactory', function TituloMFactory($resource) {
	return $resource('rest/manga/:id/titulos/:idTituloM', {}, {
		show: { method: 'GET', params: {id: '@manga.id', idTituloM: '@id'}  },
		update: { method: 'PUT', params: {id: '@manga.id', idTituloM: '@id'} },
		delete: { method: 'DELETE', params: {id: '@manga.id', idTituloM: '@id'} }
	})
});

services.factory('VolumesFactory', function VolumesFactory($resource) {
	return $resource('rest/manga/:id/titulo/:idTituloM/volumes', {}, {
		query: { method: 'GET', params: {id: '@manga.id', idTituloM: '@id'}, isArray: true }
	})
});

services.factory('VolumeCreateFactory', function VolumeFactory($resource) {
	return $resource('rest/manga/titulo/volumes', {}, {
		create: { method: 'POST' }
	})
});

services.factory('VolumeFactory', function VolumeFactory($resource) {
	return $resource('rest/manga/:id/titulo/:idTituloM/volumes/:idVolume', {}, {
		show: { method: 'GET', params: {id: '@manga.id', idTituloM: '@titulo.id', idVolume: '@id'} },
		update: { method: 'PUT', params: {id: '@manga.id', idTituloM: '@tituo.id', idVolume: '@id'} },
		delete: { method: 'DELETE', params: {id: '@manga.id', idTituloM: '@tituo.id', idVolume: '@id'} },
	})
});

services.factory('CapitulosMFactory', function CapitulosMFactory($resource) {
	return $resource('rest/manga/:id/titulo/:idTituloM/volume/:idVolume/capitulos', {}, {
		query: { method: 'GET', params: {id: '@manga.id', idTituloM: '@titulo.id', idVolume: '@id'}, isArray: true }
	})
});

services.factory('CapituloMCreateFactory', function CapituloMFactory($resource) {
	return $resource('rest/manga/titulo/volume/capitulos', {}, {
		create: { method: 'POST' }
	})
});

services.factory('CapituloMFactory', function CapituloMFactory($resource) {
	return $resource('rest/manga/:id/titulo/:idTituloM/volume/:idVolume/capitulos/:idCapituloM', {}, {
		show: { method: 'GET', params: {id: '@manga.id', idTituloM: '@titulo.id', idVolume: '@volume.id', idCapituloM: '@id'} },
		update: { method: 'PUT', params: {id: '@manga.id', idTituloM: '@titulo.id', idVolume: '@volume.id', idCapituloM: '@id'} },
		delete: { method: 'DELETE', params: {id: '@manga.id', idTituloM: '@titulo.id', idVolume: '@volume.id', idCapituloM: '@id'} },
	})
});