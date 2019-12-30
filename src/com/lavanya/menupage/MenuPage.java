package com.lavanya.menupage;

import java.io.IOException;
import java.util.List;

import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.utile.Utile;



/**
 * Singleton class MenuPage qui lance tous les menus associés à chaque classe GameLaunch, Defenseur, Challenger ou Duel
 * @author lavanya
 *
 */
public class MenuPage {
	
//	Variable statique  _instance de type singleton
	private static MenuPage _instance = null;
	DefChallDuelInterface iDefChallDuel = new Utile();
	
	/**
     * Méthode statique qui crée une instance de la classe MenuPage seulement si _instance n'a jamais été instancié auparavant
     * @return l'unique instance de MenuPage
     * @throws IOException, lève une exception si MenuPage a mal été instancié
     */
	public synchronized static MenuPage getInstance() throws IOException {
        
		
//		Bloc if qui vérifie si _instance de MenuPage a déja été instancié
//    	si non, il est instancié il passe de null à une instanciation en un objet de MenuPage
//    	si oui (_intance n'avait pas pour valeur nulle) il ne s'instancie pas de nouveau, il retourne la valeur que l'objet avait déjà
		if (_instance == null)
            _instance = new MenuPage();
        
        return _instance;
    }
	
	/**
	 * Méthode qui envoie le contenu du menu principal quand on rentre dans le jeu GamePlayStudio
	 */
	public void menuStart() {
		System.out.println("Bienvenue à GAMEPLAY STUDIO");
		System.out.println("Menu");
		System.out.println("1: Défenseur");
		System.out.println("2: Challenger");
		System.out.println("3: Duel");
		
		System.out.println("Quel mode de jeu vous intéresse? Veuillez préciser votre choix en saisissant un chiffre 1 à 4: ");
	}
	
	/**
	 * Méthode qui envoie le contenu du menu du jeu Défenseur
	 * @return retourne la combinaison secrète de plusieurs chiffres du joueur sous forme de liste d'Integer
	 * @throws IOException, lève une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.
	 */
	public List<Integer> menuStartDefenseur() throws IOException {
		
		System.out.println("Mode Défenseur");
		System.out.println("Bienvenue dans le mode Défenseur!");	
		System.out.println("Vous devez me faire découvrir une combinaison de 4 chiffres comprise entre 0000 et 9999!");
		System.out.println("J'ai 5 tentatives pour y arriver");
		System.out.println("Pour m'aider; à chaque proposition que je ferai, vous devrez indiquer pour chaque chiffre de cette combinaison si le chiffre est plus grand(+), moins grand(-) ou exact(=) par rapport à votre combinaison à découvrir!");
		System.out.println("Pour cela vous renseignerez une combinaison de 4 caractères dont les valeurs seront + = ou -!");
		System.out.println("C'est parti!!");
		
//		Le joueur propose saisie sa combinaison secrète
		List<Integer> combiToFind = iDefChallDuel.playerCombi();
        
        System.out.println("Votre combinaison secrète à découvrir est " + combiToFind);
		System.out.println(
				"Maintenant c'est à moi de la deviner!");
		
		return combiToFind;
	}
	
	/**
	 * Méthode qui envoie le contenu du menu du Challenger
	 */
	public void menuStartChallenger() {
		
		System.out.println("Mode Challenger:");
		System.out.println("Bienvenue dans le mode Challenger!");
		System.out.println("Vous devez deviner une combinaison de 4 chiffres comprise entre 0000 et 9999!");
		System.out.println("Vous avez le droit à 5 tentatives!");
		System.out.println("Pour vous aider; à chaque proposition que vous ferez, j'indiquerai pour chaque chiffre de cette combinaison si le chiffre est plus grand(+), moins grand(-) ou exact(=) par rapport à la combinaison à découvrir!");
		System.out.println("Pour cela je renseignerai une combinaison de 4 caractères dont les valeurs seront + = ou -!");
		System.out.println("Vous êtes prêt? Allez! A vous de jouer!");
	}
	
	
	/**
	 * Méthode qui envoie le contenu du menu du jeu Duel
	 * @return retourne la combinaison secrète de plusieurs chiffres du joueur sous forme de liste d'Integer
	 * @throws IOException, lève une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.
	 */
	public List<Integer> menuStartDuel() throws IOException {
		System.out.println("Mode Duel");
		System.out.println("Bienvenue dans le mode Duel!");
		System.out.println("Je vais définir un nombre secret à 4 chiffres compris entre 0000 à 9999 que vous devrez deviner! ex: 8651");
		List<Integer> playerCombiToFind = iDefChallDuel.playerCombi();
        
        System.out.println("Votre combinaison secrète à découvrir est " + playerCombiToFind);
		System.out.println("Nous sommes en mode duel, le premier à découvrir la combinaison secrète de l'autre gagne!");
		System.out.println("A tour de rôle nous ferons nos propositions et l'adversaire(vous ou moi) devra pour chaque combinaison proposée si le chiffre est plus grand(+), moins grand(-) ou exact(=) par rapport à la combinaison à découvrir!");
		System.out.println("Pour cela nous renseignerons une combinaison de 4 caractères dont les valeurs seront + = ou -!");
		System.out.println("Vous êtes prêt? Allez je commence!");
		
		return playerCombiToFind;
	}

	
}
