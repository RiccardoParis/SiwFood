package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Award;
import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.Videogame;
import it.uniroma3.siw.service.AwardService;
import it.uniroma3.siw.service.DeveloperService;
import it.uniroma3.siw.service.VideogameService;
import jakarta.transaction.Transactional;

@Controller
public class DeveloperController {
	@Autowired DeveloperService developerService;
	@Autowired VideogameService videogameService;
	@Autowired AwardService awardService;
	
	@GetMapping("/admin/formNewDeveloper")
	public String formNewDeveloper(Model model) {
		model.addAttribute("developer", new Developer());
		return "admin/formNewDeveloper.html";
	}
	@PostMapping("/admin/developers")
	public String newDeveloper(@ModelAttribute("developer") Developer developer, Model model,@RequestParam("image") MultipartFile multipartFile)throws IOException {
		if(!developerService.existsByNameCompany(developer.getNameCompany())) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			developer.setLogo(fileName);
			this.developerService.save(developer);
			String uploadDir = "videogame-photo/";
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			model.addAttribute("developer", developer);
			return "admin/developer.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo sviluppatore esiste già");
			return "admin/formNewDeveloper.html";
		}
	}
	@GetMapping("/admin/developer/{id}")
	public String getGame(@PathVariable("id") Long id, Model model) {
		model.addAttribute("developer", this.developerService.findById(id));
		return "admin/developer.html";
	}
	@GetMapping("/admin/developers")
	public String showDevelopers(Model model) {
		model.addAttribute("developers", this.developerService.findAll());
		return "admin/developers.html";
	}
	@Transactional
	@GetMapping("/admin/deleteDeveloper/{developerId}")
	public String deleteDeveloper(@PathVariable("developerId") Long id, Model model) {
		Developer developer = this.developerService.findById(id);
		//List<Award> awards = (List<Award>) awardService.findAll();
		for(Award award: awardService.findAll()) {
			if((award.getWinner() != null) && award.getWinner().getDeveloper().equals(developer)) {
				award.setWinner(null);
			}
			Iterator<Videogame> iterator = award.getNominations().iterator();
		    while (iterator.hasNext()) {
		        Videogame v = iterator.next();
		        if (v.getDeveloper().equals(developer)) {
		            iterator.remove();
		        }
		    }
		}
		videogameService.deleteAllByDeveloper(developer);
		developerService.deleteById(id);
		return "admin/confirmDeleteDeveloper.html";
	}
}
