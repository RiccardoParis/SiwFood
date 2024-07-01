package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Award;
import it.uniroma3.siw.repository.AwardRepository;

@Service
public class AwardService {
	@Autowired private AwardRepository awardRepository;
	
	public void save(Award prize) {
		awardRepository.save(prize);
	}
	public Award findById(Long id) {
		return awardRepository.findById(id).get();
	}
	public Iterable<Award> findAll() {
		return awardRepository.findAll();
	}
	// metodi chiamati
	public boolean existsByTypeAndYear(String type,Integer year) {
		return awardRepository.existsByTypeAndYear(type, year);
	}
}