package com.mposluszny.lolification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mposluszny.lolification.core.dao.PlayerDao;
import com.mposluszny.lolification.core.dao.TeamDao;
import com.mposluszny.lolification.core.domain.Player;
import com.mposluszny.lolification.core.domain.Team;
import com.mposluszny.lolification.core.domain.builder.PlayerBuilder;
import com.mposluszny.lolification.core.domain.builder.TeamBuilder;

public class DAOTest {
	
	private Context ctx;
    private EJBContainer ejbContainer;
    
	TeamDao teamDao;
	PlayerDao playerDao;
    
   @BeforeClass
   public void setUp() {
       ejbContainer = EJBContainer.createEJBContainer();
       System.out.println("Opening the container" );
       ctx = ejbContainer.getContext();
   }
   
   @Before
   public void injectDao() throws NamingException {
	   teamDao = (TeamDao) ctx.lookup("java:global/classes/TeamDao");
	   playerDao = (PlayerDao) ctx.lookup("java:global/classes/PlayerDao");
   }

   @AfterClass
   public  void tearDown() {
       ejbContainer.close();
       System.out.println("Closing the container" );
   }
	
	@Test
	public void TestGetAll() {
		
		List<Team> teams = teamDao.getAllTeams();
		List<Player> players = playerDao.getAllPlayers();
		int teamCount = teamDao.count();
		int playerCount = playerDao.count();
		
		assertNotNull(teams);
		assertNotNull(players);
		assertTrue(teams.size() == teamCount);
		assertTrue(players.size() == playerCount);
	}
	
	@Test
	public void addTest() {
		
		/**
		 * Dodawanie z perspektywy Playera
		 */
		
		final String IGN = "xPeke";
		
		Team team1 = new TeamBuilder()
				.name("Origen")
				.region("EU")
				.dateOfEstablishment("2010-10-10")
					.build();
				
		Player player1 = new PlayerBuilder()
				.name("Enrique")
				.surname("Cedeño Martínez")
				.ign(IGN)
				.role("MID")
				.team(team1)
				.isRetired(false)
					.build();
		
		/**
		 * Dodawanie z perspektywy Team
		 */
		
		List<Player> team2Players = new ArrayList<>();
		
		Team team2 = new TeamBuilder()
				.name("Cloud9")
				.region("NA")
				.dateOfEstablishment("2010-09-09")
				.players(team2Players)
					.build();
		
		Player player2 = new PlayerBuilder()
				.name("Zachary")
				.surname("Scuderi")
				.ign("Sneaky")
				.role("ADC")
				.team(team2)
				.isRetired(false)
					.build();
		
		Player player3 = new PlayerBuilder()
				.name("Daerek")
				.surname("Hart")
				.ign("LemonNation")
				.role("Staff")
				.team(team2)
				.isRetired(false)
					.build();
						
		team2Players.add(player2);
		team2Players.add(player3);
		
		int beforePlayers = playerDao.count();
		playerDao.addPlayer(player1);
		int afterPlayers = playerDao.count();
		assertEquals(afterPlayers, beforePlayers+1);
		
		Player player = playerDao.getPlayerByIgn(IGN);
		assertNotNull(player);
		
		assertNotNull(player.getTeam());
		assertNotNull(teamDao.getTeamById(player.getTeam().getIdTeam()));
		
		int beforeTeams = teamDao.count();
		beforePlayers = playerDao.count();
		teamDao.addTeam(team2);
		int afterTeams = teamDao.count();
		afterPlayers = playerDao.count();
		assertEquals(afterTeams, beforeTeams+1);
		assertEquals(afterPlayers, beforePlayers+2);
	}
	
	@Test
	public void updateTest() {
		
		final String OLD_NAME = "Roccat";
		final String NEW_NAME = "Kiedys Mialem Team";

		Team team = teamDao.getTeamByName(OLD_NAME);		
		team.setName(NEW_NAME);
		teamDao.updateTeam(team);
		
		Team team2 = teamDao.getTeamByName(NEW_NAME);
		assertEquals(team.getIdTeam(), team2.getIdTeam());
		
		Player player = playerDao.getPlayerByIgn("Jankos");
		assertFalse(player.getRetired());
		player.setRetired(true);
		playerDao.updatePlayer(player);
		Player player2 = playerDao.getPlayerByIgn("Jankos");
		assertTrue(player2.getRetired());
	}
	
