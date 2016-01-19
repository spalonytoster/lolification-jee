package com.mposluszny.lolification.core.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.domain.Player;

@Stateless
public class PlayerDaoImpl implements PlayerDao {

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayers() {
		return getEntityManager().createNamedQuery("player.all").getResultList();
	}

	@Override
	public Player getPlayerById(long idPlayer) {
		return getEntityManager().find(Player.class, idPlayer);
	}

	@Override
	public Player getPlayerByIgn(String ign) {
		return (Player) getEntityManager().createNamedQuery("player.byIgn")
				.setParameter("ign", ign)
				.getSingleResult();
	}

	@Override
	public void updatePlayer(Player player) {
		//getEntityManager().getCurrentSession().update(player);
		getEntityManager().merge(player);
	}

	@Override
	public void addPlayer(Player player) {
		getEntityManager().persist(player);
	}

	@Override
	public void deletePlayer(Player player) {
		getEntityManager().remove(player);
	}

	@Override
	public int count() {
//		return ((Number) getEntityManager().getCurrentSession()
//				.createCriteria(Player.class).setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return (int) entityManager.createQuery("select count(p) from Player p").getSingleResult();
	}

}
