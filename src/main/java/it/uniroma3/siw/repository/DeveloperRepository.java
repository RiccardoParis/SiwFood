package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Developer;

public interface DeveloperRepository extends CrudRepository<Developer,Long> {

	boolean existsByNameCompany(String nameCompany);
	void deleteById(Long id);
}
