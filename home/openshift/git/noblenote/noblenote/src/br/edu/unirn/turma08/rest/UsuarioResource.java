package br.edu.unirn.turma08.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.edu.unirn.turma08.modelo.Usuario;

@Path("/usuario")
public class UsuarioResource {

	private static List<Usuario> usuarios = UsuarioLista.getUsuarios();

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(List<Usuario> usuarios) {
		UsuarioResource.usuarios = usuarios;
	}

	@GET
	@Produces("application/json")
	@Path("/logar")
	public Response logar(@QueryParam("login") String login,
			@QueryParam("senha") String senha) {

		Response response = Response.status(Response.Status.OK).build();

		if (!login.equals("") && !senha.equals("")) {

			Usuario usuario = new Usuario();
			usuario.setLogin(login);
			usuario.setSenha(senha);
			
			for (Usuario usuario2 : usuarios) {
				if (usuario2.equals(usuario)) {
					response = Response.status(Response.Status.OK).entity(usuario2)
							.build();
				}
			}

		}

		return response;
	}

	@POST
	@Produces("application/json")
	@Path("/cadastro")
	/*public Usuario cadastro(@QueryParam("login") String login,
			@QueryParam("senha") String senha, @QueryParam("nome") String nome,
			@QueryParam("numeroTelefone") String numeroTelefone) {*/
	public Usuario cadastro(Usuario usuario){

		usuarios.add(usuario);
		
      	return usuario;
	}
	
	@GET
	@Produces("application/json")
	@Path("/alterarsenha")
	public Usuario alterarSenha(@QueryParam("login") String login,
			@QueryParam("senha") String senha, @QueryParam("novasenha") String novaSenha){
		
		return null;
	}
	
	@GET
	@Produces("application/json")
	@Path("/recuperarsenha")
	public Usuario recuperarSenha(@QueryParam("login") String login,
			@QueryParam("numerotelefone") String numeroTelefone){
		
		return null;
	}
	
}
