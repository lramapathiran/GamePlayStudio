package com.lavanya.defenseur;


import java.io.IOException;
import java.util.List;

import com.lavanya.utile.Proposition;
import com.lavanya.utile.Utile;
import com.lavanya.utile.UtileDefenseurDuel;
import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameType;
import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.interfaces.DefDuelInterface;
import com.lavanya.menupage.MenuPage;

/**
 * Defenseur est la classe qui permet d'exécuter le jeu en mode Défenseur.
 * Le joueur doit faire découvrir à l'ordinateur une combinaison secrète.
 * Cette combinaison est composée de plusieurs chiffres et l'ordinateur a droit à un certain nombre de tentatives.
 * Ces paramètres sont définis dans le fichier properties.
 * A chaque tentative, l'ordinateur propose sa combinaison de chiffres.
 * Le joueur donne des indications + = ou - pour chaque chiffre de la combinaison proposée par l'ordinateur.
 * Celui-ci pourra alors rectifier sa réponse au tour prochain.
 * Les méthodes sont appelées grâce aux interfaces DefChallDuelInterface et DefDuelInterface.
 * La classe Défenseur est une concrete classe Factory qui découle de l'abstract classe Factory Game.
 * @author lavanya
 *
 */

public class Defenseur extends Game{
	
	static DefChallDuelInterface iDefChallDuel = new Utile();
	static DefDuelInterface iDefDuel = new UtileDefenseurDuel();
	
	/**
	 * Méthode qui lance toutes les méthodes nécessaires au jeu Defenseur
	 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
	 */
	@Override
	public void gamePlay() throws IOException {
		
//		Appel à la méthode menuStartDefenseur() issue de la classe singleton MenuPage dont on récupère l'instance ici.
		List<Integer> combiToFind = MenuPage.getInstance().menuStartDefenseur();
		List<Integer> computerProposition = iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"), iDefChallDuel.intProperties("max"));
		
		
		System.out.println("Voici ma première proposition: " + computerProposition);
		List<Proposition> range = iDefDuel.rangeArray();
		
//		Boucle for qui permet à l'ordinateur de faire plusieurs tentatives de propositions de combinaisons pour découvrir la combio secrète du joueur jusqu'à ce qu'il trouve ou perde
		for (int i=0, j=iDefChallDuel.intProperties("digitAttempt"); i<iDefChallDuel.intProperties("digitAttempt"); i++, j--) {
			
			List<Character> playerAnswerExpected = iDefChallDuel.computerPropositionCheck(combiToFind, computerProposition);
			List<Character> playerClue = iDefDuel.playerAnswer();
			List<Character> validPlayerClue = iDefDuel.validationPlayerClue(playerClue,playerAnswerExpected);
			
		
//			Bloc if/else si l'ordinateur a trouvé la bonne réponse ou sinon(else) il doit entrer une nouvelle proposition	
			if (iDefChallDuel.winAnswer(validPlayerClue)){
				System.out.println("J'ai gagné!!!");
				break;
				
			}			
						
			else {					
				System.out.println("Je n'ai pas eu la bonne réponse, il me reste " + j + " tentative(s)");
				computerProposition = iDefDuel.runConditions(validPlayerClue, computerProposition, range);
				System.out.println("La combinaison secrète à découvrir est pour rappel: " + combiToFind);
				System.out.println("Voila ma nouvelle proposition " + computerProposition);
				
				
//				instruction si on est au dernier tour du jeu
				if (i == iDefChallDuel.intProperties("lastAttempt")) {
					List<Character> lastPlayerAnswerExpected = iDefChallDuel.computerPropositionCheck(combiToFind, computerProposition);
					List<Character> lastPlayerClue = iDefDuel.playerAnswer();
					List<Character> lastValidPlayerClue = iDefDuel.validationPlayerClue(lastPlayerClue,lastPlayerAnswerExpected);
				
				
					if (!iDefChallDuel.winAnswer(lastValidPlayerClue)) {
						System.out.println("Vous avez gagné!!!");
					}
					else {
						System.out.println("J'ai gagné!!!");
					}
				}
				
			}
		}
		iDefChallDuel.replay(GameType.defenseur);
	}
	
}
