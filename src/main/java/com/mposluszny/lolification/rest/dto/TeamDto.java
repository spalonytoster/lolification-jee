package com.mposluszny.lolification.rest.dto;

import java.util.List;

public class TeamDto {

	private long idTeam;
	private String name;
	private String region;
	private String dateOfEstablishment;
	private List<String> players;
	
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

	public List<String> getPlayers() {
		return players;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}
}
