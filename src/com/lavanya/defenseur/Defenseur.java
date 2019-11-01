package com.lavanya.defenseur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Defenseur {
	

	public static void menuStart() {
		System.out.println("Mode Défenseur");
		System.out.println("Bienvenue dans le mode Défenseur!");
		System.out.println("Veuillez définir un nombre à 4 chiffres compris entre 0000 à 9999!");
		System.out.println(
				"C'est fait? Garder bien en tête votre combinaison à 4 chiffres\nMaintenant c'est à moi de jouer!");
	}

//Méthode getRandom: l'ordinateur doit proposer au joueur une combinaison à 4 chiffres
	public static List<Integer> getRandom(int min, int max) {

		List<Integer> randomCombi = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			int j = min + random.nextInt(max - min);
			randomCombi.add(j);
		}
		

		return randomCombi;
	}

//Méthode pour créer une nouvelle combinaison à 4 chiffres par l'ordinateur
	public static Integer newRandom(int min, int max) {

		Random random = new Random();
		int j = min + random.nextInt(max - min);
		return j;
	}

//	Méthode où Le joueur va comparer la combi proposée à la sienne avec les valeurs + = ou -
//	Conversion de la réponse du joueur en String List ArrayList
	public static List<String> playerAnswer() {

		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison proposée: ex +,=,=,-(merci d'utiliser le même format de réponse que dans l'exemple)");
		String clue = sc.nextLine();
//		sc.close();	
		List<String> playerClue = Arrays.asList(clue.split(","));
		return playerClue;

	}

//	Méthode de type booléen pour définir si l'ordinateur a donné une combinaison 4 chiffres gagnante 
	public static boolean winAnswer(List<String> playerAnswer) {

		// Création d'une liste qui correspond à la réponse du joueur que l'ordi attend
		// pour gagner
		List<String> winCombi = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			winCombi.add("=");
		}

		boolean win = playerAnswer.equals(winCombi);
		return win;
	}
	
	public static List<Proposition> rangeArray() {
		
		List<Proposition> limit = new ArrayList<Proposition>();
				
		for (int i = 0; i < 4; i++) {
			limit.add(new Proposition(0,10));
		}
		
		return limit;
	}

//	L'ordinateur va générer une nouvelle combinaison en fonction des indices +,= ou - 
//	indiqué par le joueur
	public static List<Integer> runConditions(List<String> y, List<Integer> x, List<Proposition> range) {
		
				
		List<Integer> randomCombiNewTry = new ArrayList<>();
		
		for (int j = 0, k = 0, z = 0; j < y.size(); j++, k++) {
			
			if (y.get(j).contentEquals("+")) {
				range.get(j).setMin(x.get(k) + 1);
				int min = range.get(j).getMin();
				int max = range.get(j).getMax();
				
				z = newRandom(min, max);
			}

			if (y.get(j).contentEquals("-")) {
				range.get(j).setMax(x.get(k));
				int min = range.get(j).getMin();
				int max = range.get(j).getMax();
				
				z = newRandom(min, max);
			}
			
			

			if (y.get(j).contentEquals("=")) {
				z = x.get(k);
			}
			
			randomCombiNewTry.add(z);
		}
		
		
		return randomCombiNewTry;

	}
	
	
// Création de la boucle for pour donner à l'ordi la possibilité d'avoir 3
// essais dans le jeu
	public static void threeAttempt(List<Integer> x, List<Proposition> range) {
		for (int i=0; i<2; i++) {
			
			List<String> y = playerAnswer();
		
			
			if (winAnswer(y) == true){
				System.out.println("J'ai gagné!!!");
				break;
				
			}
			
						
			else {					
				
				x = runConditions(y, x, range);
				System.out.println("Voila ma nouvelle proposition " + x);
				
				if (i == 1) {
					List<String> yLast = playerAnswer();
				
				
					if (winAnswer(yLast) == false) {
						System.out.println("Vous avez gagné!!!");
					}
					else {
						System.out.println("J'ai gagné!!!");
					}
				}
				
			}
		}
	}

	
	public static void main(String[] args) {

		menuStart();
		
		List<Integer> x = getRandom(0, 10);
		
		System.out.println("Voici ma première proposition: " + x);
		
		List<Proposition> range = rangeArray();
		
		threeAttempt(x, range);

	}

}
