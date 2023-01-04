package br.com.veiculo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.veiculo.model.Item;
import br.com.veiculo.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getItem() {
	return itemRepository.findAll();
	}
	
	public Item findById(Long id) {
		return itemRepository.getById(id);
	}
	
}
