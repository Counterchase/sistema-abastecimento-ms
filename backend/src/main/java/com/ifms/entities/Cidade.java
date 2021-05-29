package com.ifms.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_Cidade")
public class Cidade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_estado_fk")
	private Estado estado;
	
	@OneToMany(mappedBy = "cidade")
	@JsonIgnore
	private List<AutoPosto> autoPosto;
	
	@OneToMany(mappedBy = "cidade")
	@JsonIgnore
	private List<Lotacao> lotacao;

	public Cidade() {
		
	}

	public Cidade(Long id, String nome, Estado estado, List<AutoPosto> autoPosto, List<Lotacao> lotacao) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
		this.autoPosto = autoPosto;
		this.lotacao = lotacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	

	public List<Lotacao> getLotacao() {
		return lotacao;
	}

	public void setLotacao(List<Lotacao> lotacao) {
		this.lotacao = lotacao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<AutoPosto> getAutoPosto() {
		return autoPosto;
	}

	public void setAutoPosto(List<AutoPosto> autoPosto) {
		this.autoPosto = autoPosto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [nome=" + nome + "]";
	}
	
	
	
	
}
