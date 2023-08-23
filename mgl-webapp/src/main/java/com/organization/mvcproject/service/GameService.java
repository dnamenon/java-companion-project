package com.organization.mvcproject.service;

import java.util.List;
import java.util.Optional;

import com.organization.mvcproject.model.Game;


public interface GameService {

	List<Game> retrieveAllGames();

	Game saveGame(Game game);
	
	Game updateGame(Game game);
	
	Optional<Game> findGameById(Game game);
	
	boolean deleteGame(Long gameId);
	
	List<Game> retrieveGamesByGenre();
	
	List<Game> searchByName(String searchTerm);

}
