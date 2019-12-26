package com.lavanya.factoryDesign;

import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameFactory;
import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.menupage.MenuPage;
import com.lavanya.utile.Utile;



public class GameLaunch {
	
	final static Logger logger = LogManager.getLogger(GameLaunch.class.getName());
	static DefChallDuelInterface iDefChallDuel = new Utile();
		
	public static void main(String[] args) throws IOException {
			
		Scanner sc= new Scanner(System.in);
		Game defenseur = GameFactory.getGame(GameType.defenseur);
		Game challenger = GameFactory.getGame(GameType.challenger);
		Game duel = GameFactory.getGame(GameType.duel);
		 
		MenuPage.getInstance().menuStart();
		
		int answer = 0;
		do {
				
			String input = sc.nextLine();
			try {
				answer = Integer.parseInt(input);
				if (answer  < iDefChallDuel.intProperties("gameModeSelectionLowRange") || answer > iDefChallDuel.intProperties("gameModeSelectionHighRange")) {
					System.out.println("Votre saisie est invalide! Veuillez préciser votre choix avec un chiffre compris entre 1 et 3!");
					logger.error("Erreur de Saisie: mode non sélectionné avec un chiffre entre 1 et 3!");
				}
				switch (answer) {
				case 1:
					defenseur.gamePlay();
					break;
				case 2:				
					challenger.gamePlay();
					break;
				case 3:				
					duel.gamePlay();
					break;
				}
		
			} catch (NumberFormatException e) {
				System.out.println("Votre saisie est invalide! Veuillez préciser votre choix avec un chiffre compris entre 1 et 3!");
				logger.error("Saisie invalide: NumberFormatException!");
			}
			
		}while(answer  < iDefChallDuel.intProperties("gameModeSelectionLowRange") || answer > iDefChallDuel.intProperties("gameModeSelectionHighRange")  || true);
				
		sc.close();

	}
	 

}
