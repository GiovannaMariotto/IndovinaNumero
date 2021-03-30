package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int nMax = 100; //costante
	private final int tMax = 8; //costante
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	

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
    	//gestione inizio nuova partita
    	this.segreto = (int)(Math.random()*nMax)+1; //Math.ramdon --> Double
    	this.tentativiFatti=0;
    	this.inGioco=true;
    	
    	//gestione dell'interfaccia
    	this.tentativitxt.setText(Integer.toString(tMax));
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
    	this.tentativiFatti++;
    	this.tentativitxt.setText(Integer.toString(tMax-this.tentativiFatti)); //Integer-->String
    	if(tentativo == this.segreto) {
    		//Utente ha indovinato
    		txtrisultato.setText("Hai vinto con "+this.tentativiFatti+" tentativi");
    		this.inGioco=false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	
    	if(this.tentativiFatti==tMax) {
    		//ho esaurito i tentativi --> ho perso
    		txtrisultato.setText("Hai Perso! Il segreto era:"+ this.segreto);
    		this.inGioco=false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	
    	//Se non hai vinto, devo informare l'utente circa la bontà del suo tentativo
    	if(tentativo<this.segreto) {
    		txtrisultato.setText("Tentativo troppo basso!");
    		
    	} else {
    		txtrisultato.setText("Tentativo troppo alto!");
    	}
    	
    	
    	
    }

    @FXML
    void initialize() {
        assert bpmnouvapartita != null : "fx:id=\"bpmnouvapartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tentativitxt != null : "fx:id=\"tentativitxt\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txttentativoutente != null : "fx:id=\"txttentativoutente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btmprova != null : "fx:id=\"btmprova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtrisultato != null : "fx:id=\"txtrisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
