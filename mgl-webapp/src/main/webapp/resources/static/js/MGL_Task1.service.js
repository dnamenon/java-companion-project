'use strict';

angular.module('MGL_Task1_app').factory('MGL_Task1_Service', ['$http', function($http) {

		var REST_SERVICE_URI = 'game/';

		var factory = {
			fetchAllGames : fetchAllGames,
			createGame : createGame
		};

		return factory;

		function fetchAllGames() {
			return $http.get('game').then(function(response) {
					return response.data;
				}
			);
		}

		function createGame(game) {
			return $http.post('game', game).then(function(response) {
					return response.data;
				}
			);
		}

}]);
