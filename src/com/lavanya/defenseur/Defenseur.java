package com.lavanya.defenseur;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.lavanya.utile.Proposition;
import com.lavanya.utile.Utile;
import com.lavanya.utile.UtileDefenseurDuel;
import com.lavanya.common.DefenseurChallengerDuel;
import com.lavanya.common.DefenseurDuel;
import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameLaunch;
import com.lavanya.menupage.MenuPage;

public class Defenseur extends Game{
	
	static DefenseurChallengerDuel dCD = new Utile();
	static DefenseurDuel dD = new UtileDefenseurDuel();
	
	
	@Override
	public void gamePlay() throws IOException {
		
		
		List<Integer> combiToFind = MenuPage.getInstance().menuStartDefenseur();
		List<Integer> x = dCD.getRandom(dCD.properties("min"), dCD.properties("max"));
		
		
		System.out.println("Voici ma première proposition: " + x);
		List<Proposition> range = dD.rangeArray();
		
		
		for (int i=0, j=4; i<dCD.properties("digitAttempt"); i++, j--) {
			
			List<Character> playerAnswerExpected = dD.computerPropositionCheck(combiToFind, x);
			List<Character> y = dD.playerAnswer();
			dD.validationPlayerClue(y,playerAnswerExpected);
			
		
			
			if (dCD.winAnswer(y) == true){
				System.out.println("J'ai gagné!!!");
				break;
				
			}
			
						
			else {					
				System.out.println("Je n'ai pas eu la bonne réponse, il me reste " + j + " tentative(s)");
				x = dD.runConditions(y, x, range);
				System.out.println("La combinaison secrète à découvrir est pour rappel: " + combiToFind);
				System.out.println("Voila ma nouvelle proposition " + x);
				
				if (i == 3) {
					List<Character> yLast = dD.playerAnswer();
				
				
					if (dCD.winAnswer(yLast) == false) {
						System.out.println("Vous avez gagné!!!");
					}
					else {
						System.out.println("J'ai gagné!!!");
					}
				}
				
			}
		}
	}

	@Override
	public void replay() throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Souhaitez-vous rejouer? O/N(pour retouner au Menu Principal, choisissez N");
		String willReplay = sc.nextLine();
		
		switch(willReplay) {
			case "O":
				gamePlay();
				break;
			case "N":
				GameLaunch.main(null);
				break;
	
		}
	}
}
