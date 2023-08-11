package com.organization.mvcproject.model;

import org.springframework.stereotype.Component;

@Component
public class Game {

	
	private Long id;
	private String name;
	private String genre;

	public Long getGameId() {
		return id;
	}

	public void setGameId(Long gameId) {
		this.id = gameId;
	}

	public String getGameName() {
		return name;
	}

	public void setGameName(String gameName) {
		this.name = gameName;
	}

	public String getGameGenre() {
		return genre;
	}

	public void setGameGenre(String gameGenre) {
		this.genre = gameGenre;
	}

}
