package com.mposluszny.lolification.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value="api")
public class MainServiceResource {

	@GET
	@Path(value="/")
	@Produces(value=MediaType.TEXT_HTML)
	public String helloMessage() {
		return "REST services are running. Enjoy.";
	}
}
