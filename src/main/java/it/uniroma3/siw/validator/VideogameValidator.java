package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Videogame;
import it.uniroma3.siw.repository.VideogameRepository;

@Component
public class VideogameValidator implements Validator {
	
	@Autowired
	private VideogameRepository videogameRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Videogame videogame = (Videogame)o;
		if (videogame.getTitle()!=null && videogame.getDeveloper()!=null
				&& videogameRepository.existsByTitleAndDeveloper(videogame.getTitle(),videogame.getDeveloper())) {
			errors.reject("videogame.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Videogame.class.equals(aClass);
	}
}
