package br.com.igti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igti.model.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long>{

	public Participante findByCpf(String cpf);
}
