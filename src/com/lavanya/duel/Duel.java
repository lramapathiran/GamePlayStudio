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
 * Duel est la classe qui permet d'ex�cuter le jeu en mode Duel.
 * Chaque participant(le joueur et l'ordinateur) d�finit une combinaison secr�te de plusieurs chiffres d�finit par le fichier properties.
 * Tour � tour les participants vont proposer une r�ponse.
 * L'adversaire lui donnera des indications: + = ou - pour chaque chiffre de la combinaison propos�e.
 * ce qui lui permettra de rectifier sa r�ponse au tour prochain.
 * Le premier � trouver gagne.
 * Les m�thodes sont appel�es gr�ce aux interfaces DefChallDuelInterface, DefDuelInterface et ChallDuelInterface.
 * La classe Duel est une concrete classe Factory qui d�coule de l'abstract classe Factory Game.
 * @author lavanya
 *
 */
public class Duel extends Game{
	
	static DefChallDuelInterface iDefChallDuel = new Utile();
	static DefDuelInterface iDefDuel = new UtileDefenseurDuel();
	static ChallDuelInterface iChallDuel = new UtileChallengerDuel();
	
	
	/**
	 * M�thode qui lance toutes les m�thodes n�cessaires au jeu Duel
	 * @throws g�n�re une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.	 
	 */
	@Override
	public void gamePlay() throws IOException {
		
//		Appel � la m�thode menuStartDuel() issue de la classe singleton MenuPage dont on r�cup�re l'instance ici.
		List<Integer> playerCombiToFind = MenuPage.getInstance().menuStartDuel();
		
		List<Integer> computerProposition= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
				
		List<Integer> computerCombiToFind= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
		
//		Lance le mode d�veloppeur si dans le fichier properties, la valeur du boolean isDevActive est configur�e � true
		if(iChallDuel.booleanProperties("isDevActive")) {	
			System.out.println("La combinaison secr�te de l'ordinateur est: " + computerCombiToFind);
		}
	
		System.out.println("Maintenant que j'ai cr�� ma combinaison secr�te, Voici ma premi�re proposition pour deviner la v�tre: " + computerProposition);
		List<Proposition> range = iDefDuel.rangeArray();
		List<Character> validPlayerClue;
		List<Character> computerClue = null;
		int i = 0;
		
//		Boucle do/while jsuqu'� ce que l'un des participants(joueur ou ordinateur gagne
		do {
			List<Character> playerAnswerExpected = iDefChallDuel.computerPropositionCheck(playerCombiToFind, computerProposition);
			List<Character> playerClue = iDefDuel.playerAnswer();
			validPlayerClue = iDefDuel.validationPlayerClue(playerClue,playerAnswerExpected);
	
//			Bloc if/else pour v�rifier si l'ordi a trouv� la bonne r�ponse donc gagn� ou sinon(else) c'est au tour du joueur de jouer!
			if (iDefChallDuel.winAnswer(validPlayerClue)) {
				System.out.println("J'ai gagn�!! Le Duel est termin�!");
				System.out.println("La r�ponse �tait: " + computerCombiToFind);
				break;
			}
				
			else {
				System.out.println("Mince! Je dois retenter ma chance, A vous de jouer maintenant!");
				if (i > iDefChallDuel.intProperties("firstAttempt")) {System.out.println("Pour rappel, l'indice obtenu au tour pr�c�dent �tait: " + computerClue);}
				
//				Lance le mode d�veloppeur si dans le fichier properties, la valeur du boolean isDevActive est configur�e � true
				if(iChallDuel.booleanProperties("isDevActive")) {	
					System.out.println("La combinaison secr�te de l'ordinateur est: " + computerCombiToFind);
				}
				List<Integer> playerProposition = iDefChallDuel.playerCombi();
				
				
				computerClue = iDefChallDuel.computerPropositionCheck(computerCombiToFind,playerProposition);		
			
			}

			
//			Bloc if/else pour v�rifier si le joueur a trouv� la bonne r�ponse donc gagn� ou sinon(else) c'est au tour de l'ordi de jouer!
			if (iDefChallDuel.winAnswer(computerClue)) {
				System.out.println("Vous avez gagn�!! Le duel est termin�");
				break;
			}
			else {
				System.out.println("D�sol�, retentez votre chance au tour prochain!");
				System.out.println("Voici quelques indications qui pourraient vous aider: " + computerClue);
				System.out.println("A mon tour!");
				
				computerProposition = iDefDuel.runConditions(validPlayerClue,computerProposition,range);
				System.out.println("Votre combinaison secr�te � d�couvrir est pour rappel: " + playerCombiToFind);
				System.out.println("Voici ma nouvelle r�ponse: " + computerProposition);				
				
			}
				
			
			i++;
				
		}while(!iDefChallDuel.winAnswer(validPlayerClue) || !iDefChallDuel.winAnswer(computerClue));
		
		iDefChallDuel.replay(GameType.duel);
		
		}	 

}
