package br.com.igti.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.igti.dto.LeiloeiroDTO;
import br.com.igti.dto.LeiloeiroRespostaDTO;
import br.com.igti.model.Leiloeiro;
import br.com.igti.repository.LeiloeiroRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/leiloeiros")
@Api(value = "Leiloeiros API")
public class LeiloeiroController {

	@Autowired
	private LeiloeiroRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@ApiOperation(value = "API para consulta de Leiloeiros")
	public List<LeiloeiroRespostaDTO> getAll() {
		return repository.findAll().stream()
				.map(leiloeiro -> modelMapper.map(leiloeiro, LeiloeiroRespostaDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "API para consultar um Leiloeiro por id")
	public LeiloeiroRespostaDTO find(@PathVariable Long id) {
		Leiloeiro leiloeiro = repository.findById(id).get();
		return modelMapper.map(leiloeiro, LeiloeiroRespostaDTO.class);
	}
	
	@GetMapping("/cnpj/{cnpj}")
	@ApiOperation(value = "API para consultar um Leiloeiro por CNPJ")
	public LeiloeiroRespostaDTO find(@PathVariable String cnpj) {
		Leiloeiro leiloeiro = repository.findByCnpj(cnpj);
		return modelMapper.map(leiloeiro, LeiloeiroRespostaDTO.class);
	}
	
	@PostMapping
	@ApiOperation(value = "API para criação de um Leiloeiro")
	public LeiloeiroRespostaDTO create(@RequestBody LeiloeiroDTO dto) {
		Leiloeiro leiloeiro = modelMapper.map(dto, Leiloeiro.class);
		leiloeiro.setSenha(encoder.encode(leiloeiro.getSenha()));
		repository.save(leiloeiro);
		return modelMapper.map(leiloeiro, LeiloeiroRespostaDTO.class);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "API para atualização dos dados de um Leiloeiro")
	public LeiloeiroRespostaDTO update(@RequestBody LeiloeiroDTO dto, @PathVariable Long id) {
		Leiloeiro participanteBD = repository.findById(id).get();
		participanteBD.merge(modelMapper.map(dto, Leiloeiro.class));
		repository.save(participanteBD);
		return modelMapper.map(participanteBD, LeiloeiroRespostaDTO.class);
	}
}
