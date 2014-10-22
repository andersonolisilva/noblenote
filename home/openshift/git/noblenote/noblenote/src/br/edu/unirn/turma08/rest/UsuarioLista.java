package br.edu.unirn.turma08.rest;

import java.util.ArrayList;
import java.util.List;

import br.edu.unirn.turma08.modelo.Usuario;

public class UsuarioLista {

	private static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

}
