package br.com.igti.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Participante extends Usuario {
	
	private String nome;	
	private String cpf;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;	
	
	@Transient
	public void merge(Participante participante) {
		super.merge(participante);
		if (participante.getNome() != null) {
			setNome(participante.getNome());
		}
		if (participante.getDataNascimento() != null) {
			setDataNascimento(participante.getDataNascimento());
		}
	}
}