	@Test
	public void deleteTeamTest() {

		Team team = new TeamBuilder()
				.name("CLG")
				.region("NA")
				.dateOfEstablishment("2010-09-25")
				.players(new ArrayList<Player>())
					.build();
		
		Player player1 = new PlayerBuilder()
				.name("George")
				.surname("Georgallidis")
				.ign("HotshotGG")
				.role("President/Co-Founder")
				.team(team)
				.isRetired(false)
					.build();
		
		Player player2 = new PlayerBuilder()
				.name("Zaqueri")
				.surname("Black")
				.ign("Aphromoo")
				.role("Support")
				.team(team)
				.isRetired(false)
					.build();
		
		team.getPlayers().add(player1); team.getPlayers().add(player2);
		teamDao.addTeam(team);
		playerDao.addPlayer(player1); playerDao.addPlayer(player2);
		
		player1 = playerDao.getPlayerByIgn(player1.getIgn());
		player2 = playerDao.getPlayerByIgn(player2.getIgn());
		team = teamDao.getTeamByName("CLG");
		
		int playerCountBefore, teamCountBefore, playerCountAfter, teamCountAfter;
		
		playerCountBefore = playerDao.count();
		teamCountBefore = teamDao.count();
		
		teamDao.deleteTeam(team);
		teamCountAfter= teamDao.count();
		playerCountAfter = playerDao.count();
		
		assertTrue(teamCountBefore == teamCountAfter+1);
		assertTrue(playerCountBefore == playerCountAfter);
		assertNull(playerDao.getPlayerByIgn(player1.getIgn()).getTeam());
		assertNull(playerDao.getPlayerByIgn(player2.getIgn()).getTeam());
		
		playerCountBefore = playerDao.count();
		playerDao.deletePlayer(player1);
		playerDao.deletePlayer(player2);
		playerCountAfter = playerDao.count();
		assertTrue(playerCountBefore == playerCountAfter+2);
	}
	
	@Test
	public void deletePlayerTest() {

		Team team = new TeamBuilder()
				.name("CLG")
				.region("NA")
				.dateOfEstablishment("2010-09-25")
				.players(new ArrayList<Player>())
					.build();
		
		Player player1 = new PlayerBuilder()
				.name("George")
				.surname("Georgallidis")
				.ign("HotshotGG")
				.role("President/Co-Founder")
				.team(team)
				.isRetired(false)
					.build();
		
		Player player2 = new PlayerBuilder()
				.name("Zaqueri")
				.surname("Black")
				.ign("Aphromoo")
				.role("Support")
				.team(team)
				.isRetired(false)
					.build();
		
		team.getPlayers().add(player1); team.getPlayers().add(player2);
		teamDao.addTeam(team);
		playerDao.addPlayer(player1); playerDao.addPlayer(player2);
		
		player1 = playerDao.getPlayerByIgn(player1.getIgn());
		player2 = playerDao.getPlayerByIgn(player2.getIgn());
		team = teamDao.getTeamByName("CLG");
		
		int playerCountBefore, teamCountBefore, playerCountAfter, teamCountAfter;
		
		teamCountBefore = teamDao.count();
		playerCountBefore = playerDao.count();
		playerDao.deletePlayer(player1);
		playerDao.deletePlayer(player2);
		playerCountAfter = playerDao.count();
		teamCountAfter = teamDao.count();
		
		assertTrue(playerCountBefore == playerCountAfter+2);
		assertTrue(teamCountBefore == teamCountAfter);
		
		assertNotNull(teamDao.getTeamByName("CLG"));
	}
	
	
	/**
	 *	Podobnie jak w deleteTeamTest i deletePlayerTest tylko, że zapisywane obiekty nie posiadają referencji do siebie.
	 *	Naszym zadaniem jest powiązać te obiekty w bazie za pomocą usługi `transferPlayer`.
	 */
	
	@Test
	public void transferPlayerTest() {
		
		Team team1 = new TeamBuilder()
				.name("CLG")
				.region("NA")
				.dateOfEstablishment("2010-09-25")
					.build();
		
		Team team2 = new TeamBuilder()
				.name("Elements")
				.region("EU")
				.dateOfEstablishment("2013-05-17")
				.players(new ArrayList<Player>())
					.build();
		
		Player player = new PlayerBuilder()
				.name("George")
				.surname("Georgallidis")
				.ign("HotshotGG")
				.role("President/Co-Founder")
				.team(team2)
				.isRetired(false)
					.build();
		team2.getPlayers().add(player);
		
		teamDao.addTeam(team1); teamDao.addTeam(team2);
		playerDao.addPlayer(player);
		
		player = playerDao.getPlayerByIgn(player.getIgn());
		team1 = teamDao.getTeamByName(team1.getName());
		
		assertNull(team1.getPlayers());
		
		playerDao.transferPlayer(player.getIdPlayer(), team1.getIdTeam());
		player = playerDao.getPlayerByIgn(player.getIgn());
		team1 = teamDao.getTeamByName(team1.getName());
		
		assertEquals(player.getTeam(), team1);
		assertTrue(team1.getPlayers().contains(player));
		
	}

}
