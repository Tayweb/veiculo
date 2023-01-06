package br.com.veiculo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.veiculo.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

	@Query(value = "select item.id, item.nome, veiculo_itens.itens_id \r\n"
			+ "from veiculo_itens\r\n"
			+ "inner join item\r\n"
			+ "on  veiculo_itens.itens_id = item.id\r\n"
			+ "where veiculo_id =?1", nativeQuery = true)
	public List<Item> listarItensVeiculo(Long idVeiculo);
}
