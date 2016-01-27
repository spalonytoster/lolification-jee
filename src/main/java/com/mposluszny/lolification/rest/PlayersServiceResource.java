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

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.dao.TeamDao;
import com.mposluszny.lolification.core.domain.Player;
import com.mposluszny.lolification.core.domain.Team;
import com.mposluszny.lolification.core.domain.builder.PlayerBuilder;
import com.mposluszny.lolification.rest.dto.PlayerDto;
import com.mposluszny.lolification.rest.dto.builders.PlayerDtoBuilder;
import com.mposluszny.lolification.utils.LolificationMapper;

@Path(value="api/players")
public class PlayersServiceResource {

	@Inject
	PlayerDao playerDao;
	@Inject
	TeamDao teamDao;
	
	// getAll
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<PlayerDto> getAllPlayers() {
		ArrayList<PlayerDto> players = new ArrayList<>();
		for (Player player : playerDao.getAllPlayers()) {
			players.add(new PlayerDtoBuilder(player)
							.build());
		}
		return players;
	}
	
	// getById
	@GET
	@Path("/id/{idPlayer}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public PlayerDto getPlayerById(@PathParam("idPlayer") long idPlayer) {
		return new PlayerDtoBuilder(playerDao.getPlayerById(idPlayer)).build();
	}
	
	// getByIgn
	@GET
	@Path("/ign/{ign}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public PlayerDto getPlayerByIgn(@PathParam("ign") String ign) {
		if (Character.isLowerCase(ign.charAt(0))) {
			StringBuilder sb = new StringBuilder(ign);
			sb.replace(0, 1, String.valueOf(Character.toUpperCase(ign.charAt(0))));
			ign =  sb.toString();
		}
		return new PlayerDtoBuilder(playerDao.getPlayerByIgn(ign)).build();
	}
	
	// Create new object in DB
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTeam(InputStream inputStream) {
		
		ObjectMapper mapper = new ObjectMapper();
		Player player;
		
		try {
			PlayerDto dto = mapper.readValue(inputStream, PlayerDto.class);
			player = LolificationMapper.playerDtoToPlayer(dto);
			
			if (StringUtils.isNotEmpty(player.getTeam().getName())) {
				String teamName = player.getTeam().getName();
				Team team = teamDao.getTeamByName(teamName);
				player.setTeam(team);
			}
			player.setIdPlayer(0L);
			playerDao.addPlayer(player);
			
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
		
		return Response.status(201).entity("Player '" + player.getIgn() + "' has been created successfully.")
									.build();
	}
	
	// Update existing object in DB
	@PUT
	@Path("/id/{idPlayer}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTeam(@PathParam("idPlayer") long idPlayer, InputStream inputStream) {
		
		ObjectMapper mapper = new ObjectMapper();
		Player player;
		
		try {
			PlayerDto dto = mapper.readValue(inputStream, PlayerDto.class);
			player = playerDao.getPlayerById(idPlayer);
				player.setName(dto.getName());
				player.setSurname(dto.getSurname());
				player.setIgn(dto.getIgn());
				player.setRole(dto.getRole());
				player.setRetired(dto.getRetired());
				
			if (StringUtils.isNotEmpty(dto.getTeam()) &&
					!StringUtils.equals(dto.getTeam(), player.getTeam().getName())) {
					
				String teamName = dto.getTeam();
				Team team = teamDao.getTeamByName(teamName);
				player.setTeam(team);
			}

			else if (StringUtils.isEmpty(dto.getTeam())) {
				player.setTeam(null);
			}

			playerDao.updatePlayer(player);
			
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
		
		return Response.status(202).entity("Player '" + player.getIgn() + "' has been updated successfully.")
				.build();
	}
	
	// Removes existing object in DB
	@DELETE
	@Path("/id/{idPlayer}")
	public Response deleteTeam(@PathParam("idPlayer") long idPlayer) {
		
		Player player;
		
		try {
			player = new PlayerBuilder().idPlayer(idPlayer).build();
			playerDao.deletePlayer(player);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Something went wrong with the server. Sorry for inconvenience.")
										.build();
		}
		
		return Response.status(202).entity("Player '" + player.getIgn() + "' has been deleted successfully.")
				.build();
	}
	
	// Removes existing object in DB
		@DELETE
		@Path("/ign/{ign}")
		public Response deleteTeam(@PathParam("ign") String ign) {
			
			Player player;
			
			try {
				player = playerDao.getPlayerByIgn(ign);
				playerDao.deletePlayer(player);
			}
			
			catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).entity("Something went wrong with the server. Sorry for inconvenience.")
											.build();
			}
			
			return Response.status(202).entity("Player '" + player.getIgn() + "' has been deleted successfully.")
					.build();
		}
}
