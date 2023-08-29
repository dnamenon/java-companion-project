package com.organization.mvcproject.model;

import org.springframework.stereotype.Component;


public interface Game {

	 Long getId(); 

	 void setId(Long gameId);

	 String getName();

	 void setName(String gameName) ;

	 String getGenre();

	 void setGenre(String gameGenre);

}
