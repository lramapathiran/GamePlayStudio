package com.lavanya.factoryDesign;

import java.io.IOException;


/**
 * Game est une abstract classe n�cesaire pour la cr�ation de concretes classes
 * Game va permettre aux classes Defenseur, Challenger et Duel d'h�riter de celle-ci.
 * @author lavanya
 *
 */
public abstract class Game {
	
	
	/**
	 * M�thode abstract 
	 * @throws IOException g�n�re une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.
	 */
	public void gamePlay() throws IOException {
		
		
	}	
	
}
