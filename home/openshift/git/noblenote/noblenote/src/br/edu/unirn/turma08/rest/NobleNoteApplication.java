package br.edu.unirn.turma08.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
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
