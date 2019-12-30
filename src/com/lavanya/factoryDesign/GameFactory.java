package com.lavanya.factoryDesign;

import com.lavanya.challenger.Challenger;
import com.lavanya.defenseur.Defenseur;
import com.lavanya.duel.Duel;

/**
 * La classe GameFactory est un factory producer selon l'abstract factory design.
 * Il va permettre en passant par la classe abstraite Game d'acc�der aux classes Defenseur, Duel ou Challenger
 * @see Game  
 * @author lavanya
 *
 */
public class GameFactory {
	
	/**
	 * M�thode pour acc�der au jeu d'int�r�t: Defenseur, Challenger ou Duel
	 * @param name
	 * 			ceci est un enum qui permet d'acc�der � la concrete classe d'int�r�t en passant directement son nom
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
