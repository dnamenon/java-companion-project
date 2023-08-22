'use strict';

angular.module('MGL_Task1_app').controller('MGL_Task1_Controller',
		[ 'MGL_Task1_Service', function(MGL_Task1_Service) {
			var self = this;
			self.game = {
				id : '',
				name : '',
				genre : ''
			};
			self.games = [];

			self.fetchAllGames = function(){
				MGL_Task1_Service.fetchAllGames().then(function(data) {
					self.games = data;
					self.resetForm();
				});
			}
			
			self.fetchAllByGenre = function(){
				MGL_Task1_Service.fetchAllByGenre().then(function(data) {
					self.games = data;
					self.resetForm();
				});
			}

			self.addGame = function(){
				return MGL_Task1_Service.createGame(self.game).then( function() {
				self.fetchAllGames();
				});
			}
			
			self.updateGame = function(){
				return MGL_Task1_Service.updateGame(self.game).then( function() {
				self.fetchAllGames();
				});
			}
			
			self.selectGame = function(toUpdate){
				self.game = angular.copy(toUpdate)
				
			}
			
			self.resetForm = function(){
				self.game = {}
			}
			
			self.deleteGame = function(gameToDelete){
				return MGL_Task1_Service.deleteGame(gameToDelete.id).then( function() {
				 self.fetchAllGames(); 
				});	
			}

			self.fetchAllGames();
		} ]);
