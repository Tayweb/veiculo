package br.com.veiculo.service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.veiculo.model.Item;
import br.com.veiculo.model.Marca;
import br.com.veiculo.model.Veiculo;
import br.com.veiculo.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private MarcaService marcaService;

	@Autowired
	private ItemService itemService;

	@Transactional
	public Veiculo salvar(Veiculo veiculo) throws NotFoundException {

		Marca marca = marcaService.findByMarcaId(veiculo.getMarca().getId());
		veiculo.setMarca(marca);
		veiculo.setItens(veiculo.getItens().stream().map(itens -> {
			Item item = itens;
			if (item.getId() > 0) {
				item = itemService.findById(item.getId());
			}

			item.addVeiculo(veiculo);
			return item;
		}).collect(Collectors.toSet()));

		return veiculoRepository.save(veiculo);
	}
}
