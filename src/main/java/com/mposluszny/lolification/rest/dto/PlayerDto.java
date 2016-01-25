package com.mposluszny.lolification.rest.dto;

public class PlayerDto {

	private long idPlayer;
	private String name;
	private String surname;
	private String ign;
	private String role;
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
