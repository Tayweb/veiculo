package br.com.veiculo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	private String modelo;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "veiculo_marca", uniqueConstraints = @UniqueConstraint(columnNames = { "veiculo_id",
			"marca_id" }, name = "unique_marca_veiculo"), joinColumns = @JoinColumn(name = "veiculo_id", referencedColumnName = "id", table = "veiculo", unique = false, foreignKey = @ForeignKey(name = "veiculo_fk", value = ConstraintMode.CONSTRAINT)), inverseJoinColumns = @JoinColumn(name = "marca_id", referencedColumnName = "id", table = "marca", unique = false, foreignKey = @ForeignKey(name = "marca_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Marca> marca = new ArrayList<Marca>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "veiculo_itens", uniqueConstraints = @UniqueConstraint(columnNames = { "veiculo_id",
	"itens_id" }, name = "unique_itens_veiculo"), joinColumns = @JoinColumn(name = "veiculo_id", 
	referencedColumnName = "id", table = "veiculo"),
	inverseJoinColumns = @JoinColumn(name = "itens_id", referencedColumnName = "id", table = "item"))
	private Set<Item> itens  = new HashSet<>();

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

	public List<Marca> getMarca() {
		return marca;
	}

	public void setMarca(List<Marca> marca) {
		this.marca = marca;
	}

	public Set<Item> getItensVeiculos() {
		return itens;
	}

	public void setItensVeiculos(Set<Item> itens) {
		this.itens = itens;
	}

}
