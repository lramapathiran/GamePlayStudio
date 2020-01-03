package com.lavanya.challenger;

import java.io.IOException;
import java.util.List;

import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameType;
import com.lavanya.interfaces.ChallDuelInterface;
import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.menupage.MenuPage;
import com.lavanya.utile.Utile;
import com.lavanya.utile.UtileChallengerDuel;


/**
 * Challenger est la classe qui permet d'exécuter le jeu en mode Challenger.
 * Le joueur doit découvrir une combinaison secrète.
 * Cette combinaison est composée de plusieurs chiffres et le joueur a droit à un nombre déterminé de tentatives.
 * Ces paramètres sont définis dans le fichier properties.
 * A chaque tentative, le joueur propose sa combinaison de chiffres.
 * L'ordinateur lui donne des indications + = ou - pour chaque chiffre de la combinaison proposée par le joueur.
 * Celui-ci pourra alors rectifier sa réponse au tour prochain.
 * Les méthodes sont appelées grâce aux interfaces DefChallDuelInterface et ChallDuelInterface.
 * La classe Challenger est une concrete classe Factory qui découle de l'abstract classe Factory Game.
 * @author lavanya
 *
 */
public class Challenger extends Game{
	
	static DefChallDuelInterface iDefChallDuel = new Utile();
	static ChallDuelInterface iChallDuel = new UtileChallengerDuel();
	

	/**
	 * Méthode qui lance toutes les méthodes nécessaires au jeu Challenger
	 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.	 
	 */
	@Override
	public void gamePlay() throws IOException {
		
//		Appel à la méthode menuStartChallenger() issue de la classe singleton MenuPage dont on récupère l'instance ici.
		MenuPage.getInstance().menuStartChallenger();
		
		List<Integer> combiToFind = iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
		
//		Lance le mode dev si dans le fichier properties, la valeur du boolean isDevActive est configurée à true
		if(iChallDuel.booleanProperties("isDevActive")) {	
			System.out.println("La combinaison secrète de l'ordinateur est: " + combiToFind);
		}
		List<Integer> playerProposition = iDefChallDuel.playerCombi();
			
//		Boucle for qui permet au joueur de faire plusieurs tentatives de propositions de combinaisons pour découvrir la combio secrète de l'ordi jusqu'à ce qu'il trouve ou perde		
		for (int i=0, j=iDefChallDuel.intProperties("digitAttempt"); i<iDefChallDuel.intProperties("digitAttempt"); i++, j--) {
			
			
			System.out.println("Vous avez saisi: " + playerProposition);
			List<Character> computerClue = iDefChallDuel.computerPropositionCheck(combiToFind,playerProposition);
			
//			Bloc if/else si le joueur a trouvé la bonne réponse ou sinon(else) il doit entrer une nouvelle proposition
			if (iDefChallDuel.winAnswer(computerClue)){
				
				System.out.println("Vous avez gagné");
				break;
			}
			
			else {
				System.out.println("Votre réponse est incorrect!");
				System.out.println("Voici quelques indications pour vous aider à trouver la réponse exacte: " + computerClue);
				System.out.println("Il vous reste " + j + " tentative(s)");
				
//				Active le mode développeur si isDevActive est configuré à true dans le fichier properties
				if(iChallDuel.booleanProperties("isDevActive")) {	
					System.out.println("La combinaison secrète de l'ordinateur est: " + combiToFind);
				}
				playerProposition = iDefChallDuel.playerCombi();
				
//				instruction si on est au dernier tour du jeu
				if (i == iDefChallDuel.intProperties("lastAttempt")) {
					
					List<Character> lastComputerClue = iDefChallDuel.computerPropositionCheck(combiToFind,playerProposition);
					
					if (iDefChallDuel.winAnswer(lastComputerClue)) {
						System.out.println("Félicitations!! Vous avez gagné!!");
						break;
					}
					
					else {
						System.out.println("J'ai gagné!");
						System.out.println("La réponse était " + combiToFind);
						break;
					}
									
				}
			}			
			
		}
		
		iDefChallDuel.replay(GameType.challenger);			
	}	

}
