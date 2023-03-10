package br.com.veiculo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Marca implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Campo nome obrigatório")
	@Column(unique = true)
	private String nome;
	
	@NotBlank(message = "Campo Ativo Obrigatório")
	private String ativo;
	
	@OneToMany(mappedBy = "marca")
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	
	
}
