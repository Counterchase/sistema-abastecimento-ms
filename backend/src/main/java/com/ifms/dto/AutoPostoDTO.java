package com.ifms.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.ifms.entities.Cidade;
import com.ifms.entities.AutoPosto;

public class AutoPostoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@Size(min = 8, max = 120, message = "tam min 8 e max 120 caractere")
	@NotBlank(message = " o campo é obrigatório")
	private String nomeFantasia;
	@NotBlank(message = " o campo é obrigatório")
	private String telefone;
	@Email
	private String email;
	@CNPJ(message = "O CNPJ nao Existe")
	private String CNPJ;
	private String endereco;
	private Cidade cidade;
	
	public void AutoPosto() {
		
	}

	public AutoPostoDTO(Long id, String nomeFantasia, String telefone, String email, String cNPJ, String endereco,
			Cidade cidade) {
		this.id = id;
		this.nomeFantasia = nomeFantasia;
		this.telefone = telefone;
		this.email = email;
		CNPJ = cNPJ;
		this.endereco = endereco;
		this.cidade = cidade;
	}
	
	public AutoPostoDTO(AutoPosto entity) {
		this.id = entity.getId();
		this.nomeFantasia = entity.getNomeFantasia();
		this.telefone = entity.getTelefone();
		this.email = entity.getEmail();
		CNPJ = entity.getCNPJ();
		this.endereco = entity.getEndereco();
		this.cidade = entity.getCidade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
}
