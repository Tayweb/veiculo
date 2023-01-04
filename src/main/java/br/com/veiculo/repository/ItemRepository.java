package br.com.veiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.veiculo.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
