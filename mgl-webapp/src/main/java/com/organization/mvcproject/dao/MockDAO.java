package com.organization.mvcproject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.organization.mvcproject.model.GameImpl;
import com.organization.mvcproject.model.Game;

@Service
public class MockDAO{
	
	
	private static Long gameId = new Long(0);
	private static Long companyId = new Long(0);
	private static List<Game> games = new ArrayList<Game>();

	static {
		games = populateGames();
	}

	private static List<Game> populateGames() {

		Game game1 = new GameImpl();
		game1.setId(++gameId);
		game1.setGenre("Sport");
		game1.setName("Rocket League");

		Game game2 = new GameImpl();
		game2.setId(++gameId);
		game2.setGenre("Shooter");
		game2.setName("Halo 3");

		Game game3 = new GameImpl();
		game3.setId(++gameId);
		game3.setGenre("MMORPG");
		game3.setName("Runescape");

		games.add(game1);
		games.add(game2);
		games.add(game3);

		return games;
	}

	
	public List<Game> getGames() {
		return games;
	}

	
	public Game saveGame(Game game) {
		
			game.setId(++gameId);
			games.add(game);
			return game;
	

	}
	
	public Game updateGame(Game game) {
	    	games = games.stream()
	    		    .map(g -> g.getId().equals(game.getId()) ? (GameImpl) game : g)
	    		    .collect(Collectors.toList());
	    	return findGameById(game);
	}
	
	public Game findGameById(Game game) {
		return (game.getId() == null) ? null : games.stream()
				.filter(g -> Objects.equals(g.getId(), game.getId()))
				.findAny()
				.orElse(null);
		
	}
	
	public boolean deleteGame(Long gameId) {
		return games.removeIf(g -> Objects.equals(g.getId(), gameId));
		
	}


	public List<Game> getGamesByGenre() {
		return games.stream()
				.sorted((g1, g2) -> (g1.getGenre()).compareTo(g2.getGenre()))
				.collect(Collectors.toList());
	}

	
}