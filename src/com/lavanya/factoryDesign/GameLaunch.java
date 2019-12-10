package com.lavanya.factoryDesign;

import java.io.IOException;
import java.util.Scanner;
import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameFactory;
import com.lavanya.menupage.MenuPage;



public class GameLaunch {

	public static void main(String[] args) throws IOException {
	
		Scanner sc= new Scanner(System.in);
		Game defenseur = GameFactory.getGame(GameType.defenseur);
		Game challenger = GameFactory.getGame(GameType.challenger);
		 
		MenuPage.getInstance().menuStart();
		
		String input = sc.nextLine();
		Integer answer = Integer.parseInt(input);
		
		
		switch (answer) {
			case 1:
				defenseur.gamePlay();
				break;
			case 2:				
				challenger.gamePlay();
				break;
		}
		
		sc.close();

	}
	 

}
