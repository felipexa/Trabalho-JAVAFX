package com.view.produto;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.dao.ProdutoDAO;
import com.entity.Produto;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerProduto extends Application implements Initializable{
	
	List<Produto> listaProduto = new ArrayList<Produto>();
	ProdutoDAO dao = new ProdutoDAO();

    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonExcluir;

    @FXML
    private Button buttonBuscar;

    @FXML
    private TextArea textAreaListProdutos;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldQuantidade;

    @FXML
    private TextField textFieldPreco;

    @FXML
    private TextField textFieldCodigo;
    
    @FXML
    private Button buttonSair;
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
    	listarProduto();
	}

    @Override
    public void start(Stage stage) {
        try {
            AnchorPane painel = (AnchorPane) FXMLLoader.load(getClass().getResource("Produto.fxml"));
            stage.setTitle("Cadastro de Produtos");
            Scene sc = new Scene(painel);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void inserirProduto(ActionEvent event) {
    	Produto produto = pegaDados();
    	listaProduto.add(produto);
		limpaCampos();
		listarProduto();
    }
    @FXML
    void alterarProduto(ActionEvent event) {
    	Produto produto = pegaDados();
    	String idString= textFieldCodigo.getText();
    	textFieldNome.setText(produto.getNome());
		textFieldQuantidade.setText(produto.getQuantidade() + "");
		textFieldPreco.setText(produto.getPreco() + "");
    	dao.alterar(produto,idString, listaProduto );
    	listarProduto();
    }
    @FXML
    void excluirProduto(ActionEvent event) {
    	String idString= textFieldCodigo.getText();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Excluir Produto");
    	alert.setContentText("Você tem certeza que deseja excluir o produto?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
            dao.deletar(idString, listaProduto);
        	limpaCampos();
        	listarProduto();
    	}
    }
 
    @FXML
    void buscarProduto(ActionEvent event) {
    	String idString= textFieldCodigo.getText();
    	Produto produto = null;
    	if (!idString.equals("")) {
			try {				
				produto = new ProdutoDAO().buscar(idString, listaProduto);
			} catch (Exception e) {
			}
			if (produto != null) { 					
				textFieldNome.setText(produto.getNome());
				textFieldQuantidade.setText(produto.getQuantidade() + "");
				textFieldPreco.setText(produto.getPreco() + "");
			}
		}
    }
  
    
    private void limpaCampos() {
		textFieldNome.clear();
		textFieldQuantidade.clear();
		textFieldPreco.clear();
		textFieldNome.requestFocus();		
	}
  
    private void listarProduto() {
		textAreaListProdutos.clear();
		listaProduto.forEach(produto -> {
			textAreaListProdutos.appendText(produto.toString() + "\n");
		});
	}
    
    private Produto pegaDados() {
		return new Produto(textFieldNome.getText(), Integer.valueOf(textFieldQuantidade.getText()) , java.lang.Float.parseFloat(textFieldPreco.getText()));
	}
    
    public void execute() {
		launch();
	}

    @FXML
    void sair(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Sair da Aplicação");
    	alert.setContentText("Saindo..");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){           
    		System.exit(0);
    	}
    }
	
}
