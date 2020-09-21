package com.view.cliente;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import com.dao.ClienteDAO;
import com.entity.Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControllerCliente {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldCpf;

    @FXML
    private TextField textFieldSalario;

    @FXML
    private TextArea textAreaListClientes;

    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonExcluir;

    @FXML
    private Button buttonBuscar;

    @FXML
    private TextField textFieldId;

    @FXML
    private DatePicker datePickerData;

    @FXML
    void alterarCliente(ActionEvent event) {

    	Cliente cliente= pegaDadosID();
    	if(String.valueOf(cliente.getId()) == null || String.valueOf(cliente.getId()) =="") {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Alerrta");
    		alert.setHeaderText("Cliente não selecionado");
    		alert.setContentText("Selecione um Cliente para alterar");
    	}
    	else {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Alterar Cliente");
        	alert.setHeaderText("Você está prestes a alterar um Cliente");
        	alert.setContentText("Tem certeza que deseja alterar o Cliente?");
        	Optional<ButtonType> result = alert.showAndWait();
        	if (result.get() == ButtonType.OK){
        	
    		limpaCampos();
    		int qtde = new ClienteDAO().alterar(cliente);
    		listarClientes();
        	}
    	}
    	
    }

    @FXML
    void buscarCliente(ActionEvent event) {
    	
    	String idString = textFieldId.getText();
		Cliente cliente = null;
		if (!idString.equals("")) {
			try {
				int id = Integer.valueOf(idString);
				cliente = new ClienteDAO().findByID(id);
			} catch (Exception e) {
			}
			if (cliente != null) {				
				textFieldNome.setText(cliente.getNome());
				textFieldCpf.setText(cliente.getCpf());
				textFieldSalario.setText(cliente.getSalario() + "");
			}
		}
		textFieldId.clear();

    }

    @FXML
    void excluirCliente(ActionEvent event) {

    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Deletar cliente");
    	alert.setHeaderText("Você está prestes a deletar um cliente");
    	alert.setContentText("Tem certeza que deseja deletar o cliente?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		Cliente cliente= pegaDadosID();
        	int qtde = new ClienteDAO().deletar(cliente.getId());
        	limpaCampos();
        	listarClientes();
    	}
    }

    @FXML
    void inserirCliente(ActionEvent event) {
    	
    	Cliente cliente = pegaDados();
		limpaCampos();
		int qtde = new ClienteDAO().inserir(cliente);
		listarClientes();	

    }
    /*
    public void execute() {
		launch();
	}
    */
	private void limpaCampos() {
		textFieldCpf.clear();
		textFieldSalario.clear();		
		textFieldNome.clear();
		textFieldNome.requestFocus();	
	}
    
	private Cliente pegaDados() {
		return new Cliente(textFieldNome.getText(), textFieldCpf.getText(), Date.valueOf(datePickerData.getValue()) , Float.valueOf(textFieldSalario.getText()));
	}
	
	private Cliente pegaDadosID() {		
		return new Cliente(Integer.valueOf(textFieldId.getText()), textFieldNome.getText(), textFieldCpf.getText(),  Date.valueOf(datePickerData.getValue()) , Float.valueOf(textFieldSalario.getText()));
	}

	private void listarClientes() {
		textAreaListClientes.clear();
		List<Cliente> listaCliente = new ClienteDAO().listAll();
		listaCliente.forEach(Cliente -> {
			textAreaListClientes.appendText(Cliente.toString() + "\n");
		});
	}


}
