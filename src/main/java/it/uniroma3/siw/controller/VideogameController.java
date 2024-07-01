package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.Videogame;
import it.uniroma3.siw.service.DeveloperService;
import it.uniroma3.siw.service.VideogameService;
import it.uniroma3.siw.validator.VideogameValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class VideogameController {
	@Autowired VideogameService videogameService;
	@Autowired DeveloperService developerService;
	@Autowired VideogameValidator videogameValidator;
	
	@GetMapping("/formNewGame")
	public String formNewGame(Model model) {
		model.addAttribute("game", new Videogame());
		model.addAttribute("developers", developerService.findAll());
		return "formNewGame.html";
	}
	@PostMapping("/games")
	public String newGame(@Valid@ModelAttribute("game") Videogame game,BindingResult bindingResult ,Model model,@RequestParam("image") MultipartFile multipartFile)throws IOException {
		this.videogameValidator.validate(game, bindingResult);
		if(bindingResult.hasErrors()) {
		   return "formNewGame.html";
		} else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        game.setUrlImage(fileName);
			this.videogameService.save(game);
			String uploadDir = "videogame-photo/";
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			model.addAttribute("game",game);
			return "redirect:default/game/"+game.getId();
		}
	}
	@GetMapping("/default/game/{id}")
	public String getGame(@PathVariable("id") Long id, Model model) {
		model.addAttribute("game", this.videogameService.findById(id));
		return "default/game.html";
	}
	@GetMapping("/default/games")
	public String showGames(Model model) {
		model.addAttribute("games", this.videogameService.findAll());
		return "default/games.html";
	}
	@GetMapping("/formSearchGames")
	public String formSearchGames() {
		return "formSearchGames.html";
	}
	@PostMapping("/searchGames")
	public String searchGames(Model model, @RequestParam Integer year) {
		model.addAttribute("games", this.videogameService.findByYear(year));
		return "foundGames.html";
	}
	@Transactional
	public void deleteAllByDeveloper(Developer developer) {
		videogameService.deleteAllByDeveloper(developer);
	}
}
