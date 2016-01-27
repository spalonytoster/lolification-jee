package com.mposluszny.lolification.rest;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.ResponseSpecification;
import com.mposluszny.lolification.rest.dto.PlayerDto;

public class PlayerServiceResourceTest {
	
	private final String PATH_API = "/lolification-jee/api";
	private final String PATH_PLAYERS_GET_ALL = PATH_API + "/players";
	private final String PATH_PLAYERS_GET_BY_ID = PATH_API + "/players/id/";
	private final String PATH_PLAYERS_GET_BY_IGN = PATH_API + "/players/ign/";
	
	@Test
	public void getPlayersTest() {
		
		PlayerDto[] players = expect().statusCode(200)
								.then().get(PATH_PLAYERS_GET_ALL).as(PlayerDto[].class);
		assertEquals(players.length, 16);
	}
	
	@Test
	public void getPlayerByIdTest() {
		
		 expect().
		    statusCode(200).
		    body(
		      "idPlayer", equalTo(1),
		      "name", equalTo("Jason"),
		      "surname", equalTo("Tran"),
		      "ign", equalTo("Wildturtle"),
		      "role", equalTo("AD"),
		      "team", equalTo("TSM"),
		      "retired", equalTo(false)).
		    when().
		    get(PATH_PLAYERS_GET_BY_ID + 1);
	}
	
	@Test
	public void getPlayerByIgnTest() {
		
		 expect().
		    statusCode(200).
		    body(
		      "idPlayer", equalTo(1),
		      "name", equalTo("Jason"),
		      "surname", equalTo("Tran"),
		      "ign", equalTo("Wildturtle"),
		      "role", equalTo("AD"),
		      "team", equalTo("TSM"),
		      "retired", equalTo(false)).
		    when().
		    get(PATH_PLAYERS_GET_BY_IGN + "Wildturtle");
	}
}
