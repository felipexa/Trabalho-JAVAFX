package com.entity;

public class Produto {

	private int cod;
	private String nome;
	private int quantidade;
	private Float preco;
	
	public Produto() {
		
	}
	public Produto(String nome, int quantidade, Float preco) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public Produto(int cod, String nome, int quantidade, Float preco) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return "- Código Produto: " + cod + " " + "\n" 
				+ "     • Nome Produto: " + nome + "\n"  + "     • Quantidade: " + quantidade + "\n" + "     • Preço: " + preco + "\n" ;
	}
	
}
