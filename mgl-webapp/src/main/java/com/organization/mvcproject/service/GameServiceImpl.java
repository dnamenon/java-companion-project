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
	
	public GameServiceImpl() {
		gameDAO = new MockDAO();
	
	}

	
	public List<Game> retrieveAllGames() {
		
		return gameDAO.getGames();
	}

	
	public Game saveOrUpdateGame(Game game) {
		return gameDAO.saveOrUpdateGame(game);
	}
	
	
	public boolean deleteGame(Game game) {
		return gameDAO.deleteGame(game);
	}


	public Game findGameById(Game game) {
		return gameDAO.findGameById(game);
	}
}