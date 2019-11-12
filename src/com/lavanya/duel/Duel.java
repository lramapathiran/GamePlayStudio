package com.lavanya.duel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Duel {
	
	public static void menuStart() {
		System.out.println("Mode Duel");
		System.out.println("Bienvenue dans le mode Duel!");
		System.out.println("Veuillez définir un nombre à 4 chiffres compris entre 0000 à 9999!");
		System.out.println(
				"C'est fait? Garder bien en tête votre combinaison à 4 chiffres\nMaintenant c'est à moi de jouer!");
	}
	
	//Méthode getRandom: l'ordinateur doit proposer au joueur une combinaison à 4 chiffres
		public static List<Integer> getRandom(int min, int max) throws IOException {

			
			List<Integer> randomCombi = new ArrayList<>();
			Random random = new Random();

			for (int i = 0; i < 4; i++) {
				int j = min + random.nextInt(max - min);
				randomCombi.add(j);
				}

			return randomCombi;
		}
		
		
		
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
