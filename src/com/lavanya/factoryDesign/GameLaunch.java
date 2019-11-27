package com.lavanya.factoryDesign;

import java.io.IOException;
import java.util.Scanner;
import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameFactory;
import com.lavanya.menupage.MenuPage;



public class GameLaunch {

	public static void main(String[] args) throws IOException {
	
		Scanner sc= new Scanner(System.in);
		 
		MenuPage.getInstance().menuStart();
		
		String input = sc.nextLine();
		Integer answer = Integer.parseInt(input);
		
		
		switch (answer) {
			case 1:
				Game defenseur = GameFactory.getGame("Defenseur");
				defenseur.gamePlay();
				break;
			case 2:
				Game challenger = GameFactory.getGame("Challenger");
				challenger.gamePlay();				
				break;
		}
		
		sc.close();

	}
	 

}
