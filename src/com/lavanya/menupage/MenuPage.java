package com.lavanya.menupage;

import java.io.IOException;
import java.util.List;

import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.utile.Utile;



/**
 * Singleton class MenuPage qui lance tous les menus associ�s � chaque classe (GameLaunch, Defenseur, Challenger ou Duel)
 * @author lavanya
 *
 */
public class MenuPage {
	
//	Variable statique  _instance de type singleton
	private static MenuPage _instance = null;
	DefChallDuelInterface iDefChallDuel = new Utile();
	
	/**
     * M�thode statique qui cr�e une instance de la classe MenuPage seulement si _instance n'a jamais �t� instanci� auparavant
     * @return l'unique instance de MenuPage
     * @throws IOException Si MenuPage a mal �t� instanci�
     */
	public synchronized static MenuPage getInstance() throws IOException {
        
		
//		Bloc if qui v�rifie si _instance de MenuPage a d�ja �t� instanci�
//    	si non, il est instanci� il passe de null � une instanciation en un objet de MenuPage
//    	si oui (_intance n'avait pas pour valeur nulle) il ne s'instancie pas de nouveau, il retourne la valeur que l'objet avait d�j�
		if (_instance == null)
            _instance = new MenuPage();
        
        return _instance;
    }
	
	/**
	 * M�thode qui envoie le contenu du menu principal quand on rentre dans le jeu GamePlayStudio
	 */
	public void menuStart() {
		System.out.println("Bienvenue � GAMEPLAY STUDIO");
		System.out.println("Menu");
		System.out.println("1: D�fenseur");
		System.out.println("2: Challenger");
		System.out.println("3: Duel");
		
		System.out.println("Quel mode de jeu vous int�resse? Veuillez pr�ciser votre choix en saisissant un chiffre 1 � 3: ");
	}
	
	/**
	 * M�thode qui envoie le contenu du menu du jeu D�fenseur
	 * @return retourne la combinaison secr�te de plusieurs chiffres du joueur sous forme de liste d'Integer
	 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
	 */
	public List<Integer> menuStartDefenseur() throws IOException {
		
		System.out.println("Mode D�fenseur");
		System.out.println("Bienvenue dans le mode D�fenseur!");	
		System.out.println("Vous devez me faire d�couvrir une combinaison de 4 chiffres comprise entre 0000 et 9999!");
		System.out.println("J'ai 5 tentatives pour y arriver");
		System.out.println("Pour m'aider; � chaque proposition que je ferai, vous devrez indiquer pour chaque chiffre de cette combinaison si le chiffre est plus grand(+), moins grand(-) ou exact(=) par rapport � votre combinaison � d�couvrir!");
		System.out.println("Pour cela vous renseignerez une combinaison de 4 caract�res dont les valeurs seront + = ou -!");
		System.out.println("C'est parti!!");
		
//		Le joueur propose saisie sa combinaison secr�te
		List<Integer> combiToFind = iDefChallDuel.playerCombi();
        
        System.out.println("Votre combinaison secr�te � d�couvrir est " + combiToFind);
		System.out.println(
				"Maintenant c'est � moi de la deviner!");
		
		return combiToFind;
	}
	
	/**
	 * M�thode qui envoie le contenu du menu du Challenger
	 */
	public void menuStartChallenger() {
		
		System.out.println("Mode Challenger:");
		System.out.println("Bienvenue dans le mode Challenger!");
		System.out.println("Vous devez deviner une combinaison de 4 chiffres comprise entre 0000 et 9999!");
		System.out.println("Vous avez le droit � 5 tentatives!");
		System.out.println("Pour vous aider; � chaque proposition que vous ferez, j'indiquerai pour chaque chiffre de cette combinaison si le chiffre est plus grand(+), moins grand(-) ou exact(=) par rapport � la combinaison � d�couvrir!");
		System.out.println("Pour cela je renseignerai une combinaison de 4 caract�res dont les valeurs seront + = ou -!");
		System.out.println("Vous �tes pr�t? Allez! A vous de jouer!");
	}
	
	
	/**
	 * M�thode qui envoie le contenu du menu du jeu Duel
	 * @return retourne la combinaison secr�te de plusieurs chiffres du joueur sous forme de liste d'Integer
	 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
	 */
	public List<Integer> menuStartDuel() throws IOException {
		System.out.println("Mode Duel");
		System.out.println("Bienvenue dans le mode Duel!");
		System.out.println("Le premier � d�couvrir la combinaison secr�te de l'autre gagne!");
		System.out.println("Pour se faire nous allons chacun d�finir sa combinaison secr�te puis tour � tour nous ferons une proposition jsuqu'� ce que l'un d'entre nous gagne!");
		System.out.println("Si la proposition est incorrecte, vous comme moi devrons donner des indications � l'autre qui l'aideront au tour prochain!");
		System.out.println("C'est � dire: pour chaque chiffre de la combinaison proposos�e il faudra renseigner si le chiffre est plus grand(+), moins grand(-) ou exact(=) par rapport � la combinaison � d�couvrir!");
		

		System.out.println("Veuillez d�finir votre combinaison secr�te!");
		List<Integer> playerCombiToFind = iDefChallDuel.playerCombi();
        
        System.out.println("Votre combinaison secr�te � d�couvrir est " + playerCombiToFind);
		
		
		System.out.println("A mon tour maintenant! Vous �tes pr�t?");
		System.out.println("Je vais maintenant d�finir mon nombre secret � 4 chiffres compris entre 0000 � 9999 que vous devrez deviner! ex: 8651");
		
		return playerCombiToFind;
	}

	
}
