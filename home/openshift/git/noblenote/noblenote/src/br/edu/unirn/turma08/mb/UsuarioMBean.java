package br.edu.unirn.turma08.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.edu.unirn.turma08.modelo.Usuario;
import br.edu.unirn.turma08.rest.UsuarioLista;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


@ManagedBean
public class UsuarioMBean {

	private List<Usuario> usuarios = UsuarioLista.getUsuarios();


	public String cadastrarUsuario(Usuario usuario){
		
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/usuario/cadastro");
	    String json = wr.get(String.class);
	    Gson gson = new Gson();
	    //return gson.fromJson(json, new TypeToken<List<Usuario>>(){}.getType());
	    Usuario u = (Usuario)gson.fromJson(json, new TypeToken<Usuario>(){}.getType());
	    this.usuarios.add(u);
	    
	    return "";
	    
	}
	
	public List<Usuario> listarUsuarios(){
		return UsuarioLista.getUsuarios();
	}
	
}
