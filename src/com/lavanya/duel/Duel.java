package com.lavanya.duel;

import java.io.IOException;
import java.util.List;

import com.lavanya.common.ChallengerDuel;
import com.lavanya.common.DefenseurChallengerDuel;
import com.lavanya.common.DefenseurDuel;
import com.lavanya.factoryDesign.Game;
import com.lavanya.menupage.MenuPage;
import com.lavanya.utile.Proposition;
import com.lavanya.utile.Utile;
import com.lavanya.utile.UtileChallengerDuel;
import com.lavanya.utile.UtileDefenseurDuel;


public class Duel extends Game{
	
	static DefenseurChallengerDuel dCD = new Utile();
	static DefenseurDuel dD = new UtileDefenseurDuel();
	static ChallengerDuel cD = new UtileChallengerDuel();
	
	
	@Override
	public void gamePlay() throws IOException {
		
		MenuPage.getInstance().menuStartDuel();
		
		List<Integer> x1= dCD.getRandom(dCD.properties("min"),dCD.properties("max"));
		List<Integer> x2= dCD.getRandom(dCD.properties("min"),dCD.properties("max"));
		System.out.println("Voici ma première proposition: " + x1);
		List<Proposition> range = dD.rangeArray();
		List<String> z1;
		List<String> z2 = null;
		int i = 0;
			
		do {
			z1 = dD.playerAnswer();
	
			if (dCD.winAnswer(z1) == true) {
				System.out.println("J'ai gagné!! Le Duel est terminé!");
				System.out.println("La réponse était: " + x2);
				break;
			}
				
			else {
				System.out.println("Mince! Je dois retenter ma chance, A vous de jouer maintenant!");
				if (i > 0) {System.out.println("Pour rappel, l'indice obtenu au tour précédent était: " + z2);}
			}
				
			List<Integer> y = cD.playerCombi();
			
			z2 = cD.computerAnswer(x2,y);
			
			if (dCD.winAnswer(z2) == true) {
				System.out.println("Vous avez gagné!! Le duel est terminé");
				break;
			}
			else {
				System.out.println("Désolé, retentez votre chance au tour prochain!");
				System.out.println("Voici quelques indications qui pourraient vous aider: " + z2);
				System.out.println("A mon tour!");
			}
				
			x1 = dD.runConditions(z1,x1,range);
			System.out.println("Voici ma nouvelle réponse: " + x1);
			i++;
				
			}while(dCD.winAnswer(z1) == false || dCD.winAnswer(z2) == false);
//			
		}

}
