package com.lavanya.duel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Duel {
	
	public static void menuStart() {
		System.out.println("Mode Duel");
		System.out.println("Bienvenue dans le mode Duel!");
		System.out.println("Je vais définir un nombre secret à 4 chiffres! ex: 8,6,5,1");
		System.out.println("Veuillez définir également votre nombre secret à 4 chiffres compris entre 0,0,0,0 à 9,9,9,9!");
		System.out.println(
				"C'est fait? Garder bien en tête votre combinaison à 4 chiffres");
		System.out.println("Nous sommes en mode duel, le premier à découvrir la combinaison secrète de l'autre gagne!");
		System.out.println("Vous êtes prêt? Allez je commence!");
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
		
		public static List<Proposition> rangeArray() {
			
			List<Proposition> limit = new ArrayList<Proposition>();
					
			for (int i = 0; i < 4; i++) {
				limit.add(new Proposition(0,10));
			}
			
			return limit;
		}
		
//		Méthode pour créer une nouvelle combinaison à 4 chiffres par l'ordinateur
		public static Integer newRandom(int min, int max) {

			Random random = new Random();
			int j = min + random.nextInt(max - min);
			return j;
		}

		
//		L'ordinateur va générer une nouvelle combinaison en fonction des indices +,= ou - 
//		indiqué par le joueur
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

		
//		Méthode où Le joueur va comparer la combi proposée par l'ordinateur à la sienne avec les valeurs + = ou -
//		Conversion de la réponse du joueur en String List ArrayList
		public static List<String> playerAnswer() {

			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison proposée: ex +,=,=,-(merci d'utiliser le même format de réponse que dans l'exemple)");
			String clue = sc.nextLine();
//			sc.close();	
			List<String> playerClue = Arrays.asList(clue.split(","));
			return playerClue;

		}

//		Méthode de type booléen pour définir si l'ordinateur ou le joueur a donné une combinaison 4 chiffres gagnante 
		public static boolean winAnswer(List<String> Answer) {

			// Création d'une liste qui correspond à la réponse du joueur ou de l'ordi attendu
			// pour que l'un ou l'autre gagne!
			List<String> winCombi = new ArrayList<>();
			
			for (int i = 0; i < 4; i++) {
				winCombi.add("=");
			}

			boolean win = Answer.equals(winCombi);
			return win;
		}
		
//		Méthode pour que le joueur fasse ses propositions de combinaisons
//		et conversion de ses propositions en array
		public static List<Integer> playerCombi() {

			List<Integer> answerInt = new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Veuillez saisir votre proposition, ex: 0,9,1,7 (Utilisez le même format que dans l'exemple):");
			String input = sc.nextLine();
			List<String> answerString = Arrays.asList(input.split(","));

			for (String str : answerString) {
				answerInt.add(Integer.parseInt(str));
			}

			return answerInt;

		}

//		Méthode pour comparer la réponse du joueur à celle que l'ordinateur à générer et indication (+,=,-)
//		pour que le joueur puisse proposer une nouvelle réponse
		public static List<String> computerAnswer(List<Integer> x, List<Integer> y) {

			List<String> computerClue = new ArrayList<>();
			for (int i = 0, j = 0; i < 4; i++, j++) {

				if (x.get(i) > y.get(j)) {
					computerClue.add("+");
				}

				if (x.get(i) < y.get(j)) {
					computerClue.add("-");
				}

				if (x.get(i) == y.get(j)) {
					computerClue.add("=");
				}

			}

			return computerClue;
		}
		
		public static void executeDuel(List<Integer> x1, List<Integer> x2, List<Proposition> range) {
			
			List<String> z1;
			List<String> z2 = null;
			int i = 0;
			
			do {
				z1 = playerAnswer();
				if (winAnswer(z1) == true) {
					System.out.println("J'ai gagné!! Le Duel est terminé!");
					System.out.println("La réponse était: " + x2);
					break;
				}
				
				else {
					System.out.println("Mince! Je dois retenter ma chance, A vous de jouer maintenant!");
					if (i > 0) {System.out.println("Pour rappel, l'indice obtenu au tour précédent était: " + z2);}
				}
				
				List<Integer> y = playerCombi();
				z2 = computerAnswer(x2,y);
				if (winAnswer(z2) == true) {
					System.out.println("Vous avez gagné!! Le duel est terminé");
					break;
				}
				else {
					System.out.println("Désolé, retentez votre chance au tour prochain!");
					System.out.println("Voici quelques indications qui pourraient vous aider: " + z2);
					System.out.println("A mon tour!");
				}
				
				x1 = runConditions(z1,x1,range);
				System.out.println("Voici ma nouvelle réponse: " + x1);
				i++;
				
			}while(winAnswer(z1) == false || winAnswer(z2) == false);
//			
		}
		
	public static void main(String[] args) {
		
		menuStart();
		List<Integer> x1= getRandom(0,10);
		List<Integer> x2= getRandom(0,10);
		System.out.println("Voici ma première proposition: " + x1);
		List<Proposition> range = rangeArray();
		executeDuel(x1, x2, range);
		

	}

}
