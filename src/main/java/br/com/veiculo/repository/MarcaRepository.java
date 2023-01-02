package br.com.veiculo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.veiculo.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{
	
	@Query(value = "SELECT * FROM marca WHERE nome LIKE (concat( ?1,'%'))", nativeQuery = true)
	List<Marca> filtrarPesquisaPorNome(String nome);
	
	@Query(value = "SELECT m FROM Marca m WHERE m.ativo =?1")
	List<Marca> filtrarPesquisaPorAtivo(String ativo);
	
	@Query(value = "SELECT * FROM marca WHERE nome LIKE (concat( ?1,'%')) AND ativo=?2", nativeQuery = true)
	List<Marca> filtrarPesquisaCompleto(String nome, String ativo);

}
