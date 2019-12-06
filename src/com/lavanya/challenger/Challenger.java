package com.lavanya.challenger;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.lavanya.common.ChallengerDuel;
import com.lavanya.common.DefenseurChallengerDuel;
import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameLaunch;
import com.lavanya.menupage.MenuPage;
import com.lavanya.utile.Utile;
import com.lavanya.utile.UtileChallengerDuel;


public class Challenger extends Game{
	
	static DefenseurChallengerDuel dCD = new Utile();
	static ChallengerDuel cD = new UtileChallengerDuel();
	

	@Override
	public void gamePlay() throws IOException {
		
		MenuPage.getInstance().menuStartChallenger();
		
		List<Integer> x = dCD.getRandom(dCD.properties("min"),dCD.properties("max"));
		List<Integer> y = cD.playerCombi();
			
		
		for (int i=0, j=4; i<dCD.properties("digitAttempt"); i++, j--) {
			
			System.out.println("Vous avez saisi: " + y);
			List<Character> computerAnswer = cD.computerAnswer(x,y);
			
			if (dCD.winAnswer(computerAnswer) == true){
				
				System.out.println("Vous avez gagné");
				break;
			}
			
			else {
				System.out.println("Votre réponse est incorrect!");
				System.out.println("Voici quelques indications pour vous aider à trouver la réponse exacte: " + computerAnswer);
				System.out.println("Il vous reste " + j + " tentative(s)");
				y = cD.playerCombi();
			}
		
			if (i == 3) {
				
				List<Character> computerAnswerLast = cD.computerAnswer(x,y);
				
				if (dCD.winAnswer(computerAnswerLast) == true) {
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
			
	}
	
	@Override
	public void replay() throws IOException {
			
		Scanner sc = new Scanner(System.in);
		System.out.println("Souhaitez-vous rejouer? O/N");
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
