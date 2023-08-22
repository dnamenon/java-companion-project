package com.organization.mvcproject.app.service;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.organization.mvcproject.model.Game;
import com.organization.mvcproject.model.GameImpl;
import com.organization.mvcproject.service.*;
import com.organization.mvcproject.config.MvcConfiguration;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MvcConfiguration.class)
@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
class Game_Service_ImplTest {
	
	
	@Autowired
	private GameService gameServiceUnderTest;
	
	private static Game testGame = createGame(1);
	
	private  static final String TEST_GENRE = "Test Genre";
	private static Game createGame(Integer number) {
		Game game = new GameImpl();
		 game.setName("Testing Game Name " + String.valueOf(number));
		 game.setGenre(TEST_GENRE);
		 return game;
	}
	
	private static List<Game> gamesToRemoveAfterTest = new ArrayList<>();
	
	@BeforeAll
	@Test
	void saveGameServiceSavesAndUpdatesGame() {
		if(gamesToRemoveAfterTest.isEmpty()) {
			Game game = gameServiceUnderTest.saveGame(testGame);
		
			Assertions.assertNotNull(game.getId());
			
			//updates 
			game.setName("Testing Game Name Updated" );
			testGame = gameServiceUnderTest.updateGame(game);
		
			assertEquals(game, testGame);	
			gamesToRemoveAfterTest.add(testGame);
			//the saveGame works, save another game to setup list operation tests
			gamesToRemoveAfterTest.add(gameServiceUnderTest.saveGame(createGame(2)));
		}
	}
	
	@AfterAll
	@Test
	void deleteGameWorksAndCleanupServiceTest() {
		int initialSize = gameServiceUnderTest.retrieveAllGames().size();
		
		int numRemoved = 0;
		if(!gamesToRemoveAfterTest.isEmpty()) {
			for(Game toRemove: gamesToRemoveAfterTest) {
				
				assertTrue(gameServiceUnderTest.deleteGame(toRemove.getId()));
				assertNull(gameServiceUnderTest.findGameById(toRemove));
				numRemoved++;
			}
		}
		
		assertEquals(initialSize-numRemoved, gameServiceUnderTest.retrieveAllGames().size());
	}
	
	
	@Test
	void findGameByIdReturnsTheGame() {
		Game game = gameServiceUnderTest.findGameById(testGame);
		assertNotNull(game);
		assertEquals(game, testGame);
	}
	


	
	@Test
  	void retrieveAllGamesServiceReturnsGames() {
		List<Game> games = gameServiceUnderTest.retrieveAllGames(); 
		assertNotNull(games);
		assertTrue(games.size() >= 2 );
	}
	
	
	@Test
	void retrieveGamesByGenre() {
		fail("Not yet implemented.");
	}
	
	
	


}
