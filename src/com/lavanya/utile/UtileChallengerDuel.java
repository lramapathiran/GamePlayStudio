package com.lavanya.utile;

import java.io.IOException;


import com.lavanya.interfaces.ChallDuelInterface;



/**
 * Classe qui contient les méthodes communes à la classe Challenger et Duel.
 * La classe UtileChallengerDuel implémente l'interface ChallDuelInterface.
 * @author lavanya
 *
 */
public class UtileChallengerDuel implements ChallDuelInterface {
	
	/**
	 * Méthode qui permet d'instancier Configuration et de récupérer les données de type boolean contenues dans le fichier properties.
	 * L'accès au fichier properties se fait au travers de la classe singleton Configuration dont on récupère l'instance ici.
	 * @see Configuration
	 * @param key
	 *		ceci correspond au nom affecté à la donnée qu'on souhaite récupérer dans le fichier properties.
	 * @return retourne le boolean de la donnée d'intérêt stockée dans le fichier properties via la méthode getIntProperty() du singleton Configuration.
	 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.  
	 */
	public boolean booleanProperties(String key) throws IOException {
		boolean confValue = Configuration.getInstance().getBooleanProperty(key);
		return confValue;
	}

}
