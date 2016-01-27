package com.mposluszny.lolification.rest.dto.builders;

import java.util.ArrayList;
import java.util.List;

import com.mposluszny.lolification.core.domain.Player;
import com.mposluszny.lolification.core.domain.Team;
import com.mposluszny.lolification.rest.dto.TeamDto;

public class TeamDtoBuilder {
	
	public TeamDtoBuilder() {}
	
	public TeamDtoBuilder(Team team) {
		
		this.team = new TeamDtoBuilder()
					.idTeam(team.getIdTeam())
					.name(team.getName())
					.region(team.getRegion())
					.dateOfEstablishment(team.getDateOfEstablishment())
					.build();
		
		if (team.getPlayers() != null) {
			this.team.setPlayers(new ArrayList<String>());
			for (Player player : team.getPlayers()) {
				this.team.getPlayers().add(player.getIgn());
			}
		}
		
	}
	
	private TeamDto team = new TeamDto();
	
	public TeamDtoBuilder idTeam(long idTeam) {
		team.setIdTeam(idTeam);
		return this;
	}
	
	public TeamDtoBuilder name(String name) {
		team.setName(name);
		return this;
	}
	
	public TeamDtoBuilder region(String region) {
		team.setRegion(region);
		return this;
	}
	
	public TeamDtoBuilder dateOfEstablishment(String dateOfEstablishment) {
		team.setDateOfEstablishment(dateOfEstablishment);;
		return this;
	}
	
	public TeamDtoBuilder players(List<String> players) {
		team.setPlayers(players);
		return this;
	}
	
	public TeamDto build() {
		return team;
	}

}
