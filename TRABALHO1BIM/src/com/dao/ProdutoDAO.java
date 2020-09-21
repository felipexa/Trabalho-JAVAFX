package com.dao;

import java.util.List;

import com.entity.Produto;

public class ProdutoDAO {

	public Produto inserirDados(Produto produto) {
        Produto inserirProduto  = new Produto();
        inserirProduto.setNome(produto.getNome());
        inserirProduto.setQuantidade(produto.getQuantidade());
        inserirProduto.setPreco(produto.getPreco());
        
        return inserirProduto;
    }
	public void alterar(Produto produto, String nome, List<Produto> listaProduto) {
        for (int i = 0; i < listaProduto.size(); i++) {
            if(listaProduto.get(i).getNome().equals(nome)) {
                listaProduto.set(i, produto);
            }
        }
    }
	public Produto buscar(String nome, List<Produto> listaProduto) {
        for (int i = 0; i < listaProduto.size(); i++) {
            if(listaProduto.get(i).getNome().equals(nome)) {
                return listaProduto.get(i);
            }
        }
        return null;
    }
		
		
    public void deletar(String nome, List<Produto> listaProduto) {
        for (int i = 0; i < listaProduto.size(); i++) {
            if(listaProduto.get(i).getNome().equals(nome)) {
                listaProduto.remove(i);
            }
        }
    }
	    
}
