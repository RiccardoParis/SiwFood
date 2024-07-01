package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import it.uniroma3.siw.model.Videogame;
import it.uniroma3.siw.service.VideogameService;



@RestController
public class VideogameRestController {
	
	@Autowired
	public VideogameService videogameService;
	
	@GetMapping(value="/rest/videogames")
	public List<Videogame> showRestVideogames(){
		return (List<Videogame>) this.videogameService.findAll();
		}
	
	@GetMapping(value="/rest/videogame/{id}")
	public Videogame showRestVideogame(@PathVariable("id")Long id) {
		return this.videogameService.findById(id);
	}

}

