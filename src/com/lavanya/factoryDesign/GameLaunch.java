package com.lavanya.factoryDesign;

import java.io.IOException;
import java.util.Scanner;
import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameFactory;



public class GameLaunch {

	public static void main(String[] args) throws IOException {
	
		Scanner sc= new Scanner(System.in);
		 
		System.out.println("Bienvenue à GAMEPLAY STUDIO");
		System.out.println("Menu");
		System.out.println("1: Défenseur");
		System.out.println("2: Challenger");
		System.out.println("3: Duel");
		System.out.println("4: Quitter l'application");
		
		System.out.println("A quel mode souhaitez-vous jouer? Veuillez préciser votre choix en saisissant un chiffre 1 à 4: ");
		String input = sc.nextLine();
		Integer answer = Integer.parseInt(input);
		
		
		switch (answer) {
			case 1:
				Game defenseur = GameFactory.getGame("Defenseur");
				defenseur.menuStart();
				defenseur.gamePlay();
				break;
			case 2:
				Game challenger = GameFactory.getGame("Challenger");
				challenger.menuStart();
				challenger.gamePlay();				
				break;
		}
		
		sc.close();

	}
	 

}
