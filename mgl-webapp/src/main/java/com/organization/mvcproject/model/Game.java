package com.organization.mvcproject.model;

import org.springframework.stereotype.Component;

@Component
public interface Game {

	public Long getId(); 

	public void setId(Long gameId);

	public String getName();

	public void setName(String gameName) ;

	public String getGenre();


	public void setGenre(String gameGenre);

}
