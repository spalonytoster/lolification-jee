package com.mposluszny.lolification.rest;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static com.jayway.restassured.http.ContentType.JSON;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.ResponseSpecification;
import com.mposluszny.lolification.rest.dto.PlayerDto;
import com.mposluszny.lolification.rest.dto.TeamDto;
import com.mposluszny.lolification.rest.dto.builders.TeamDtoBuilder;

public class TeamServiceResourceTest {
	
	private final String PATH_API = "/lolification-jee/api";
	private final String PATH_TEAMS_ALL = PATH_API + "/teams";
	private final String PATH_TEAMS_BY_ID = PATH_API + "/teams/id/";
	private final String PATH_TEAMS_BY_NAME = PATH_API + "/teams/name/";
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void createTeamTest() throws IOException {
		
		TeamDto team = new TeamDtoBuilder()
						.name("Kaboom")
						.region("BR")
						.dateOfEstablishment("2010-10-10").
							build();
		
		String json = mapper.writeValueAsString(team);

		
		given().
	        body(json).
	        with().
	        contentType(JSON).
		expect().
		        statusCode(201).
		when().
		        post(PATH_TEAMS_ALL);
	}
	
	
	@Test
	public void getTeamsTest() {
		
		TeamDto[] teams = expect().statusCode(200)
								.then().get(PATH_TEAMS_ALL).as(TeamDto[].class);
		assertEquals(teams.length, 4);
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
		    get(PATH_TEAMS_BY_ID + 1);
	}
	
	@Test
	public void deleteTeamTest() {
		
		expect().
		        statusCode(202).
		when().
		        delete(PATH_TEAMS_BY_NAME + "Kaboom");
	}
	

}
