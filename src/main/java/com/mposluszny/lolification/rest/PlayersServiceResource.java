package com.mposluszny.lolification.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.domain.Player;

@Path(value="api/players")
public class PlayersServiceResource {

	@Inject
	PlayerDao playerDao;
	
	@GET
	@Path(value="/")
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Player> getAllPlayers() {
		return playerDao.getAllPlayers();
	}
}
