package com.lavanya.factoryDesign;

import com.lavanya.challenger.Challenger;
import com.lavanya.defenseur.Defenseur;
import com.lavanya.duel.Duel;

/**
 * La classe GameFactory est un factory producer selon l'abstract factory design.
 * Il va permettre en passant par la classe abstraite Game d'accéder aux classes Defenseur, Duel ou Challenger
 * @see Game  
 * @author lavanya
 *
 */
public class GameFactory {
	
	/**
	 * Méthode pour accéder au jeu d'intérêt: Defenseur, Challenger ou Duel
	 * @param name
	 * 			ceci est un enum qui permet d'accéder à la concrete classe d'intérêt en passant directement son nom
	 * @see GameType
	 * @return retourne la valeur null si aucune condition n'est remplie
	 */
	public static Game getGame(GameType name){
		
		if(GameType.defenseur == name){
            return new Defenseur();
        }
        else if(GameType.challenger == name){
            return new Challenger();
        }
        else if(GameType.duel == name){
            return new Duel();
        }

		return null;
	}

}
