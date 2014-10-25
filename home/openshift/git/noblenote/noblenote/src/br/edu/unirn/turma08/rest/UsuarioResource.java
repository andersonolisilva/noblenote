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
		return "Teste para Ok com WebService";
	}

	/*
	 * Acertado com Plecyo
	 * */
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

			Usuario usua = dao.logar(usuario);
			if(usua!=null){
				response = Response.status(Response.Status.OK)
								.entity(usua).build();
			}
			
		}

		return response;
	}

	/*
	 * Acertado com Plecyo
	 * */
	@POST
	@Produces("application/json")
	@Path("/cadastro")
	public Response cadastro(Usuario usuario) {
		
		Response response = Response.status(Response.Status.OK).build();
		try{
			dao.create(usuario);
			response = Response.status(Response.Status.OK).entity(usuario)
					   .build();
		}catch(Exception e){
		}

		return response;
	}

	/*
	 * Acertado com Plecyo
	 * */
	@GET
	@Produces("application/json")
	@Path("/alterarsenha")
	public Response alterarSenha( @QueryParam("login") String login,
			@QueryParam("senha") String senha,
			@QueryParam("novasenha") String novaSenha) {

		Response response = Response.status(Response.Status.OK).build();
		
		Usuario usuario = new Usuario();
		usuario.setSenha(senha);
		usuario.setLogin(login);
		
		Usuario usuarioLogado = dao.logar(usuario);
		if (usuarioLogado != null){
			usuarioLogado.setSenha(novaSenha);
			dao.update(usuarioLogado);
			response = Response.status(Response.Status.OK).entity(usuarioLogado)
					.build();
		}
		
		return response;
	}

	/*
	 * Acertado com Plecyo
	 * */
	@GET
	@Produces("application/json")
	@Path("/recuperarsenha")
	public Response recuperarSenha(Usuario usuario) {
		Response response = Response.status(Response.Status.OK).build();
		
		if (!( "".equals(usuario.getLogin()) || "".equals(usuario.getNumeroTelefone()) ) ){
			usuario = dao.recuperarSenha(usuario);
			response = Response.status(Response.Status.OK).entity(usuario)
					.build();
		}
		return response;
	}

	/**
	 * Codificação extra
	 * @return
	 */
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
