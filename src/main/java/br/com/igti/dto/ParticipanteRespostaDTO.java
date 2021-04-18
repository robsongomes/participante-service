package br.com.igti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParticipanteRespostaDTO {
	
	private String id;
	private String nome;	
	private String cpf;
	private String dataNascimento;	
	private String email;	
	private String endereco;	
	private String telefone;
	private String dataCadastro;
	
}
