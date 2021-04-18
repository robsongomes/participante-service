package br.com.igti.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
public class Leiloeiro extends Usuario {
	
	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;
	
	@Transient
	public void merge(Leiloeiro leiloeiro) {
		if (leiloeiro.getNomeFantasia() != null) {
			setNomeFantasia(leiloeiro.getNomeFantasia());
		}
		if (leiloeiro.getRazaoSocial() != null) {
			setRazaoSocial(leiloeiro.getRazaoSocial());
		}
		if (leiloeiro.getEndereco() != null) {
			setEndereco(leiloeiro.getEndereco());
		}
		if (leiloeiro.getTelefone() != null) {
			setTelefone(leiloeiro.getTelefone());
		}
	}
}
