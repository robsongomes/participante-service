package br.com.igti.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {

	@Id
	@GeneratedValue
	protected Long id;
	protected String endereco;	
	protected String telefone;
	protected String email;	
	protected String senha;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new Date();
	
	@Transient
	public void merge(Usuario usuario) {
		if (usuario.getEndereco() != null) {
			setEndereco(usuario.getEndereco());
		}
		if (usuario.getTelefone() != null) {
			setTelefone(usuario.getTelefone());
		}
	}
}
