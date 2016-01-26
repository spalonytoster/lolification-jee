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
import com.mposluszny.lolification.rest.dto.TeamDto;

public class TeamServiceResourceTest {
	
	private final String PATH_API = "/lolification-jee/api";
	private final String PATH_TEAMS_GET_ALL = PATH_API + "/teams";
	private final String PATH_TEAMS_GET_BY_ID = PATH_API + "/teams/id/";
	
	@Test
	public void getTeamsTest() {
		
		TeamDto[] teams = expect().statusCode(200)
								.then().get(PATH_TEAMS_GET_ALL).as(TeamDto[].class);
		assertEquals(teams.length, 3);
	}
	
	@Test
	public void getTeamByIdTest() {
		
		 expect().
		    statusCode(200).
		    body(
		      "idTeam", equalTo(1),
		      "name", equalTo("TSM"),
		      "region", equalTo("NA"),
		      "dateOfEstablishment", equalTo("2011-01-01")).
		    when().
		    get(PATH_TEAMS_GET_BY_ID + 1);
	}

}
