package br.com.veiculo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.veiculo.model.Marca;
import br.com.veiculo.repository.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository marcaVeiculoRepository;

	@Transactional
	public Marca save(Marca marcaVeiculo) {
		return marcaVeiculoRepository.save(marcaVeiculo);
	}

	@Transactional
	public List<Marca> getMarca() {
		List<Marca> listaMarcaVeiculos = marcaVeiculoRepository.findAll();
		return listaMarcaVeiculos.stream().sorted((m1, m2) -> m1.getNome().compareTo(m2.getNome()))
				.collect(Collectors.toList());
	}

	public Marca findByMarcaId(Long id) throws NotFoundException {

		Optional<Marca> marcaVeiculo = marcaVeiculoRepository.findById(id);

		return marcaVeiculo.orElseThrow(() -> new NotFoundException());
	}

	public void deletarMarca(Long id) {
		marcaVeiculoRepository.deleteById(id);
	}

	@Transactional
	public List<Marca> filtroPesquisa(Marca marcaVeiculo) {
		
		if (marcaVeiculo.getNome().isEmpty()) {
			marcaVeiculo.setNome(null);
		}

		if (Objects.nonNull(marcaVeiculo.getNome()) && Objects.isNull(marcaVeiculo.getAtivo())) {
			return marcaVeiculoRepository.filtrarPesquisaPorNome(marcaVeiculo.getNome());
			
		} else if (Objects.nonNull(marcaVeiculo.getAtivo()) && Objects.isNull(marcaVeiculo.getNome())) {
			return marcaVeiculoRepository.filtrarPesquisaPorAtivo(marcaVeiculo.getAtivo());
			
		} else if (Objects.nonNull(marcaVeiculo.getNome()) && Objects.nonNull(marcaVeiculo.getAtivo())) {
			return marcaVeiculoRepository.filtrarPesquisaCompleto(marcaVeiculo.getNome(), marcaVeiculo.getAtivo());
			
		} else {
			return getMarca();
		}
	}

}
