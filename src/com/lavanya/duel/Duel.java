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


public class Duel extends Game{
	
	static DefChallDuelInterface iDefChallDuel = new Utile();
	static DefDuelInterface iDefDuel = new UtileDefenseurDuel();
	static ChallDuelInterface iChallDuel = new UtileChallengerDuel();
	
	
	@Override
	public void gamePlay() throws IOException {
		
		List<Integer> combiToFind = MenuPage.getInstance().menuStartDuel();
		
		List<Integer> x1= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
				
		List<Integer> x2= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
	
		System.out.println("Voici ma premi�re proposition: " + x1);
		List<Proposition> range = iDefDuel.rangeArray();
		List<Character> playerClue;
		List<Character> z2 = null;
		int i = 0;
			
		do {
			List<Character> playerAnswerExpected = iDefChallDuel.computerPropositionCheck(combiToFind, x1);
			List<Character> z1 = iDefDuel.playerAnswer();
			playerClue = iDefDuel.validationPlayerClue(z1,playerAnswerExpected);
	
			if (iDefChallDuel.winAnswer(playerClue)) {
				System.out.println("J'ai gagn�!! Le Duel est termin�!");
				System.out.println("La r�ponse �tait: " + x2);
				break;
			}
				
			else {
				System.out.println("Mince! Je dois retenter ma chance, A vous de jouer maintenant!");
				if (i > iDefChallDuel.intProperties("firstAttempt")) {System.out.println("Pour rappel, l'indice obtenu au tour pr�c�dent �tait: " + z2);}
			}
			
			if(iChallDuel.booleanProperties("isDevActive")) {	
				System.out.println("La combinaison secr�te de l'ordinateur est: " + x2);
			}
			List<Integer> y = iDefChallDuel.playerCombi();
			
			
			z2 = iDefChallDuel.computerPropositionCheck(x2,y);
			
			if (iDefChallDuel.winAnswer(z2)) {
				System.out.println("Vous avez gagn�!! Le duel est termin�");
				break;
			}
			else {
				System.out.println("D�sol�, retentez votre chance au tour prochain!");
				System.out.println("Voici quelques indications qui pourraient vous aider: " + z2);
				System.out.println("A mon tour!");
			}
				
			x1 = iDefDuel.runConditions(playerClue,x1,range);
			System.out.println("La combinaison secr�te � d�couvrir est pour rappel: " + combiToFind);
			System.out.println("Voici ma nouvelle r�ponse: " + x1);
			i++;
				
		}while(!iDefChallDuel.winAnswer(playerClue) || !iDefChallDuel.winAnswer(z2));
		
		iDefChallDuel.replay(GameType.duel);
		
		}	 

}
