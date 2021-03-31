package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model; //variabile vuota, solo definita
	
	
	

    @FXML
    private HBox layoutTentativo;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bpmnouvapartita;

    @FXML
    private Label label;

    @FXML
    private TextField tentativitxt;

    @FXML
    private TextField txttentativoutente;

    @FXML
    private Button btmprova;

    @FXML
    private TextArea txtrisultato;

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	//gestione inizio nuova partita ---> Modelo
    	this.model.nuovaPartita(); //inizio la partita
    	
    	//gestione dell'interfaccia
    	this.txtrisultato.clear();
    	this.tentativitxt.setText(Integer.toString(this.model.gettMax()));
    	this.layoutTentativo.setDisable(false);
    	
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//lettura input dell'utente ---> deve essere sempre controlato
    	String ts = txttentativoutente.getText();
    	
    	int tentativo;
    	try { //input va sempre controllato prima di procedere
    		tentativo = Integer.parseInt(ts);
    	} catch(NumberFormatException nfe ) {
    		txtrisultato.appendText("Devi inserire un numero!"); //appendText(non cancela cosa c'era prima)=setText
    		return;
    	}
    	this.txttentativoutente.setText("");
    	
    	
    	int risult;
    	
    	try{
    		risult= this.model.tentativo(tentativo);
    	} catch(IllegalStateException ise) {
    		this.txtrisultato.setText(ise.getMessage());
    		this.layoutTentativo.setDisable(true);
    		return;
    	} catch(InvalidParameterException ipe) {
    		this.txtrisultato.setText(ipe.getMessage());
    		return;
    	}
    	this.tentativitxt.setText(Integer.toString(this.model.gettMax()-this.model.getTentativiFatti())); //Integer-->String
    	
    	if(risult==0) {
    		//Utente ha indovinato
    		txtrisultato.setText("Hai vinto con "+this.model.getTentativiFatti()+" tentativi");
    		this.layoutTentativo.setDisable(true);
    		return;
    	} else if (risult<0) {   
    		txtrisultato.setText("Tentativo troppo basso!");
    	}else 
    		txtrisultato.setText("Tentativo troppo alto!");
    	
    	
  
    	
    }

    @FXML
    void initialize() {
        assert bpmnouvapartita != null : "fx:id=\"bpmnouvapartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tentativitxt != null : "fx:id=\"tentativitxt\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txttentativoutente != null : "fx:id=\"txttentativoutente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btmprova != null : "fx:id=\"btmprova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtrisultato != null : "fx:id=\"txtrisultato\" was not injected: check your FXML file 'Scene.fxml'.";
// this.model = new Moodel(); ---> sbagliato perchè vogliamo disacoppiare il controllore dal modelo
    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
    
    
}
