package com.lavanya.factoryDesign;

import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameFactory;
import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.menupage.MenuPage;
import com.lavanya.utile.Utile;



/**
 * Méthode qui va utiliser la classe GameFactory pour accéder à la classe abstraite Game et ainsi exécuter les classes concrètes Defenseur Duel ou Challenger. 
 * @author lavanya
 *
 */
public class GameLaunch {
	
	final static Logger logger = LogManager.getLogger(GameLaunch.class.getName());
	static DefChallDuelInterface iDefChallDuel = new Utile();
		
	
	/**
	 * Méthode main qui va executer le jeu Gameplay Studio
	 * @param args un array d'arguments de lignes de commande nécessaire à l'exécution de l'application
	 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
	 */
	public static void main(String[] args) throws IOException {
			
		Scanner sc= new Scanner(System.in);
		
//		va accéder au factory producer GameFactory et récupérer l'objet de type Defenseur. 
		Game defenseur = GameFactory.getGame(GameType.defenseur);
//		va accéder au factory producer GameFactory et récupérer l'objet de type Challenger.		
		Game challenger = GameFactory.getGame(GameType.challenger);
//		va accéder au factory producer GameFactory et récupérer l'objet de type Duel.
		Game duel = GameFactory.getGame(GameType.duel);
		 
//		Accès à la méthode menuStart() issue de la classe singleton MenuPage dont on récupère l'instance ici.
		MenuPage.getInstance().menuStart();
		
//		Boucle do/while qui demandera au joueur de saisir le mode de jeu auquel il souhaite jouer tant que sa saisie sera incorrecte
		int answer = 0;
		do {
				
			String input = sc.nextLine();
			
//			bloc try/catch qui gère avec des messages/logs d'erreur une exception de type NumberFormatException
//			ou une saisie qui ne correspond à aucun choix proposé
			try {
				answer = Integer.parseInt(input);
				if (answer  < iDefChallDuel.intProperties("gameModeSelectionLowRange") || answer > iDefChallDuel.intProperties("gameModeSelectionHighRange")) {
					logger.error("Erreur de Saisie: mode non sélectionné avec un chiffre entre 1 et 3!");
					System.out.println("Votre saisie est invalide! Veuillez préciser votre choix avec un chiffre compris entre 1 et 3!");
				}
				
//				switch pour appeler la méthode gamePlay() de Defenseur, Challenger ou Duel
				switch (answer) {
				case 1:
					defenseur.gamePlay();
					break;
				case 2:				
					challenger.gamePlay();
					break;
				case 3:				
					duel.gamePlay();
					break;
				}
		
			} catch (NumberFormatException e) {
				logger.error("Saisie invalide: NumberFormatException!");
				System.out.println("Votre saisie est invalide! Veuillez préciser votre choix avec un chiffre compris entre 1 et 3!");
			}
			
		}while(answer  < iDefChallDuel.intProperties("gameModeSelectionLowRange") || answer > iDefChallDuel.intProperties("gameModeSelectionHighRange")  || true);
				
		sc.close();

	}
	 

}
