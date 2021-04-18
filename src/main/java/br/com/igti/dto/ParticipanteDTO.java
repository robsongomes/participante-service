package br.com.igti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParticipanteDTO {
	
	private String nome;	
	private String cpf;
	private String dataNascimento;	
	private String email;	
	private String senha;
	private String endereco;	
	private String telefone;
	
}
