package com.organization.mvcproject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.lang.IllegalArgumentException;

import com.organization.mvcproject.model.GameImpl;
import com.organization.mvcproject.model.Game;


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
		Game updated = updateGame(game);
		
		if(updated == null) {
			game.setId(++gameId);
			games.add(game);
			return game;
		}
		
		return updated;
	}
	
	private Game updateGame(Game game) {
		
		return games.stream()
			.filter(g -> Objects.equals(g.getId(), game.getId()))
			.map(g -> {
				g.setName(game.getName());
				g.setGenre(game.getGenre());
				return g;
			}).findAny()
			.orElse(null);
	}
	
	public Game findGameById(Game game) {
		return games.stream()
				.filter(g -> Objects.equals(g.getId(), game.getId()))
				.findAny()
				.orElse(null);
		
	}
	
	public boolean deleteGame(Game game) {
		return games.removeIf(g -> Objects.equals(g.getId(), game.getId()));
		
	}

	
}