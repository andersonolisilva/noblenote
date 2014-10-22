package br.edu.unirn.turma08.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import br.edu.unirn.turma08.modelo.Note;

@Path("/note")
public class NoteResource {

	private List<Note> notes = new ArrayList<Note>();
	
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	@POST
	@Consumes({"application/json","text/xml"})
	@Path("/cadastrarnotas")
	public void cadastrarNotas(@QueryParam("login") String login,
			@QueryParam("senha") String senha, List<Note> notas ) {

		notes.addAll(notas);

	}
	
	
}
