package br.edu.unirn.turma08.noblenote.dao;

import javax.persistence.Query;

import br.edu.unirn.turma08.modelo.Usuario;
import br.edu.unirn.turma08.noblenote.arquitetura.GenericDAO;

public class UsuarioDAO extends GenericDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	public Usuario logar(Usuario usuario){
	
		String jpql = "from Usuario u where u.login = :login and u.senha = :senha ";
		
		Query q = getEm().createQuery(jpql);
		q.setParameter("login", usuario.getLogin());
		q.setParameter("senha", usuario.getSenha());
		
	    
		try{
		    usuario = (Usuario) q.getSingleResult();
		}catch(Exception e){
			usuario = null;
		}
		
		return usuario;
	}
	
	public Usuario recuperarSenha(Usuario usuario){
		
		String jpql = "from Usuario u where u.login = :login and u.numeroTelefone = :numeroTelefone ";
		
		Query q = getEm().createQuery(jpql);
		q.setParameter("login", usuario.getLogin());
		q.setParameter("numeroTelefone", usuario.getNumeroTelefone());
		
	    
		try{
		    usuario = (Usuario) q.getSingleResult();
		}catch(Exception e){
			usuario = null;
		}
		
		return usuario;
	}
	
}
