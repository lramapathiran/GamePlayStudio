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
		
		List<Integer> playerCombiToFind = MenuPage.getInstance().menuStartDuel();
		
		List<Integer> computerProposition= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
				
		List<Integer> computerCombiToFind= iDefChallDuel.getRandom(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max"));
	
		System.out.println("Voici ma première proposition: " + computerProposition);
		List<Proposition> range = iDefDuel.rangeArray();
		List<Character> validPlayerClue;
		List<Character> computerClue = null;
		int i = 0;
			
		do {
			List<Character> playerAnswerExpected = iDefChallDuel.computerPropositionCheck(playerCombiToFind, computerProposition);
			List<Character> playerClue = iDefDuel.playerAnswer();
			validPlayerClue = iDefDuel.validationPlayerClue(playerClue,playerAnswerExpected);
	
			if (iDefChallDuel.winAnswer(validPlayerClue)) {
				System.out.println("J'ai gagné!! Le Duel est terminé!");
				System.out.println("La réponse était: " + computerCombiToFind);
				break;
			}
				
			else {
				System.out.println("Mince! Je dois retenter ma chance, A vous de jouer maintenant!");
				if (i > iDefChallDuel.intProperties("firstAttempt")) {System.out.println("Pour rappel, l'indice obtenu au tour précédent était: " + computerClue);}
			}
			
			if(iChallDuel.booleanProperties("isDevActive")) {	
				System.out.println("La combinaison secrète de l'ordinateur est: " + computerCombiToFind);
			}
			List<Integer> playerProposition = iDefChallDuel.playerCombi();
			
			
			computerClue = iDefChallDuel.computerPropositionCheck(computerCombiToFind,playerProposition);
			
			if (iDefChallDuel.winAnswer(computerClue)) {
				System.out.println("Vous avez gagné!! Le duel est terminé");
				break;
			}
			else {
				System.out.println("Désolé, retentez votre chance au tour prochain!");
				System.out.println("Voici quelques indications qui pourraient vous aider: " + computerClue);
				System.out.println("A mon tour!");
			}
				
			computerProposition = iDefDuel.runConditions(validPlayerClue,computerProposition,range);
			System.out.println("La combinaison secrète à découvrir est pour rappel: " + playerCombiToFind);
			System.out.println("Voici ma nouvelle réponse: " + computerProposition);
			i++;
				
		}while(!iDefChallDuel.winAnswer(validPlayerClue) || !iDefChallDuel.winAnswer(computerClue));
		
		iDefChallDuel.replay(GameType.duel);
		
		}	 

}
