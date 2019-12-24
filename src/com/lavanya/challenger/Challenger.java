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


public class Challenger extends Game{
	
	static DefChallDuelInterface iDefChallDuel = new Utile();
	static ChallDuelInterface iChallDuel = new UtileChallengerDuel();
	

	@Override
	public void gamePlay() throws IOException {
		
		MenuPage.getInstance().menuStartChallenger();
		
		List<Integer> x = iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
		
		if(iChallDuel.booleanProperties("isDevActive")) {	
			System.out.println("La combinaison secrète de l'ordinateur est: " + x);
		}
		List<Integer> y = iDefChallDuel.playerCombi();
			
		
		for (int i=0, j=iDefChallDuel.intProperties("digitAttempt"); i<iDefChallDuel.intProperties("digitAttempt"); i++, j--) {
			
			
			System.out.println("Vous avez saisi: " + y);
			List<Character> computerAnswer = iDefChallDuel.computerPropositionCheck(x,y);
			
			if (iDefChallDuel.winAnswer(computerAnswer)){
				
				System.out.println("Vous avez gagné");
				break;
			}
			
			else {
				System.out.println("Votre réponse est incorrect!");
				System.out.println("Voici quelques indications pour vous aider à trouver la réponse exacte: " + computerAnswer);
				System.out.println("Il vous reste " + j + " tentative(s)");
				if(iChallDuel.booleanProperties("isDevActive")) {	
					System.out.println("La combinaison secrète de l'ordinateur est: " + x);
				}
				y = iDefChallDuel.playerCombi();
			}
		
			if (i == iDefChallDuel.intProperties("lastAttempt")) {
				
				List<Character> computerAnswerLast = iDefChallDuel.computerPropositionCheck(x,y);
				
				if (iDefChallDuel.winAnswer(computerAnswerLast)) {
					System.out.println("Félicitations!! Vous avez gagné!!");
					break;
				}
				
				else {
					System.out.println("J'ai gagné!");
					System.out.println("La réponse était " + x);
					break;
				}
								
			}
			
			
		}
		
		iDefChallDuel.replay(GameType.challenger);
			
	}
	

}
