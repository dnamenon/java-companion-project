package com.organization.mvcproject.service;

import java.util.List;

import com.organization.mvcproject.model.Game;


public interface GameService {

	List<Game> retrieveAllGames();

	Game saveGame(Game game);
	
	boolean updateGame(Game game);
	
	boolean deleteGame(Game game);

}
