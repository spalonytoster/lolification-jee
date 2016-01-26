package com.mposluszny.lolification.core.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.domain.Player;
import com.mposluszny.lolification.core.domain.Team;

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
		Player beforeUpdate = getEntityManager().find(Player.class, player.getIdPlayer());
		Team team = getEntityManager().find(Team.class, beforeUpdate.getTeam().getIdTeam());
		Team destination = getEntityManager().find(Team.class, player.getTeam().getIdTeam());
		boolean found = false;
		
		if (StringUtils.equals(player.getTeam().getName(), team.getName())) {
			for (int i = 0; i < team.getPlayers().size() && !found; i++) {
				if (team.getPlayers().get(i).getIdPlayer() == player.getIdPlayer()) {
					team.getPlayers().remove(i);
					team.getPlayers().add(player);
					getEntityManager().merge(team);
					found = true;
				}
			}
		}
		else {
			for (int i = 0; i < team.getPlayers().size() && !found; i++) {
				if (team.getPlayers().get(i).getIdPlayer() == player.getIdPlayer()) {
					team.getPlayers().remove(i);
					getEntityManager().merge(team);
				}
			}
			player.setTeam(destination);
			destination.getPlayers().add(player);
			getEntityManager().merge(destination);
		}

		player.setTeam(destination);
		getEntityManager().merge(player);
	}

	@Override
	public void addPlayer(Player player) {
		if (player.getTeam() != null &&
				player.getTeam().getIdTeam() != 0L) {
			Team team = getEntityManager().find(Team.class, player.getTeam().getIdTeam());
			team.getPlayers().add(player);
			player.setTeam(team);
		}
		else {
			player.setTeam(null);
		}
		getEntityManager().persist(player);
	}

	@Override
	public void deletePlayer(Player player) {
		player = getEntityManager().find(Player.class, player.getIdPlayer());
		removeFromTeam(player);
		getEntityManager().remove(player);
	}
	
	@Override
	public void removeFromTeam(Player player) {
		Team team = entityManager.find(Team.class, player.getTeam().getIdTeam());
		boolean found = false;
		for (int i = 0; i < team.getPlayers().size() && !found; i++) {
			if (team.getPlayers().get(i).getIdPlayer() == player.getIdPlayer()) {
				team.getPlayers().remove(i);
				found = true;
			}
		}
		player.setTeam(null);
		entityManager.merge(team);
		entityManager.merge(player);
	}

	@Override
	public int count() {
//		return ((Number) getEntityManager().getCurrentSession()
//				.createCriteria(Player.class).setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return (int) entityManager.createQuery("select count(p) from Player p").getSingleResult();
	}

}
