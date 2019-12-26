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

public class Defenseur extends Game{
	
	static DefChallDuelInterface iDefChallDuel = new Utile();
	static DefDuelInterface iDefDuel = new UtileDefenseurDuel();
	
	
	@Override
	public void gamePlay() throws IOException {
		
		
		List<Integer> combiToFind = MenuPage.getInstance().menuStartDefenseur();
		List<Integer> computerProposition = iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"), iDefChallDuel.intProperties("max"));
		
		
		System.out.println("Voici ma première proposition: " + computerProposition);
		List<Proposition> range = iDefDuel.rangeArray();
		
		
		for (int i=0, j=iDefChallDuel.intProperties("digitAttempt"); i<iDefChallDuel.intProperties("digitAttempt"); i++, j--) {
			
			List<Character> playerAnswerExpected = iDefChallDuel.computerPropositionCheck(combiToFind, computerProposition);
			List<Character> playerClue = iDefDuel.playerAnswer();
			List<Character> validPlayerClue = iDefDuel.validationPlayerClue(playerClue,playerAnswerExpected);
			
		
			
			if (iDefChallDuel.winAnswer(validPlayerClue)){
				System.out.println("J'ai gagné!!!");
				break;
				
			}
			
						
			else {					
				System.out.println("Je n'ai pas eu la bonne réponse, il me reste " + j + " tentative(s)");
				computerProposition = iDefDuel.runConditions(validPlayerClue, computerProposition, range);
				System.out.println("La combinaison secrète à découvrir est pour rappel: " + combiToFind);
				System.out.println("Voila ma nouvelle proposition " + computerProposition);
				
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
