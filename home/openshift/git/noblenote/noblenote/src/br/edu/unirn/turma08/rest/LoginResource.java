package br.edu.unirn.turma08.rest;

import javax.ws.rs.Path;

@Path("/login")
public class LoginResource {
/*
	private Map<String, Usuario> usuarios;

	public LoginResource() {

		usuarios = new HashMap<String, Usuario>();

		usuarios.put("usuario1", new Usuario(1, "usuario1", "Usuario 1",
				"padrao"));
		usuarios.put("usuario2", new Usuario(2, "usuario2", "Usuario 2",
				"padrao"));
		usuarios.put("usuario3", new Usuario(3, "usuario3", "Usuario 3",
				"padrao"));
		usuarios.put("usuario4", new Usuario(4, "usuario4", "Usuario 4",
				"padrao"));
	}

	@GET
	@Produces("application/json")
	public Response logar(@QueryParam("usuario") String login,@QueryParam("senha") String senha) {

		Response response = Response.status(Response.Status.FORBIDDEN).build();

		Usuario usuario = usuarios.get(login);

		if (usuario != null && usuario.getSenha().equals(senha)) {
			response = Response.status(Response.Status.OK).entity(usuario)
					.build();
		}
		
		return response;
	}
*/
}