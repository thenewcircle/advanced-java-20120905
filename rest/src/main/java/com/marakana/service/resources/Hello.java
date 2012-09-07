package com.marakana.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class Hello {

	@GET
	public String sayHello() {
		return "Hello.";
	}

}
