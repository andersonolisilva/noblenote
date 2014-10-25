package br.edu.unirn.turma08.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.edu.unirn.turma08.modelo.Usuario;
import br.edu.unirn.turma08.noblenote.dao.UsuarioDAO;
import br.edu.unirn.turma08.rest.UsuarioResourceService;

@ManagedBean
public class UsuarioMBean {

	UsuarioDAO dao = new UsuarioDAO();

	private Usuario usuarioSelecionado = new Usuario();

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public String cadastrarUsuario() {

		/*
		 * Client c = Client.create(); WebResource wr =
		 * c.resource("http://localhost:8080/usuario/cadastro"); String json =
		 * wr.get(String.class); Gson gson = new Gson(); //return
		 * gson.fromJson(json, new TypeToken<List<Usuario>>(){}.getType());
		 * Usuario u = (Usuario)gson.fromJson(json, new
		 * TypeToken<Usuario>(){}.getType()); dao.create(u);
		 */

		dao.create(usuarioSelecionado);
		usuarioSelecionado = new Usuario();

		return "";

	}

	public List<Usuario> listarUsuarios() {

		List<Usuario> usuarios = new ArrayList<Usuario>();
	
		usuarios = dao.findAll();
		
		return usuarios;
		
	}
	
	public void converterObjetoUsuarioEmJson(){
		UsuarioResourceService service = new UsuarioResourceService();
		service.converterJsonEmObjetoUsuario();
	}
	
	public String recuperarSenha(Usuario usuario){
		UsuarioResourceService service = new UsuarioResourceService();
		service.recuperarSenha(usuario);
		return "";
	}
}
