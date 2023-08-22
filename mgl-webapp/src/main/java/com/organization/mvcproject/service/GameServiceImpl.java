package com.organization.mvcproject.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.organization.mvcproject.dao.MockDAO;
import com.organization.mvcproject.model.Game;



@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private MockDAO gameDAO; 
	

	
	public List<Game> retrieveAllGames() {
		
		return gameDAO.getGames();
	}

	
	public Game saveGame(Game game) {
		return gameDAO.saveGame(game);
	}
	
	public Game updateGame(Game game) {
		return gameDAO.updateGame(game);
	}
	
	
	
	public boolean deleteGame(Long gameId) {
		return gameDAO.deleteGame(gameId);
	}


	public Game findGameById(Game game) {
		return gameDAO.findGameById(game);
	}
	
	
	public List<Game> retrieveGamesByGenre() {
		
		return gameDAO.getGamesByGenre();
	}
}