package br.com.igti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igti.model.Leiloeiro;

public interface LeiloeiroRepository extends JpaRepository<Leiloeiro, Long>{

	Leiloeiro findByCnpj(String cnpj);

}
