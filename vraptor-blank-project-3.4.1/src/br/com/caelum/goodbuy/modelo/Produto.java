package br.com.caelum.goodbuy.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Range;

@Entity
public class Produto {
	
	@Id @GeneratedValue
	private Long id;
		
	@NotNull(message="Nome precisa ser preenchido.")
	@Length(min=3, message="Nome precisa ter mais de 3 letras")
	private String nome;
	
	@NotNull(message="Descrição precisa ser preenchido.")
	@Length(max=40, message="Nome precisa ter no máximo de 40 letras")
	private String descricao;
	
	@Range(min=1, max=99999, message="Preço inválido.")
	private Double preco;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
