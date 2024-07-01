package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Award;
import it.uniroma3.siw.repository.AwardRepository;

@Component
public class AwardValidator implements Validator {
	
	@Autowired
	private AwardRepository awardRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Award award = (Award)o;
		if (award.getType()!=null && award.getYear()!=null
				&& awardRepository.existsByTypeAndYear(award.getType(),award.getYear())) {
			errors.reject("award.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Award.class.equals(aClass);
	}
}
