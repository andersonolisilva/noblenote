package br.edu.unirn.turma08.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.edu.unirn.turma08.modelo.Usuario;

public class NobleNoteApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

    public NobleNoteApplication() {
		/*singletons.add(new TwitterResource());
		singletons.add(new LoginResource());*/
		singletons.add(new UsuarioResource());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
