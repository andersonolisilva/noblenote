package br.edu.unirn.turma08.mb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import br.edu.unirn.turma08.modelo.Usuario;
import br.edu.unirn.turma08.noblenote.dao.UsuarioDAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

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
		/*
		 * Usuario usuario = dao.findByPrimaryKey(1); GsonBuilder builder = new
		 * GsonBuilder(); builder.setDateFormat("dd/MM/yyyy"); Gson g =
		 * builder.create(); String json = g.toJson(usuario);
		 * System.out.println(json);
		 */
		/*
		 * String json =
		 * "{\"nome_completo\":\"Almiro Fagundes\",\"idade\":15,\"data_nascimento\":\"04/08/2012\",\"irmaos\":[\"Marilde Louzato\",\"João Fagundes\"]}"
		 * ; GsonBuilder builder2 = new GsonBuilder();
		 * builder.setDateFormat("dd/MM/yyyy"); Gson g2 = builder.create();
		 * Pessoa p = g.fromJson(json, Pessoa.class);
		 * System.out.println("Nome: " + p.getNome());
		 * System.out.println("Idade: " + p.getIdade());
		 * System.out.println("Data Nascimento: " + p.getDataNascimento());
		 * System.out.println("Irmãos: " + p.getIrmaos().toString());
		 */

		try {

			ClientRequest request = new ClientRequest(
					"http://localhost:8080/noblenote/rest/usuario/listarusuarios");
			request.accept("application/json");
			ClientResponse<String> response = request.get(String.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String json = response.getEntity().toString();
			JsonParser parser = new JsonParser();
        	JsonElement element = parser.parse(json);
        	JsonArray jasonArray = element.getAsJsonArray();
        	
        	for (JsonElement object : jasonArray) {
        		
        		System.out.println(" name is --"+object.toString());
        		GsonBuilder builder2 = new GsonBuilder();
       		 	builder2.setDateFormat("dd/MM/yyyy"); 
       		 	Gson g2 = builder2.create();
       		 	Usuario u = g2.fromJson(object, Usuario.class);
       		 	
       		 	usuarios.add(u);
			}
			
			/*
			 * BufferedReader br = new BufferedReader(new InputStreamReader( new
			 * ByteArrayInputStream(response.getEntity().getBytes())));
			 * 
			 * String output; System.out.println("Output from Server .... \n");
			 * while ((output = br.readLine()) != null) {
			 * System.out.println(output); }
			 */
		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return usuarios; //dao.findAll();
	}

}
