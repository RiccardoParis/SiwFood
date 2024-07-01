package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.repository.DeveloperRepository;

@Service
public class DeveloperService {
	@Autowired private DeveloperRepository developerRepository;
	
	public void save(Developer developer) {
		developerRepository.save(developer);
	}
	public Developer findById(Long id) {
		return developerRepository.findById(id).get();
	}
	public Iterable<Developer> findAll() {
		return developerRepository.findAll();
	}
	// metodi chiamati
	public boolean existsByNameCompany(String nameCompany) {
		return developerRepository.existsByNameCompany(nameCompany);
	}
	public void deleteById(Long id) {
		developerRepository.deleteById(id);
	}
	
}
