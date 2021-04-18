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

import br.com.igti.dto.ParticipanteDTO;
import br.com.igti.dto.ParticipanteRespostaDTO;
import br.com.igti.model.Participante;
import br.com.igti.repository.ParticipanteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/participantes")
@Api(value = "Participantes API")
public class ParticipanteController {

	@Autowired
	private ParticipanteRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@ApiOperation(value = "API para consulta de Participantes")
	public List<ParticipanteRespostaDTO> getAll() {
		return repository.findAll().stream()
				.map(participante -> modelMapper.map(participante, ParticipanteRespostaDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "API para consultar um Participante por id")
	public ParticipanteRespostaDTO find(@PathVariable Long id) {
		Participante participante = repository.findById(id).get();
		return modelMapper.map(participante, ParticipanteRespostaDTO.class);
	}
	
	@GetMapping("/cpf/{cpf}")
	@ApiOperation(value = "API para consultar um Participante por CPF")
	public ParticipanteRespostaDTO find(@PathVariable String cpf) {
		Participante participante = repository.findByCpf(cpf);
		return modelMapper.map(participante, ParticipanteRespostaDTO.class);
	}
	
	@PostMapping
	@ApiOperation(value = "API para criação de um Participante")
	public ParticipanteRespostaDTO create(@RequestBody ParticipanteDTO dto) {
		Participante participante = modelMapper.map(dto, Participante.class);
		participante.setSenha(encoder.encode(participante.getSenha()));
		repository.save(participante);
		return modelMapper.map(participante, ParticipanteRespostaDTO.class);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "API para atualização dos dados de um Participante")
	public ParticipanteRespostaDTO update(@RequestBody ParticipanteDTO dto, @PathVariable Long id) {
		Participante participanteBD = repository.findById(id).get();
		participanteBD.merge(modelMapper.map(dto, Participante.class));
		repository.save(participanteBD);
		return modelMapper.map(participanteBD, ParticipanteRespostaDTO.class);
	}
}
