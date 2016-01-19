package com.mposluszny.lolification.web.players;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.domain.Player;

@ManagedBean
@SessionScoped
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
		return playerDao.getAllPlayers();
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
}
