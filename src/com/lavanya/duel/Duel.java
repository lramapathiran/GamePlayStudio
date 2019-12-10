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
		
		MenuPage.getInstance().menuStartDuel();
		
		List<Integer> x1= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
		List<Integer> x2= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
		System.out.println("Voici ma première proposition: " + x1);
		List<Proposition> range = iDefDuel.rangeArray();
		List<Character> z1;
		List<Character> z2 = null;
		int i = 0;
			
		do {
			z1 = iDefDuel.playerAnswer();
	
			if (iDefChallDuel.winAnswer(z1) == true) {
				System.out.println("J'ai gagné!! Le Duel est terminé!");
				System.out.println("La réponse était: " + x2);
				break;
			}
				
			else {
				System.out.println("Mince! Je dois retenter ma chance, A vous de jouer maintenant!");
				if (i > 0) {System.out.println("Pour rappel, l'indice obtenu au tour précédent était: " + z2);}
			}
				
			List<Integer> y = iDefChallDuel.playerCombi();
			
			z2 = iDefChallDuel.computerPropositionCheck(x2,y);
			
			if (iDefChallDuel.winAnswer(z2) == true) {
				System.out.println("Vous avez gagné!! Le duel est terminé");
				break;
			}
			else {
				System.out.println("Désolé, retentez votre chance au tour prochain!");
				System.out.println("Voici quelques indications qui pourraient vous aider: " + z2);
				System.out.println("A mon tour!");
			}
				
			x1 = iDefDuel.runConditions(z1,x1,range);
			System.out.println("Voici ma nouvelle réponse: " + x1);
			i++;
				
		}while(iDefChallDuel.winAnswer(z1) == false || iDefChallDuel.winAnswer(z2) == false);
		
		iDefChallDuel.replay(GameType.duel);
		
		}	 

}
