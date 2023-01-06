package br.com.veiculo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;

import lombok.Data;
@Data
@Entity
public class Veiculo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	private String modelo;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "veiculo_itens", uniqueConstraints = @UniqueConstraint(columnNames = { "veiculo_id",
	"itens_id" }, name = "unique_itens_veiculo"), joinColumns = @JoinColumn(name = "veiculo_id", 
	referencedColumnName = "id", table = "veiculo"),
	inverseJoinColumns = @JoinColumn(name = "itens_id", referencedColumnName = "id", table = "item"))
	private Set<Item> itens = new HashSet<>();

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

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}
	
	

}
