package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.Videogame;

public interface VideogameRepository extends CrudRepository<Videogame,Long> {

	boolean existsByTitleAndDeveloper(String title, Developer developer);

	public List<Videogame> findByYear(Integer year);

	public List<Videogame> findByDeveloper(Developer developer);
	
	@Query(value = "select * "
			+ "from videogame v "
			+ "where v.genre =:awardType and v.year =:awardYear and v.id not in "
			+ "(select nominations_id "
			+ "from award_nominations "
			+ "where award_nominations.award_id = :awardId)", nativeQuery = true)
	public  Videogame[] findVideogamesByAwardTypeAndAwardYearAndNotInAward(@Param("awardType") String awardType,@Param("awardYear") Integer awardYear, @Param("awardId") Long awardId);

	public void deleteAllByDeveloper(Developer developer);
}
