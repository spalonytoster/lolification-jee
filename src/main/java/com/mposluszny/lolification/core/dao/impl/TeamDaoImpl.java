package com.mposluszny.lolification.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mposluszny.lolification.core.dao.TeamDao;
import com.mposluszny.lolification.core.domain.Player;
import com.mposluszny.lolification.core.domain.Team;

@Stateless
public class TeamDaoImpl implements TeamDao {
	
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
	public List<Team> getAllTeams() {
		return getEntityManager().createNamedQuery("team.all").getResultList();
	}

	@Override
	public Team getTeamById(long idTeam) {
		return (Team) getEntityManager().find(Team.class, idTeam);
	}

	@Override
	public Team getTeamByName(String name) {
		return (Team) getEntityManager().createNamedQuery("team.byName")
				.setParameter("name", name).getSingleResult();
	}

	@Override
	public void updateTeam(Team Team) {
		getEntityManager().merge(Team);
	}

	@Override
	public void addTeam(Team team) {
		getEntityManager().persist(team);
	}

	@Override
	public void deleteTeam(Team team) {
		team = (Team) getEntityManager()
				.find(Team.class, team.getIdTeam());
		
		if (team.getPlayers() != null) {
			for (Player player : team.getPlayers()) {
				player.setTeam(null);
			}
		}
		
		getEntityManager().remove(team);
	}

	@Override
	public int count() {
//		return ((Number) getEntityManager().getCurrentSession()
//				.createCriteria(Team.class).setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return (int) entityManager.createQuery("select count (t) from Team t").getSingleResult();
	}

	@Override
	public List<Player> getPlayersForTeam(Team team) {
		team = (Team) getEntityManager()
				.find(Team.class, team.getIdTeam());
		
		List<Player> players = new ArrayList<Player>(team.getPlayers());
		return players;
	}

}
