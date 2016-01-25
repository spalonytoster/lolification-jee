package com.mposluszny.lolification.web.teams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.dao.TeamDao;
import com.mposluszny.lolification.core.domain.Player;
import com.mposluszny.lolification.core.domain.Team;
import com.mposluszny.lolification.core.domain.builder.TeamBuilder;

@SessionScoped
@Named(value="teamsViewBean")
public class TeamsViewBean implements Serializable {
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
	@Inject
	PlayerDao playerDao;
	
	private List<Team> teams;
	private Team team;
	private boolean readonly;

	private String name;
	private String region;
	private String dateOfEstablishment;
	
	public String goToTeam() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> params = context.getExternalContext().getRequestParameterMap();
		String action = params.get("action");
		
		if (action.equalsIgnoreCase("view")) {
			this.readonly = true;
		}
		else if (action.equalsIgnoreCase("edit")) {
			this.readonly = false;
		}
		
		team = teamDao.getTeamById(Long.parseLong(params.get("id")));
		team.setPlayers(teamDao.getPlayersForTeam(team));
		
		return "team" + "?faces-redirect=true";
	}

	public boolean isReadonly() {
		return readonly;
	}

	public String removePlayerFromTeam() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> params = context.getExternalContext().getRequestParameterMap();
		
		Player player = playerDao.getPlayerById(Long.parseLong(params.get("id")));
		playerDao.removeFromTeam(player);
		
		List<Player> players = teamDao.getPlayersForTeam(this.team);
		this.team.setPlayers(players);
		
		return "team";
	}
	
	public void createTeam() {
		teamDao.addTeam(new TeamBuilder()
							.name(name)
							.region(region)
							.dateOfEstablishment(dateOfEstablishment)
							.build());
		teams = teamDao.getAllTeams();
	}
	
	public String editTeam() {
		teamDao.updateTeam(team);
		teams = teamDao.getAllTeams();
		return "teams" + "?faces-redirect=true";
	}

	public Team getTeam() {
//		this.team = teamDao.getTeamById(this.team.getIdTeam());
//		this.team.setPlayers(teamDao.getPlayersForTeam(this.team));
		return team;
	}

	public List<Team> getTeams() {
		if (teams == null) {
			teams = teamDao.getAllTeams();
		}
		return teams;
	}
	
	public void deleteTeam(Team team) {
		teamDao.deleteTeam(team);
		teams = teamDao.getAllTeams();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDateOfEstablishment() {
		return dateOfEstablishment;
	}

	public void setDateOfEstablishment(String dateOfEstablishment) {
		this.dateOfEstablishment = dateOfEstablishment;
	}

}
