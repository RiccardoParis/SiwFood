package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Award;

public interface AwardRepository extends CrudRepository<Award,Long> {

	boolean existsByTypeAndYear(String type, Integer year);

}
