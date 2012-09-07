package com.marakana.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.marakana.service.representations.Person;

@Path("/hello")
public class Hello {

	@GET
	@Path("{first}/{last}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person sayHello(
			@PathParam("first") String first,
			@PathParam("last") String last) {
		return new Person(first, last);
	}

}
