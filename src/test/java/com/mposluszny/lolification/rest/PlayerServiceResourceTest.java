package com.mposluszny.lolification.rest;

import javax.inject.Inject;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.dao.TeamDao;

public class PlayerServiceResourceTest {
	
	@Inject
	PlayerDao playerDao;
	@Inject
	TeamDao teamDao;

/*	@Test
	public void getPlayerByIdTest() {
		
		if (playerDao.getAllPlayers().size() > 0) {
			Player player = playerDao.getPlayerById(1);
			get("api/players/player/1").then().assertThat().body("ign", is(player.getIgn()));
		}
		
	}*/
}
