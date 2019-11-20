package com.lavanya.defenseur;


import java.io.IOException;
import java.util.List;

import com.lavanya.utile.Utile;
import com.lavanya.utile.UtileDefenseurDuel;
import com.lavanya.common.DefenseurChallengerDuel;
import com.lavanya.common.DefenseurDuel;

public class Defenseur {
	
	static DefenseurChallengerDuel dCD = new Utile();
	static DefenseurDuel dD = new UtileDefenseurDuel();
	
	
	public static void menuStart() {
		System.out.println("Mode D�fenseur");
		System.out.println("Bienvenue dans le mode D�fenseur!");
		System.out.println("Veuillez d�finir un nombre � 4 chiffres compris entre 0000 � 9999!");
		System.out.println(
				"C'est fait? Garder bien en t�te votre combinaison � 4 chiffres\nMaintenant c'est � moi de jouer!");
	}
	
	
// Cr�ation de la boucle for pour donner � l'ordi la possibilit� d'avoir 3
// essais dans le jeu
	public static void threeAttempt(List<Integer> x, List<Proposition> range) throws IOException {
		for (int i=0; i<dCD.properties("digitAttempt"); i++) {
			
			List<String> y = dD.playerAnswer();
		
			
			if (dCD.winAnswer(y) == true){
				System.out.println("J'ai gagn�!!!");
				break;
				
			}
			
						
			else {					
				
				x = dD.runConditions(y, x, range);
				System.out.println("Voila ma nouvelle proposition " + x);
				
				if (i == 1) {
					List<String> yLast = dD.playerAnswer();
				
				
					if (dCD.winAnswer(yLast) == false) {
						System.out.println("Vous avez gagn�!!!");
					}
					else {
						System.out.println("J'ai gagn�!!!");
					}
				}
				
			}
		}
	}

	
	public static void main(String[] args) throws IOException {

		menuStart();
		
		List<Integer> x = dCD.getRandom(dCD.properties("min"), dCD.properties("max"));
		
		System.out.println("Voici ma premi�re proposition: " + x);
		
		List<Proposition> range = dD.rangeArray();
		
		threeAttempt(x, range);

	}

}
