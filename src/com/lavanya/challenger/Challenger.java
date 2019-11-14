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
		System.out.println("Vous avez le droit � 3 tentatives!");
		System.out.println("Vous �tes pr�t? Allez! A vous de jouer!");
	}

	// M�thode getRandom: l'ordinateur doit cr�er une combinaison � 4 chiffres
	public static List<Integer> getRandom(int min, int max) {

		List<Integer> randomCombi = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			int j = min + random.nextInt(max - min);
			randomCombi.add(j);
		}

		return randomCombi;
	}

//	M�thode pour que le joueur fasse ses propositions de combinaisons
//	et conversion de ses propositions en array
	public static List<Integer> playerCombi() {

		List<Integer> answerInt = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Veuillez saisir votre proposition, ex: 0,9,1,7 (Utilisez le m�me format que dans l'exemple):");
		String input = sc.nextLine();
		List<String> answerString = Arrays.asList(input.split(","));

		for (String str : answerString) {
			answerInt.add(Integer.parseInt(str));
		}

		return answerInt;

	}

//	M�thode pour comparer la r�ponse du joueur � celle que l'ordinateur � g�n�rer et indication (+,=,-)
//	pour que le joueur puisse proposer une nouvelle r�ponse
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

//	M�thode de type bool�en pour d�finir si le joueur a donn� une combinaison 4 chiffres gagnante 
	public static boolean winAnswer(List<String> computerAnswer) {

		// Cr�ation d'une liste qui correspond � la r�ponse du joueur que l'ordi attend
		// pour gagner
		List<String> winCombi = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			winCombi.add("=");
		}

		boolean win = computerAnswer.equals(winCombi);
		return win;
	}

//	M�thode pour permettre plusieurs tentatives de r�ponses au joueur (ici 2 car une tentative a d�j� �t� faite
//			en dehors de la boucle).
	public static void runConditions(List<Integer> x) {
		
	List<Integer> y = playerCombi();
			
		
		for (int i=0; i<2; i++) {
			
			System.out.println("Vous avez saisi: " + y);
			List<String> computerAnswer = computerAnswer(x,y);
			
			if (winAnswer(computerAnswer) == true){
				
				System.out.println("Vous avez gagn�");
				break;
			}
			
			else {
				System.out.println("Votre r�ponse est incorrect!");
				System.out.println("Voici quelques indications pour vous aider � trouver la r�ponse exacte: " + computerAnswer);
				y = playerCombi();
			}
		
			if (i == 1) {
				
				List<String> computerAnswerLast = computerAnswer(x,y);
				
				if (winAnswer(computerAnswerLast) == true) {
					System.out.println("F�licitations!! Vous avez gagn�!!");
					break;
				}
				
				else {
					System.out.println("J'ai gagn�!");
					System.out.println("La r�ponse �tait " + x);
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
