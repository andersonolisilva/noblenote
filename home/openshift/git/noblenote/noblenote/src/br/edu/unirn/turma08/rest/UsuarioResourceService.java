package br.edu.unirn.turma08.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

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

public class UsuarioResourceService {

	UsuarioDAO dao = new UsuarioDAO();

	public String converterObjetoUsuarioEmJson(Usuario usuario) {

		Usuario usua = dao.findByPrimaryKey(usuario.getId());

		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
		Gson g = builder.create();
		String json = g.toJson(usua);
		System.out.println(json);
		return json;

	}

	public void converterJsonEmObjetoUsuario() {

		Usuario usuario = new Usuario();
		usuario.setId(1);

		String json = converterObjetoUsuarioEmJson(usuario);
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
		Gson g = builder.create();
		Usuario usua = g.fromJson(json, Usuario.class);
		System.out.println("Nome: " + usua.getNome());
		System.out.println("Login: " + usua.getLogin());
		System.out.println("Número de Telefone: " + usua.getNumeroTelefone());

	}

	public Usuario recuperarSenha(Usuario usuario) {

		try {
			ClientRequest request = new ClientRequest(
					"http://localhost:8080/noblenote/rest/usuario/recuperarsenha");
			request.accept("application/json");

			String input = converterObjetoUsuarioEmJson(usuario);
			request.body("application/json", input);

			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(response.getEntity().getBytes())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
		
		return null;
	}

	public List<Usuario> pesquisarListaJson() {

		List<Usuario> usuarios = new ArrayList<Usuario>();

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
				int fim = object.toString().length();
				System.out.println(" name is --"
						+ object.toString().substring(10, fim - 1));
				GsonBuilder builder2 = new GsonBuilder();
				builder2.setDateFormat("dd/MM/yyyy");
				Gson g2 = builder2.create();
				Usuario u = g2.fromJson(object.toString()
						.substring(10, fim - 1), Usuario.class);

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

		return usuarios; // dao.findAll();
	}

}
