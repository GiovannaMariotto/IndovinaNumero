package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	//Logica Applicativa deve essere trasportata qui
	private final int nMax = 100; //costante
	private final int tMax = 8; //costante
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	private Set<Integer> tentativi; //Integer ha il suo HashCode ben definito (int no)
	
	public void nuovaPartita() {
		//gestione di una nuova partita
		this.segreto = (int)(Math.random()*nMax)+1; //Math.ramdon --> Double
    	this.tentativiFatti=0;
    	this.inGioco=true;
    	this.tentativi = new HashSet<Integer>();
	}

	//tentativo: cosa è inserito il utente
	public int tentativo(int tentativo) {
		//controllare se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("HAI PERSO! IL SEGRETO ERA:"+this.segreto);
		}
		//Controllo del input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Deve inserire un numero tra 1 e "+this.nMax);
			
		}
		// Tentativo valido
		this.tentativiFatti++;
		if(this.tentativiFatti==tMax) {
			this.inGioco=false; //Partita terminata
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;
		} else if(tentativo<this.segreto) {
			return -1;
		}else
			return 1;
		
	}
	/**
	 * Questo metodo mi dice se il tentativo è valido oppure no
	 * @return
	 */
	public boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>nMax) {
			return false;
		}
		if(tentativi.contains(tentativo)){
			return false;
		}
		return true;
		
	}
	
	
	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	

	public int getnMax() {
		return nMax;
	}

	public int gettMax() {
		return tMax;
	}
	
	
}
