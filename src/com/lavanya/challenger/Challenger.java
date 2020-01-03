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
 * Challenger est la classe qui permet d'ex�cuter le jeu en mode Challenger.
 * Le joueur doit d�couvrir une combinaison secr�te.
 * Cette combinaison est compos�e de plusieurs chiffres et le joueur a droit � un nombre d�termin� de tentatives.
 * Ces param�tres sont d�finis dans le fichier properties.
 * A chaque tentative, le joueur propose sa combinaison de chiffres.
 * L'ordinateur lui donne des indications + = ou - pour chaque chiffre de la combinaison propos�e par le joueur.
 * Celui-ci pourra alors rectifier sa r�ponse au tour prochain.
 * Les m�thodes sont appel�es gr�ce aux interfaces DefChallDuelInterface et ChallDuelInterface.
 * La classe Challenger est une concrete classe Factory qui d�coule de l'abstract classe Factory Game.
 * @author lavanya
 *
 */
public class Challenger extends Game{
	
	static DefChallDuelInterface iDefChallDuel = new Utile();
	static ChallDuelInterface iChallDuel = new UtileChallengerDuel();
	

	/**
	 * M�thode qui lance toutes les m�thodes n�cessaires au jeu Challenger
	 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.	 
	 */
	@Override
	public void gamePlay() throws IOException {
		
//		Appel � la m�thode menuStartChallenger() issue de la classe singleton MenuPage dont on r�cup�re l'instance ici.
		MenuPage.getInstance().menuStartChallenger();
		
		List<Integer> combiToFind = iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
		
//		Lance le mode dev si dans le fichier properties, la valeur du boolean isDevActive est configur�e � true
		if(iChallDuel.booleanProperties("isDevActive")) {	
			System.out.println("La combinaison secr�te de l'ordinateur est: " + combiToFind);
		}
		List<Integer> playerProposition = iDefChallDuel.playerCombi();
			
//		Boucle for qui permet au joueur de faire plusieurs tentatives de propositions de combinaisons pour d�couvrir la combio secr�te de l'ordi jusqu'� ce qu'il trouve ou perde		
		for (int i=0, j=iDefChallDuel.intProperties("digitAttempt"); i<iDefChallDuel.intProperties("digitAttempt"); i++, j--) {
			
			
			System.out.println("Vous avez saisi: " + playerProposition);
			List<Character> computerClue = iDefChallDuel.computerPropositionCheck(combiToFind,playerProposition);
			
//			Bloc if/else si le joueur a trouv� la bonne r�ponse ou sinon(else) il doit entrer une nouvelle proposition
			if (iDefChallDuel.winAnswer(computerClue)){
				
				System.out.println("Vous avez gagn�");
				break;
			}
			
			else {
				System.out.println("Votre r�ponse est incorrect!");
				System.out.println("Voici quelques indications pour vous aider � trouver la r�ponse exacte: " + computerClue);
				System.out.println("Il vous reste " + j + " tentative(s)");
				
//				Active le mode d�veloppeur si isDevActive est configur� � true dans le fichier properties
				if(iChallDuel.booleanProperties("isDevActive")) {	
					System.out.println("La combinaison secr�te de l'ordinateur est: " + combiToFind);
				}
				playerProposition = iDefChallDuel.playerCombi();
				
//				instruction si on est au dernier tour du jeu
				if (i == iDefChallDuel.intProperties("lastAttempt")) {
					
					List<Character> lastComputerClue = iDefChallDuel.computerPropositionCheck(combiToFind,playerProposition);
					
					if (iDefChallDuel.winAnswer(lastComputerClue)) {
						System.out.println("F�licitations!! Vous avez gagn�!!");
						break;
					}
					
					else {
						System.out.println("J'ai gagn�!");
						System.out.println("La r�ponse �tait " + combiToFind);
						break;
					}
									
				}
			}			
			
		}
		
		iDefChallDuel.replay(GameType.challenger);			
	}	

}
