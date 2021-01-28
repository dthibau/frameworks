package org.formation.service.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.formation.model.Member;
import org.formation.service.UserDocumentServiceLocal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/members")
@Api(value = "/api/members", description = "User document service")
public class UserDocumentServiceRest {

	@EJB
	UserDocumentServiceLocal userDocumentService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Register a new member of the service", notes = "Parameter must be a complete member structure. The member containing its id is returned", response = Member.class)
	public Response registerMember(@ApiParam(value = "Updated user object", required = true) Member member) {

		Response.ResponseBuilder builder = null;

		try {
			member = userDocumentService.registerUser(member);
			// Create an "ok" response with the persisted contact
			builder = Response.ok(member);
		} catch (Exception e) {
			// Handle generic exceptions
			builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
		}

		return builder.build();
	}

	@POST
	@Path("autenticate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Authenticate a member", notes = "Authenticate a member", response = Member.class)
	public Response authenticate(
			@ApiParam(value = "login,password encapsulated", required = true) PrincipalDto principals) {

		try {
			Member member = new Member();
			member.setEmail(principals.getLogin());
			member.setPassword(principals.getPassword());
			member = userDocumentService.authenticate(member);
			return Response.ok(member).build();
		} catch (Exception e) {
			// Handle generic exceptions
			// builder =
			return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
		}

	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve a member via its Id", response = Member.class)
	public Response getMember(
			@PathParam("id") long id) {

		Response.ResponseBuilder builder = null;

		try {
			Member member = new Member();
			member = userDocumentService.getById(id);
			return Response.ok(member).build();
		} catch (Exception e) {
			// Handle generic exceptions
			// builder =
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

	}


	@GET
	@Produces(MediaType.TEXT_HTML)
	@ApiOperation(value = "Just proove that you hit the service", notes = "Return HTML", response = String.class)
	public String getHome() {
		return "<html><body><h1>Hello you really Hit me</h1></body></html>";
	}
}
