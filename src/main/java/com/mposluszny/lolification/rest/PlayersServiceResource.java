package com.mposluszny.lolification.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.domain.Player;
import com.mposluszny.lolification.rest.dto.PlayerDto;
import com.mposluszny.lolification.rest.dto.builders.PlayerDtoBuilder;

@Path(value="api/players")
public class PlayersServiceResource {

	@Inject
	PlayerDao playerDao;
	
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
	
	@GET
	@Path("/id/{idPlayer}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public PlayerDto getPlayerById(@PathParam("idPlayer") long idPlayer) {
		return new PlayerDtoBuilder(playerDao.getPlayerById(idPlayer)).build();
	}
	
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
}
