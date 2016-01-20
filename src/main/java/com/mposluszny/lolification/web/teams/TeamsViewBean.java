package com.mposluszny.lolification.web.teams;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mposluszny.lolification.commons.BaseViewBean;
import com.mposluszny.lolification.core.dao.TeamDao;
import com.mposluszny.lolification.core.domain.Team;

@SessionScoped
@Named(value="teamsViewBean")
public class TeamsViewBean extends BaseViewBean<Team> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3849413342551126280L;

	/**
	 * Bean for 'teams' section.
	 * Contains a Player list
	 **/

	@Inject
	TeamDao teamDao;
			
	private boolean readonly = true;

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	@Override
	public List<Team> getAll() {
		return teamDao.getAllTeams();
	}

	@Override
	public Team getById(long id) {
		return teamDao.getTeamById(id);
	}

	@Override
	public void add(Team object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Team object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Team object) {
		// TODO Auto-generated method stub
		
	}
}
