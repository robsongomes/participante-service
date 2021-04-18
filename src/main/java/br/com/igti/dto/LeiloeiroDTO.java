package br.com.igti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeiloeiroDTO {

	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;		
	private String email;	
	private String senha;
	private String endereco;	
	private String telefone;
}
