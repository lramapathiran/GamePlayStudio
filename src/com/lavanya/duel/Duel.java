package com.lavanya.duel;

import java.io.IOException;
import java.util.List;

import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameType;
import com.lavanya.interfaces.ChallDuelInterface;
import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.interfaces.DefDuelInterface;
import com.lavanya.menupage.MenuPage;
import com.lavanya.utile.Proposition;
import com.lavanya.utile.Utile;
import com.lavanya.utile.UtileChallengerDuel;
import com.lavanya.utile.UtileDefenseurDuel;

/**
 * Duel est la classe qui permet d'exécuter le jeu en mode Duel.
 * Chaque participant(le joueur et l'ordinateur) définit une combinaison secrète de plusieurs chiffres définit par le fichier properties.
 * Tour à tour les participants vont proposer une réponse.
 * L'adversaire lui donnera des indications: + = ou - pour chaque chiffre de la combinaison proposée.
 * ce qui lui permettra de rectifier sa réponse au tour prochain.
 * Le premier à trouver gagne.
 * Les méthodes sont appelées grâce aux interfaces DefChallDuelInterface, DefDuelInterface et ChallDuelInterface.
 * La classe Duel est une concrete classe Factory qui découle de l'abstract classe Factory Game.
 * @author lavanya
 *
 */
public class Duel extends Game{
	
	static DefChallDuelInterface iDefChallDuel = new Utile();
	static DefDuelInterface iDefDuel = new UtileDefenseurDuel();
	static ChallDuelInterface iChallDuel = new UtileChallengerDuel();
	
	
	/**
	 * Méthode qui lance toutes les méthodes nécessaires au jeu Duel
	 * @throws génère une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.	 
	 */
	@Override
	public void gamePlay() throws IOException {
		
//		Appel à la méthode menuStartDuel() issue de la classe singleton MenuPage dont on récupère l'instance ici.
		List<Integer> playerCombiToFind = MenuPage.getInstance().menuStartDuel();
		
		List<Integer> computerProposition= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
				
		List<Integer> computerCombiToFind= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
		
//		Lance le mode développeur si dans le fichier properties, la valeur du boolean isDevActive est configurée à true
		if(iChallDuel.booleanProperties("isDevActive")) {	
			System.out.println("La combinaison secrète de l'ordinateur est: " + computerCombiToFind);
		}
	
		System.out.println("Maintenant que j'ai créé ma combinaison secrète, Voici ma première proposition pour deviner la vôtre: " + computerProposition);
		List<Proposition> range = iDefDuel.rangeArray();
		List<Character> validPlayerClue;
		List<Character> computerClue = null;
		int i = 0;
		
//		Boucle do/while jsuqu'à ce que l'un des participants(joueur ou ordinateur gagne
		do {
			List<Character> playerAnswerExpected = iDefChallDuel.computerPropositionCheck(playerCombiToFind, computerProposition);
			List<Character> playerClue = iDefDuel.playerAnswer();
			validPlayerClue = iDefDuel.validationPlayerClue(playerClue,playerAnswerExpected);
	
//			Bloc if/else pour vérifier si l'ordi a trouvé la bonne réponse donc gagné ou sinon(else) c'est au tour du joueur de jouer!
			if (iDefChallDuel.winAnswer(validPlayerClue)) {
				System.out.println("J'ai gagné!! Le Duel est terminé!");
				System.out.println("La réponse était: " + computerCombiToFind);
				break;
			}
				
			else {
				System.out.println("Mince! Je dois retenter ma chance, A vous de jouer maintenant!");
				if (i > iDefChallDuel.intProperties("firstAttempt")) {System.out.println("Pour rappel, l'indice obtenu au tour précédent était: " + computerClue);}
				
//				Lance le mode développeur si dans le fichier properties, la valeur du boolean isDevActive est configurée à true
				if(iChallDuel.booleanProperties("isDevActive")) {	
					System.out.println("La combinaison secrète de l'ordinateur est: " + computerCombiToFind);
				}
				List<Integer> playerProposition = iDefChallDuel.playerCombi();
				
				
				computerClue = iDefChallDuel.computerPropositionCheck(computerCombiToFind,playerProposition);		
			
			}

			
//			Bloc if/else pour vérifier si le joueur a trouvé la bonne réponse donc gagné ou sinon(else) c'est au tour de l'ordi de jouer!
			if (iDefChallDuel.winAnswer(computerClue)) {
				System.out.println("Vous avez gagné!! Le duel est terminé");
				break;
			}
			else {
				System.out.println("Désolé, retentez votre chance au tour prochain!");
				System.out.println("Voici quelques indications qui pourraient vous aider: " + computerClue);
				System.out.println("A mon tour!");
				
				computerProposition = iDefDuel.runConditions(validPlayerClue,computerProposition,range);
				System.out.println("Votre combinaison secrète à découvrir est pour rappel: " + playerCombiToFind);
				System.out.println("Voici ma nouvelle réponse: " + computerProposition);				
				
			}
				
			
			i++;
				
		}while(!iDefChallDuel.winAnswer(validPlayerClue) || !iDefChallDuel.winAnswer(computerClue));
		
		iDefChallDuel.replay(GameType.duel);
		
		}	 

}
