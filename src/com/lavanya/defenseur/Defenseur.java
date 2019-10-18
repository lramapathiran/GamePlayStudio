package com.lavanya.defenseur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Defenseur {
	
	
	
	//Méthode getRandom: l'ordinateur doit proposer au joueur une combinaison à 4 chiffres
	public static List<Integer> getRandom(int min, int max) {
		List<Integer> randomCombi = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			int j = min + random.nextInt(max - min);
			randomCombi.add(j);
		};
		
		
		return randomCombi;
	}
	
	
	
	
	public static void main(String[] args) {

		System.out.println("Mode Défenseur");
		System.out.println("Bienvenue dans le mode Défenseur!");
		System.out.println("Veuillez définir un nombre à 4 chiffres compris entre 0000 à 9999!");
		System.out.println("C'est fait? Garder bien en tête votre combinaison à 4 chiffres\nMaintenat c'est à moi de jouer!");
		
		System.out.println("Voici ma première proposition: " + getRandom(0,10));
		
		
		String randomResult = scanRandom.nextLine();
		
		List<
		
		//Le joueur va comparer la combi proposée à la sienne avec les valeurs + = ou -
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison proposée: ex +,=,=,-(merci d'utiliser le même format de réponse que dans l'exemple)");
		String clue = sc.nextLine();
		
		//Conversion de la réponse du joueur en String List ArrayList		
		List<String> playerClue = Arrays.asList(clue.split(","));
		
		
		System.out.println("Vous avez saisi : " + playerClue);
		
		
		for (int i =0, j = 0; i < playerClue.size(); i++, j++) {
			if (playerClue.get(i).contentEquals("+")) {
				List<Integer> randomCombi = new ArrayList<>();
				Random random = new Random();
				int m;
				int n;
				 
				for (int i = 0; i < 4; i++) {
					int j = random.nextInt(m > n && m < 10);
					randomCombi.add(j);
				};
				
				
				return randomCombi;
			}
				randomCombi.get(j);
			}
		}
		
		
		sc.close();
	}

}
