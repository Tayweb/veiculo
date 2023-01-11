package br.com.veiculo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

		if (Objects.nonNull(veiculo.getId())) {
			return editar(veiculo);
		}

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

	@Transactional
	private Veiculo editar(Veiculo veiculo) throws NotFoundException {

		Veiculo veiculoAnterior = findByVeiculoId(veiculo.getId());

		Set<Item> itens = new HashSet<Item>();

		itens = Stream.concat(veiculo.getItens().stream(), veiculoAnterior.getItens().stream())
				.collect(Collectors.toSet());

		veiculo.setItens(itens);

		veiculo.setItens(veiculo.getItens().stream().map(items -> {

			Item item = items;
			if (item.getId() > 0) {
				item = itemService.findById(item.getId());
			}

			item.addVeiculo(veiculo);
			return item;
		}).collect(Collectors.toSet()));

		return veiculoRepository.save(veiculo);
	}

	@Transactional
	public List<Veiculo> getVeiculo() {
		List<Veiculo> veiculos = veiculoRepository.findAll();
		return veiculos;
	}

	@Transactional
	public Veiculo findByVeiculoId(Long id) throws NotFoundException {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);

		return veiculo.orElseThrow(() -> new NotFoundException());
	}

	public void deletar(Long id) {
		veiculoRepository.deleteById(id);
	}

}
