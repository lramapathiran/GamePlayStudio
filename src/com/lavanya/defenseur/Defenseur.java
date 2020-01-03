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
 * Defenseur est la classe qui permet d'ex�cuter le jeu en mode D�fenseur.
 * Le joueur doit faire d�couvrir � l'ordinateur une combinaison secr�te.
 * Cette combinaison est compos�e de plusieurs chiffres et l'ordinateur a droit � un certain nombre de tentatives.
 * Ces param�tres sont d�finis dans le fichier properties.
 * A chaque tentative, l'ordinateur propose sa combinaison de chiffres.
 * Le joueur donne des indications + = ou - pour chaque chiffre de la combinaison propos�e par l'ordinateur.
 * Celui-ci pourra alors rectifier sa r�ponse au tour prochain.
 * Les m�thodes sont appel�es gr�ce aux interfaces DefChallDuelInterface et DefDuelInterface.
 * La classe D�fenseur est une concrete classe Factory qui d�coule de l'abstract classe Factory Game.
 * @author lavanya
 *
 */

public class Defenseur extends Game{
	
	static DefChallDuelInterface iDefChallDuel = new Utile();
	static DefDuelInterface iDefDuel = new UtileDefenseurDuel();
	
	/**
	 * M�thode qui lance toutes les m�thodes n�cessaires au jeu Defenseur
	 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
	 */
	@Override
	public void gamePlay() throws IOException {
		
//		Appel � la m�thode menuStartDefenseur() issue de la classe singleton MenuPage dont on r�cup�re l'instance ici.
		List<Integer> combiToFind = MenuPage.getInstance().menuStartDefenseur();
		List<Integer> computerProposition = iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"), iDefChallDuel.intProperties("max"));
		
		
		System.out.println("Voici ma premi�re proposition: " + computerProposition);
		List<Proposition> range = iDefDuel.rangeArray();
		
//		Boucle for qui permet � l'ordinateur de faire plusieurs tentatives de propositions de combinaisons pour d�couvrir la combio secr�te du joueur jusqu'� ce qu'il trouve ou perde
		for (int i=0, j=iDefChallDuel.intProperties("digitAttempt"); i<iDefChallDuel.intProperties("digitAttempt"); i++, j--) {
			
			List<Character> playerAnswerExpected = iDefChallDuel.computerPropositionCheck(combiToFind, computerProposition);
			List<Character> playerClue = iDefDuel.playerAnswer();
			List<Character> validPlayerClue = iDefDuel.validationPlayerClue(playerClue,playerAnswerExpected);
			
		
//			Bloc if/else si l'ordinateur a trouv� la bonne r�ponse ou sinon(else) il doit entrer une nouvelle proposition	
			if (iDefChallDuel.winAnswer(validPlayerClue)){
				System.out.println("J'ai gagn�!!!");
				break;
				
			}			
						
			else {					
				System.out.println("Je n'ai pas eu la bonne r�ponse, il me reste " + j + " tentative(s)");
				computerProposition = iDefDuel.runConditions(validPlayerClue, computerProposition, range);
				System.out.println("La combinaison secr�te � d�couvrir est pour rappel: " + combiToFind);
				System.out.println("Voila ma nouvelle proposition " + computerProposition);
				
				
//				instruction si on est au dernier tour du jeu
				if (i == iDefChallDuel.intProperties("lastAttempt")) {
					List<Character> lastPlayerAnswerExpected = iDefChallDuel.computerPropositionCheck(combiToFind, computerProposition);
					List<Character> lastPlayerClue = iDefDuel.playerAnswer();
					List<Character> lastValidPlayerClue = iDefDuel.validationPlayerClue(lastPlayerClue,lastPlayerAnswerExpected);
				
				
					if (!iDefChallDuel.winAnswer(lastValidPlayerClue)) {
						System.out.println("Vous avez gagn�!!!");
					}
					else {
						System.out.println("J'ai gagn�!!!");
					}
				}
				
			}
		}
		iDefChallDuel.replay(GameType.defenseur);
	}
	
}
