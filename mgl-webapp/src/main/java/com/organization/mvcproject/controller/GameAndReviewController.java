
package com.organization.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.organization.mvcproject.model.Game;
import com.organization.mvcproject.model.GameImpl;
import com.organization.mvcproject.model.Review;
import com.organization.mvcproject.service.GameService;


@Controller
public class GameAndReviewController {

	
	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ModelAndView review() {
	
		return new ModelAndView("reviewCreatePage", "command", new Review());
	}

	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	public ModelAndView addReview(Review review, ModelMap model) {
		if(review.getAuthor().equals("")) {
			review.setAuthor("anonymous");
		}
	
		return new ModelAndView("reviewDetailPage", "submittedReview", review);
	}

	
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public ModelAndView game() {
		
		return new ModelAndView("gamesPage", "command", new GameImpl());
	}

	/**
	 * TODO 2.0 (Separation of concerns) consider moving all controller endpoints that return a ResponseEntity into a @RestController.
	 */
	
	
	@RequestMapping(value = "/game", method = RequestMethod.GET)
	public ResponseEntity<List<Game>> fetchAllGames() {
		return new ResponseEntity<List<Game>>(gameService.retrieveAllGames(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/game/byGenre", method = RequestMethod.GET)
	public ResponseEntity<List<Game>> fetchAllByGenre() {
		return new ResponseEntity<List<Game>>(gameService.retrieveGamesByGenre(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/game/nameSearch", method = RequestMethod.POST)
	public ResponseEntity<List<Game>> fetchAllSearchedByName(@RequestBody String nameSearchTerm) {
		return new ResponseEntity<List<Game>>(gameService.searchByName(nameSearchTerm), HttpStatus.OK);
	}



	
	@RequestMapping(value = "/game/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createGame(@RequestBody GameImpl game) {
		gameService.saveGame(game);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/game/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateGame(@RequestBody GameImpl game) {
		gameService.updateGame(game);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
 
	@DeleteMapping(value = "game/{id}" )
	public ResponseEntity<?> deleteGame(@PathVariable("id") Long gameId ){
		return new ResponseEntity<>(gameService.deleteGame(gameId), HttpStatus.OK);
	}
	
	
}