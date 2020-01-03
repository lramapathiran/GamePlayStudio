package com.lavanya.utile;




/**
 * Classe Proposition pour créer un objet ayant les attributs min et max
 * nécessaire pour être utilisé dans la liste de Proposition dans la classe UtileDefenseurDuel
 * @see UtileDefenseurDuel
 * @author lavanya
 *
 */
public class Proposition {
	
//	initialisation des champs nécessaires à Proposition
	public int max;
	public int min;
	

	/**
	 * Méthode du constructeur vide, constructeur par défaut pour pouvoir utiliser une instance de Proposition
	 */
	public Proposition() {
		
	}
	

	/**
	 * Méthode du constructeur qui définit les paramètres associés à l'objet Proposition
	 * @param min
	 * 		ceci correspond à la limite minimum de l'intervalle dans lequel l'ordinateur devra deviner un chiffre aléatoire
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
	 * setter du champ min pour définir sa valeur
	 * @param min
	 * 		ceci correspond à la limite minimum de l'intervalle dans lequel l'ordinateur devra deviner un chiffre aléatoire
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
	 * setter du champ max pour définir sa valeur
	 * @param max
	 * 		ceci correspond à la limite maximum de l'intervalle dans lequel l'ordinateur devra deviner un chiffre aléatoire
	 */
	public void setMax(int max) {
		this.max = max;
	}
	
	
	

}

