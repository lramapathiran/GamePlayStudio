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
		List<Integer> x = iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"), iDefChallDuel.intProperties("max"));
		
		
		System.out.println("Voici ma première proposition: " + x);
		List<Proposition> range = iDefDuel.rangeArray();
		
		
		for (int i=0, j=iDefChallDuel.intProperties("digitAttempt"); i<iDefChallDuel.intProperties("digitAttempt"); i++, j--) {
			
			List<Character> playerAnswerExpected = iDefChallDuel.computerPropositionCheck(combiToFind, x);
			List<Character> y = iDefDuel.playerAnswer();
			iDefDuel.validationPlayerClue(y,playerAnswerExpected);
			
		
			
			if (iDefChallDuel.winAnswer(y) == true){
				System.out.println("J'ai gagné!!!");
				break;
				
			}
			
						
			else {					
				System.out.println("Je n'ai pas eu la bonne réponse, il me reste " + j + " tentative(s)");
				x = iDefDuel.runConditions(y, x, range);
				System.out.println("La combinaison secrète à découvrir est pour rappel: " + combiToFind);
				System.out.println("Voila ma nouvelle proposition " + x);
				
				if (i == 3) {
					List<Character> yLast = iDefDuel.playerAnswer();
				
				
					if (iDefChallDuel.winAnswer(yLast) == false) {
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
