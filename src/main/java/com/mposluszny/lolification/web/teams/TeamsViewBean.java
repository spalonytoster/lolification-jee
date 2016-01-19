package com.mposluszny.lolification.web.teams;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.mposluszny.lolification.core.dao.TeamDao;
import com.mposluszny.lolification.core.domain.Team;

@ManagedBean
@SessionScoped
public class TeamsViewBean implements Serializable {
	/**
	 * Bean for 'teams' section.
	 * Contains a Player list
	 **/
	private static final long serialVersionUID = 1551523865258549328L;

	@Inject
	TeamDao teamDao;
	
	private List<Team> teams;
		
	private boolean readonly = true;

	public List<Team> getTeams() {
		return teamDao.getAllTeams();
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
}
