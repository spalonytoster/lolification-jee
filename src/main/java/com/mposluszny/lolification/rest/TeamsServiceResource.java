package com.mposluszny.lolification.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mposluszny.lolification.core.dao.TeamDao;
import com.mposluszny.lolification.core.domain.Team;
import com.mposluszny.lolification.rest.dto.TeamDto;
import com.mposluszny.lolification.rest.dto.builders.TeamDtoBuilder;

@Path(value="api/teams")
public class TeamsServiceResource {
	
	@Inject
	TeamDao teamDao;
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<TeamDto> getAllTeams() {
		List<TeamDto> teams = new ArrayList<>();
		for (Team team : teamDao.getAllTeams()) {
			team.setPlayers(teamDao.getPlayersForTeam(team));
			teams.add(new TeamDtoBuilder(team).build());
		}
		return teams;
	}
	
	@GET
	@Path("/id/{idTeam}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public TeamDto getTeamById(@PathParam("idTeam") long idTeam) {
		Team team = teamDao.getTeamById(idTeam);
		team.setPlayers(teamDao.getPlayersForTeam(team));
		TeamDto result = new TeamDtoBuilder(team).build();
		team.setPlayers(teamDao.getPlayersForTeam(team));
		return result;
	}

}
