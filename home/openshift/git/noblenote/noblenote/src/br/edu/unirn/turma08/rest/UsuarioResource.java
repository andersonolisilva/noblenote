package br.edu.unirn.turma08.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.edu.unirn.turma08.modelo.Usuario;
import br.edu.unirn.turma08.noblenote.dao.UsuarioDAO;

@Path("/usuario")
public class UsuarioResource {

	UsuarioDAO dao = new UsuarioDAO();

	@GET
	@Path("/hello")
	@Produces("text/plain")
	public String hello() {
		return "Hello World";
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

			List<Usuario> usuarios = dao.findAll();

			for (Usuario usuario2 : usuarios) {
				if (usuario2.equals(usuario)) {
					response = Response.status(Response.Status.OK)
							.entity(usuario2).build();
				}
			}

		}

		return response;
	}

	@POST
	@Produces("application/json")
	@Path("/cadastro")
	public Usuario cadastro(Usuario usuario) {

		dao.create(usuario);

		return usuario;
	}

	@GET
	@Produces("application/json")
	@Path("/alterarsenha")
	public Usuario alterarSenha(@QueryParam("login") String login,
			@QueryParam("senha") String senha,
			@QueryParam("novasenha") String novaSenha) {

		return null;
	}

	@GET
	@Produces("application/json")
	@Path("/recuperarsenha")
	public Usuario recuperarSenha(@QueryParam("login") String login,
			@QueryParam("numerotelefone") String numeroTelefone) {

		return null;
	}

	@GET
	@Produces("application/json")
	@Path("/listarusuarios")
	public List<Usuario> listUsuarios(){
		return new ArrayList<Usuario>(dao.findAll());
	}

	@GET
	@Produces("application/json")
	@Path("/id")
	public Response findById(@QueryParam("id") String id) {

		Response response = null;

		Usuario usuario = dao.findByPrimaryKey(Integer.parseInt(id));

		if (usuario != null) {
			response = Response.status(Response.Status.OK).entity(usuario)
					.build();
		} else {
			response = Response.status(Response.Status.FORBIDDEN).build();
		}

		return response;

	}

}
