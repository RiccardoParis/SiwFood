package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Award;
import it.uniroma3.siw.model.Videogame;
import it.uniroma3.siw.service.AwardService;
import it.uniroma3.siw.service.VideogameService;
import it.uniroma3.siw.validator.AwardValidator;
import jakarta.validation.Valid;

@Controller
public class AwardController {
	@Autowired AwardService awardService;
	@Autowired VideogameService videogameService;
	@Autowired AwardValidator awardValidator;
	
	@GetMapping("/admin/formNewAward")
	public String formNewAward(Model model) {
		model.addAttribute("award", new Award());
		return "admin/formNewAward.html";
	}
	@PostMapping("/awards")
	public String newAward(@Valid@ModelAttribute("award") Award award,BindingResult bindingResult, Model model) {
		this.awardValidator.validate(award, bindingResult);
		if(!bindingResult.hasErrors()) {
			award.inizializzaLista();
			this.awardService.save(award);
			model.addAttribute("award", award);
			return "default/award.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo premio esiste già");
			return "admin/formNewAward.html";
		}
	}
	@GetMapping("/default/award/{id}")
	public String getAward(@PathVariable("id") Long id, Model model) {
		model.addAttribute("award", this.awardService.findById(id));
		return "default/award.html";
	}
	@GetMapping("/default/awards")
	public String showAwards(Model model) {
		model.addAttribute("awards", this.awardService.findAll());
		return "default/awards.html";
	}
	@GetMapping("/manageAwards")
	public String manageAwards(Model model) {
		model.addAttribute("awards", this.awardService.findAll());
		return "manageAwards.html";
	}
	@GetMapping("/formUpdateAward/{id}/{awardType}/{awardYear}")
	public String formUpdateAward(@PathVariable("id") Long id, Model model) {
		model.addAttribute("award", awardService.findById(id));
		return "formUpdateAward.html";
	}
	@GetMapping("/updateNominations/{id}/{awardType}/{awardYear}")
	public String updateNominations(@PathVariable("awardType") String awardType,@PathVariable("awardYear") Integer awardYear, @PathVariable("id") Long id, Model model) {
		List<Videogame> nominationsToAdd = this.nominationsToAdd(awardType,awardYear, id);
		model.addAttribute("nominationsToAdd", nominationsToAdd);
		model.addAttribute("award", this.awardService.findById(id));

		return "nominationsToAdd.html";
	}
	@GetMapping(value="/addNominationToAward/{nominationId}/{awardId}/{awardType}/{awardYear}")
	public String addNominationToAward(@PathVariable("nominationId") Long nominationId, @PathVariable("awardType") String awardType,@PathVariable("awardYear") Integer awardYear, @PathVariable("awardId") Long awardId, Model model) {
		Award award = this.awardService.findById(awardId);
		Videogame nomination = this.videogameService.findById(nominationId);
		List<Videogame> nominations = award.getNominations();
		nominations.add(nomination);
		this.awardService.save(award);
		
		List<Videogame> nominationsToAdd = nominationsToAdd(awardType,awardYear, awardId);
		
		model.addAttribute("award", award);
		model.addAttribute("nominationsToAdd", nominationsToAdd);

		return "nominationsToAdd.html";
	}
	@GetMapping(value="/removeNominationFromAward/{nominationId}/{awardId}/{awardType}/{awardYear}")
	public String removeNominationFromAward(@PathVariable("nominationId") Long nominationId, @PathVariable("awardType") String awardType,@PathVariable("awardYear") Integer awardYear,@PathVariable("awardId") Long awardId, Model model) {
		Award award = this.awardService.findById(awardId);
		Videogame nomination= this.videogameService.findById(nominationId);
		List<Videogame> nominations= award.getNominations();
		nominations.remove(nomination);
		this.awardService.save(award);

		List<Videogame> nominationsToAdd = nominationsToAdd(awardType,awardYear,awardId);
		
		model.addAttribute("award", award);
		model.addAttribute("nominationsToAdd", nominationsToAdd);

		return "nominationsToAdd.html";
	}
	private List<Videogame> nominationsToAdd(String awardType, Integer awardYear,Long awardId) {
		List<Videogame> nominationsToAdd = new ArrayList<>();

		for (Videogame v : videogameService.findVideogamesByAwardTypeAndAwardYearAndNotInAward(awardType,awardYear, awardId)) {
			nominationsToAdd.add(v);
		}
		return nominationsToAdd;
	}
	@GetMapping("/admin/manageAwardsAdmin")
	public String manageAwardsAdmin(Model model) {
		model.addAttribute("awards", this.awardService.findAll());
		return "admin/manageAwardsAdmin.html";
	}
	@GetMapping("/admin/formUpdateAwardAdmin/{id}/{awardType}")
	public String formUpdateAwardAdmin(@PathVariable("id") Long id, Model model) {
		model.addAttribute("award", awardService.findById(id));
		return "admin/formUpdateAwardAdmin.html";
	}
	@GetMapping("/addWinner/{id}")
	public String addWinner(@PathVariable("id") Long id, Model model) {
		model.addAttribute("award", awardService.findById(id));
		model.addAttribute("nominations", awardService.findById(id).getNominations());
		return "admin/winnerToAdd.html";
	}
	@GetMapping("/setWinnerToAward/{winnerId}/{awardId}")
	public String setWinnerToAward(@PathVariable("winnerId") Long winnerId, @PathVariable("awardId") Long awardId, Model model) {
		Videogame winner = this.videogameService.findById(winnerId);
		Award award = this.awardService.findById(awardId);
		award.setWinner(winner);
		this.awardService.save(award);
		
		model.addAttribute("award", award);
		return "admin/formUpdateAwardAdmin.html";
	}
	
}
