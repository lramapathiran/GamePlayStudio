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
				"Veuillez saisir votre première proposition, ex: 0,9,1,7 (Utilisez le même format que dans l'exemple):");
		String input = sc.nextLine();
		List<String> answerString = Arrays.asList(input.split(","));

		for (String str : answerString) {
			answerInt.add(Integer.parseInt(str));
		}

		return answerInt;

	}
	
	
	
	

	public static void main(String[] args) {

		menuStart();
		List<Integer> x = getRandom(0, 10);
		System.out.println("Vous avz saisi: " + playerCombi());
		
//		Comparer la réponse du joueur à la réponse de l'ordinateur à trouver.
		
		
		

	}

}
