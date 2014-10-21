package br.edu.unirn.turma08.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.unirn.turma08.noblenote.arquitetura.PersistDB;

@Entity
@Table(name = "note")
@ManagedBean
public class Note implements PersistDB, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="seq_note",initialValue=1,
						allocationSize=1,sequenceName="seq_note")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_note")
	private int id;
	private String anotacao;
	private boolean anotacaoPublica;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAtualizacao;
	private boolean modificada;
	private boolean excluida;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	public boolean isAnotacaoPublica() {
		return anotacaoPublica;
	}

	public void setAnotacaoPublica(boolean anotacaoPublica) {
		this.anotacaoPublica = anotacaoPublica;
	}

	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public boolean isModificada() {
		return modificada;
	}

	public void setModificada(boolean modificada) {
		this.modificada = modificada;
	}

	public boolean isExcluida() {
		return excluida;
	}

	public void setExcluida(boolean excluida) {
		this.excluida = excluida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
