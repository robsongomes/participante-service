package br.com.igti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeiloeiroRespostaDTO {

	private String id;
	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;	
	private String email;	
	private String endereco;	
	private String telefone;
	private String dataCadastro;
}
