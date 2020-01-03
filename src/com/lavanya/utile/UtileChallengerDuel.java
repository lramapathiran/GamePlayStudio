package com.lavanya.utile;

import java.io.IOException;


import com.lavanya.interfaces.ChallDuelInterface;



/**
 * Classe qui contient les m�thodes communes � la classe Challenger et Duel.
 * La classe UtileChallengerDuel impl�mente l'interface ChallDuelInterface.
 * @author lavanya
 *
 */
public class UtileChallengerDuel implements ChallDuelInterface {
	
	/**
	 * M�thode qui permet d'instancier Configuration et de r�cup�rer les donn�es de type boolean contenues dans le fichier properties.
	 * L'acc�s au fichier properties se fait au travers de la classe singleton Configuration dont on r�cup�re l'instance ici.
	 * @see Configuration
	 * @param key
	 *		ceci correspond au nom affect� � la donn�e qu'on souhaite r�cup�rer dans le fichier properties.
	 * @return retourne le boolean de la donn�e d'int�r�t stock�e dans le fichier properties via la m�thode getIntProperty() du singleton Configuration.
	 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.  
	 */
	public boolean booleanProperties(String key) throws IOException {
		boolean confValue = Configuration.getInstance().getBooleanProperty(key);
		return confValue;
	}

}
