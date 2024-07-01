package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Award {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String type;
	@Min(1972)
	@Max(2024)
	private Integer year;
	
	@ManyToOne
	private Videogame winner;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Videogame> nominations;
	
	public void inizializzaLista(){
		this.nominations=new ArrayList<Videogame>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Videogame getWinner() {
		return winner;
	}
	public void setWinner(Videogame winner) {
		this.winner = winner;
	}
	public List<Videogame> getNominations() {
		return nominations;
	}
	public void setNominations(List<Videogame> nominations) {
		this.nominations = nominations;
	}
	@Override
	public int hashCode() {
		return Objects.hash(type, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Award other = (Award) obj;
		return Objects.equals(type, other.type) && Objects.equals(year, other.year);
	}
}
