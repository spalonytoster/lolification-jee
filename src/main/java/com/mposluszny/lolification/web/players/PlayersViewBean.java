package com.mposluszny.lolification.web.players;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.domain.Player;

@ViewScoped
@Named(value="playersViewBean")
public class PlayersViewBean implements Serializable {

	/**
	 * Bean for 'players' section.
	 * Contains a Player list
	 */
	
	@Inject
	PlayerDao playerDao;
	
	private List<Player> players;
		
	private static final long serialVersionUID = 1551523865258549328L;

	public List<Player> getPlayers() {
		if (players == null) {
			players = playerDao.getAllPlayers();
		}
		return players;
	}
	
}
