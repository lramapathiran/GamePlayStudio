package com.lavanya.challenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Challenger {

	public static void menuStart() {
		System.out.println("Mode Challenger:");
		System.out.println("Bienvenue dans le mode Challenger!");
		System.out.println("Vous devez deviner une combinaison de 4 chiffres comprise entre 0,0,0,0 et 9,9,9,9!");
		System.out.println("Vous avez le droit à 3 tentatives!");
		System.out.println("Vous êtes prêt? Allez! A vous de jouer!");
	}

	// Méthode getRandom: l'ordinateur doit créer une combinaison à 4 chiffres
	public static List<Integer> getRandom(int min, int max) {

		List<Integer> randomCombi = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			int j = min + random.nextInt(max - min);
			randomCombi.add(j);
		}

		return randomCombi;
	}

//	Méthode pour que le joueur fasse ses propositions de combinaisons
//	et conversion de ses propositions en array
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

//	Méthode pour comparer la réponse du joueur à celle que l'ordinateur à générer et indication (+,=,-)
//	pour que le joueur puisse proposer une nouvelle réponse
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

//	Méthode de type booléen pour définir si le joueur a donné une combinaison 4 chiffres gagnante 
	public static boolean winAnswer(List<String> computerAnswer) {

		// Création d'une liste qui correspond à la réponse du joueur que l'ordi attend
		// pour gagner
		List<String> winCombi = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			winCombi.add("=");
		}

		boolean win = computerAnswer.equals(winCombi);
		return win;
	}

//	Méthode pour permettre plusieurs tentatives de réponses au joueur (ici 2 car une tentative a déjà été faite
//			en dehors de la boucle).
	public static void runConditions(List<Integer> x) {
		
	List<Integer> y = playerCombi();
			
		
		for (int i=0; i<2; i++) {
			
			System.out.println("Vous avez saisi: " + y);
			List<String> computerAnswer = computerAnswer(x,y);
			
			if (winAnswer(computerAnswer) == true){
				
				System.out.println("Vous avez gagné");
				break;
			}
			
			else {
				System.out.println("Votre réponse est incorrect!");
				System.out.println("Voici quelques indications pour vous aider à trouver la réponse exacte: " + computerAnswer);
				y = playerCombi();
			}
		
			if (i == 1) {
				
				List<String> computerAnswerLast = computerAnswer(x,y);
				
				if (winAnswer(computerAnswerLast) == true) {
					System.out.println("Félicitations!! Vous avez gagné!!");
					break;
				}
				
				else {
					System.out.println("J'ai gagné!");
					System.out.println("La réponse était " + x);
					break;
				}
								
			}
		}
			
	}

	public static void main(String[] args) {

		menuStart();
		List<Integer> x = getRandom(0, 10);

		runConditions(x);


	}

}
