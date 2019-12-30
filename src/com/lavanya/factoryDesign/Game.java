package com.lavanya.factoryDesign;

import java.io.IOException;


/**
 * Game est une abstract classe nécesaire pour la création de concretes classes
 * Game va permettre aux classes Defenseur, Challenger et Duel d'hériter de celle-ci.
 * @author lavanya
 *
 */
public abstract class Game {
	
	
	/**
	 * Méthode abstract 
	 * @throws IOException génère une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.
	 */
	public void gamePlay() throws IOException {
		
		
	}	
	
}
