package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.Videogame;
import it.uniroma3.siw.repository.VideogameRepository;

@Service
public class VideogameService {
	@Autowired private VideogameRepository videogameRepository;

	public void save(Videogame game) {
		videogameRepository.save(game);
	}
	public Videogame findById(Long id) {
		return videogameRepository.findById(id).get();
	}
	public Iterable<Videogame> findAll() {
		return videogameRepository.findAll();
	}
	// metodi chiamati
	public boolean existsByTitleAndDeveloper(String title, Developer developer) {
		return videogameRepository.existsByTitleAndDeveloper(title, developer);
	}
	public List<Videogame> findByYear(Integer year) {
		return videogameRepository.findByYear(year);
	}
	public List<Videogame> findByDeveloper(Developer developer) {
		return videogameRepository.findByDeveloper(developer);
	}
	public Videogame[] findVideogamesByAwardTypeAndAwardYearAndNotInAward(String awardType,Integer awardYear, Long awardId) {
		return videogameRepository.findVideogamesByAwardTypeAndAwardYearAndNotInAward(awardType,awardYear, awardId);
	}
	public void deleteAllByDeveloper(Developer developer) {
		videogameRepository.deleteAllByDeveloper(developer);
	}
}
