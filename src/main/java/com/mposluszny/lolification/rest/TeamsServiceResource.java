package com.mposluszny.lolification.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mposluszny.lolification.core.dao.TeamDao;
import com.mposluszny.lolification.core.domain.Team;

@Path(value="api/teams")
public class TeamsServiceResource {
	
	@Inject
	TeamDao teamDao;
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Team> getAllTeams() {
		List<Team> teams = teamDao.getAllTeams();
		for (Team team : teams) {
			team.setPlayers(teamDao.getPlayersForTeam(team));
		}
		return teams;
	}
	
	@GET
	@Path("/{idTeam}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public Team getTeamById(@PathParam("idTeam") long idTeam) {
		Team team = teamDao.getTeamById(idTeam);
		team.setPlayers(teamDao.getPlayersForTeam(team));
		return team;
	}

}
