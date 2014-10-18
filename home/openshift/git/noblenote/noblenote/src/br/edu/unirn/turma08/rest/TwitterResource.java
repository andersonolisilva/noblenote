package br.edu.unirn.turma08.rest;

import javax.ws.rs.Path;

@Path("/tweets")
public class TwitterResource {
/*
	private Map<String, Map<Integer,Tweet>> tweets;
	
	public TwitterResource() {
		
		
		tweets = new HashMap<String, Map<Integer,Tweet>>();
		
		Map<Integer,Tweet> tweetsUsuario = new HashMap<Integer,Tweet>();
		
		Usuario usuario = new Usuario(1, "usuario1", "Usuario 1","padrao","usuario1@gmail,com","2233433");
		
		tweetsUsuario.put(1, new Tweet(1,"Tweet 1",usuario));
		
		tweets.put("usuario1", tweetsUsuario);
	}

	@GET
	@Produces("application/json")
	@Path("/{login}")
	public Response getAll(@PathParam("login") String loginUsuario) {
		
		Map<Integer,Tweet> tweetsUsuario = tweets.get(loginUsuario);
		Response response = null;
		
		if(tweetsUsuario != null){
			response = Response.status(Response.Status.OK).entity(tweetsUsuario.values()).build();
		}
		else{
			response = Response.status(Response.Status.NO_CONTENT).build();
			
		}
		
		return response;

	}

	@GET
	@Path("/{login}/{id}")
	@Produces({"application/json","text/xml"})
	public Response get(@PathParam("login") String loginUsuario,@PathParam("id") String id) {
		Response response = null;
		
		Map<Integer,Tweet> tweetsUsuario = tweets.get(loginUsuario);
		Tweet tweet = null;
		
		if(tweetsUsuario != null){
			tweet = tweetsUsuario.get(Integer.parseInt(id));
		}
		

		if (tweet != null) {
			response = Response.status(Response.Status.OK).entity(tweet).build();
		}
		else{
			response = Response.status(Response.Status.NOT_FOUND).build();
		}

		return response;
	}



	@POST
	@Path("/{login}/{id}")
	@Consumes({"application/json","text/xml"})
	public void cadastrar(Tweet tweet,@PathParam("login") String loginUsuario) {

		Map<Integer,Tweet> tweetsUsuario = tweets.get(loginUsuario);
		
		if(tweetsUsuario == null){
			tweetsUsuario = new HashMap<Integer, Tweet>();
			tweets.put(loginUsuario, tweetsUsuario);
		}
		
		tweetsUsuario.put(tweet.getId(), tweet);
	}
	
	@POST
	@Path("/{login}")
	@Consumes({"application/json","text/xml"})
	public void cadastrar(Tweet[] tweetsNovos,@PathParam("login") String loginUsuario) {
		Map<Integer,Tweet> tweetsUsuario = tweets.get(loginUsuario);
		
		if(tweetsUsuario == null){
			tweetsUsuario = new HashMap<Integer, Tweet>();
			tweets.put(loginUsuario, tweetsUsuario);
		}
		
		for(Tweet tweet:tweetsNovos){
			tweetsUsuario.put(tweet.getId(), tweet);
		}
	}
	
	


*/
}