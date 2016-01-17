package com.mposluszny.lolification.core.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
	@NamedQuery(name = "team.all", query = "SELECT t FROM Team t"),
	@NamedQuery(name = "team.byName", query = "SELECT t FROM Team t WHERE t.name = :name")
})
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idTeam;
	
	@Size(min=3, max=20, message="Must be between 3 and 20 letters.")
	private String name;
	
	@NotNull
	@Size(min=2, max=5)
	private String region;
	
	@Pattern(regexp="(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)",
			message="Wrong date format. Use dd-mm-yy.")
	private String dateOfEstablishment;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Player> players;
	
	public long getIdTeam() {
		return idTeam;
	}
	
	public void setIdTeam(long idTeam) {
		this.idTeam = idTeam;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getDateOfEstablishment() {
		return dateOfEstablishment;
	}
	
	public void setDateOfEstablishment(String dateOfEstablishment) {
		this.dateOfEstablishment = dateOfEstablishment;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
