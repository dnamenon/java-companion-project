'use strict';


angular.module('MGL_Task1_app').controller('MGL_Task1_Controller',
		[ 'MGL_Task1_Service', function(MGL_Task1_Service) {
			var self = this;
			self.game = {
				id : '',
				name : '',
				genre : ''
			};
			
			
			class ById {
				constructor() {
					this.asString = "default"
	  			}
	
	  			getSorted() {
	   			 return MGL_Task1_Service.fetchAllGames()
	  			}
			}

			class ByGenre {
  				constructor() {
    				this.asString = "genre"
  				}

  				getSorted() {
    				return MGL_Task1_Service.fetchAllByGenre()
  				}
  			}
  			
			self.games = [];
			
			var sortType;
			self.sortTypeString;
			var defaultSort = new ById();
			var genreSort = new ByGenre();
			
			
			self.nameSearchTerm = '';
			
			

			self.fetchAllGames = function(){
				sortType.getSorted().then(function(data) {
					self.games = data;
					self.resetForm();
				});
			}
			
			self.sortDefault = function(){
				sortType = defaultSort;
				self.sortTypeString = sortType.asString;
				self.fetchAllGames();
			}
			
			self.sortByGenre = function(){
				sortType = genreSort;
				self.sortTypeString = sortType.asString;
				self.fetchAllGames();
			}
			
			self.searchByName = function(){
				return MGL_Task1_Service.fetchAllSearchedByName(self.nameSearchTerm).then(function(data){
						self.games = data;
				});
				
			}
			
			self.resetSearch = function(){
				self.nameSearchTerm = '';
				self.fetchAllGames();
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
			
			
			self.sortDefault();
			self.fetchAllGames();
		} ]);
