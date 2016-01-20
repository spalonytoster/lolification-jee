package com.mposluszny.lolification.web.players;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mposluszny.lolification.commons.BaseViewBean;
import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.domain.Player;

@SessionScoped
@Named(value="playersViewBean")
public class PlayersViewBean extends BaseViewBean<Player> {

	/**
	 * Bean for 'players' section.
	 * Contains a Player list
	 */
	
	@Inject
	PlayerDao playerDao;
		
	private static final long serialVersionUID = 1551523865258549328L;

	@Override
	public List<Player> getAll() {
		return playerDao.getAllPlayers();
	}

	@Override
	public Player getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Player object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Player object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Player object) {
		// TODO Auto-generated method stub
		
	}
	
}
