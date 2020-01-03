package com.lavanya.utile;




/**
 * Classe Proposition pour cr�er un objet ayant les attributs min et max
 * n�cessaire pour �tre utilis� dans la liste de Proposition dans la classe UtileDefenseurDuel
 * @see UtileDefenseurDuel
 * @author lavanya
 *
 */
public class Proposition {
	
//	initialisation des champs n�cessaires � Proposition
	public int max;
	public int min;
	

	/**
	 * M�thode du constructeur vide, constructeur par d�faut pour pouvoir utiliser une instance de Proposition
	 */
	public Proposition() {
		
	}
	

	/**
	 * M�thode du constructeur qui d�finit les param�tres associ�s � l'objet Proposition
	 * @param min
	 * 		ceci correspond � la limite minimum de l'intervalle dans lequel l'ordinateur devra deviner un chiffre al�atoire
	 * @param max
	 * 		idem que min mais pour la limite maximum
	 */
	public Proposition(int min, int max) {
		this.min = min;
		this.max = max;
	}

	/**
	 * getter du champ min pour obtenir la valeur du min
	 * @return le min de l'intervalle
	 */
	public int getMin() {
		return min;
	}
	
	/**
	 * setter du champ min pour d�finir sa valeur
	 * @param min
	 * 		ceci correspond � la limite minimum de l'intervalle dans lequel l'ordinateur devra deviner un chiffre al�atoire
	 * 
	 */
	public void setMin(int min) {
		this.min = min;
	}
	
	/**
	 * getter du champ max pour obtenir la valeur du max
	 * @return le max de l'intervalle
	 */
	public int getMax() {
		return max;
	}
	
	/**
	 * setter du champ max pour d�finir sa valeur
	 * @param max
	 * 		ceci correspond � la limite maximum de l'intervalle dans lequel l'ordinateur devra deviner un chiffre al�atoire
	 */
	public void setMax(int max) {
		this.max = max;
	}
	
	
	

}

