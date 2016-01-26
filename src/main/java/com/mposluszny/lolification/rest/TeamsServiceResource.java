package com.mposluszny.lolification.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.mposluszny.lolification.core.dao.TeamDao;
import com.mposluszny.lolification.core.domain.Team;
import com.mposluszny.lolification.rest.dto.TeamDto;
import com.mposluszny.lolification.rest.dto.builders.TeamDtoBuilder;
import com.mposluszny.lolification.utils.LolificationMapper;

@Path(value="api/teams")
public class TeamsServiceResource {
	
	@Inject
	TeamDao teamDao;
	
	// getAll
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
	
	// getById
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
	
	// Create new object in DB
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTeam(InputStream inputStream) {
		
		ObjectMapper mapper = new ObjectMapper();
		Team team;
		
		try {
			TeamDto dto = mapper.readValue(inputStream, TeamDto.class);
			team = LolificationMapper.teamDtoToTeam(dto);
			team.setIdTeam(0L);
			teamDao.addTeam(team);
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(500).entity("You gave me wrong parameters bro.\n"
												+ "Try to POST a valid JSON next time, ok? :(")
										.build();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Something went wrong with the server. Sorry for inconvenience.")
										.build();
		}
		
		return Response.status(201).entity("Team '" + team.getName() + "' has been created successfully.")
									.build();
	}
	
	// Update existing object in DB
	@PUT
	@Path("/id/{idTeam}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTeam(@PathParam("idTeam") long idTeam, InputStream inputStream) {
		
		ObjectMapper mapper = new ObjectMapper();
		Team team;
		
		try {
			TeamDto dto = mapper.readValue(inputStream, TeamDto.class);
			team = teamDao.getTeamById(idTeam);
				team.setName(dto.getName());
				team.setRegion(dto.getRegion());
				team.setDateOfEstablishment(dto.getDateOfEstablishment());
			teamDao.updateTeam(team);
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(500).entity("You gave me wrong parameters bro.\n"
												+ "Try to PUT a valid JSON next time, ok? :(")
										.build();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Something went wrong with the server. Sorry for inconvenience.")
										.build();
		}
		
		return Response.status(202).entity("Team '" + team.getName() + "' has been updated successfully.")
				.build();
	}
	
	// Removes existing object in DB
	@DELETE
	@Path("/id/{idTeam}")
	public Response deleteTeam(@PathParam("idTeam") long idTeam) {
		
		Team team;
		
		try {
			team = teamDao.getTeamById(idTeam);
			teamDao.deleteTeam(team);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Something went wrong with the server. Sorry for inconvenience.")
										.build();
		}
		
		return Response.status(202).entity("Team '" + team.getName() + "' has been deleted successfully.")
				.build();
	}

}
