package com.mposluszny.lolification.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="PLAYER")
@NamedQueries({
	@NamedQuery(name = "player.all", query = "SELECT p FROM Player p"),
	@NamedQuery(name = "player.byIgn", query = "SELECT p FROM Player p WHERE p.ign = :ign")
})
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPlayer;
	
	@Size(min=3, max=20, message="Must be between 3 and 20 letters.")
	private String name;
	
	@Size(min=3, max=20, message="Must be between 3 and 20 letters.")
	private String surname;
	
	@Size(min=3, max=20, message="Must be between 3 and 20 letters.")
	@NotNull
	private String ign;
	
	private String role;
	
	@JoinColumn(referencedColumnName="name", table="team")
	private String team;
	
	private boolean retired;
	
	public long getIdPlayer() {
		return idPlayer;
	}
	
	public void setIdPlayer(long idPlayer) {
		this.idPlayer = idPlayer;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getIgn() {
		return ign;
	}
	
	public void setIgn(String ign) {
		this.ign = ign;
	}
	
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean getRetired() {
		return retired;
	}
	
	public void setRetired(boolean retired) {
		this.retired = retired;
	}

}
