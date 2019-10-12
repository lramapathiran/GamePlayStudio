package com.lavanya.defenseur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Defenseur {
	
	//Méthode getRandom: l'ordinateur doit proposer au joueur une combinaison à 4 chiffres
	public static List<Integer> getRandom() {
		List<Integer> randomCombi = new ArrayList<>();

		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			int j = random.nextInt(10);
			randomCombi.add(j);
		}
		;

		return randomCombi;
	}
	
	

	public static void main(String[] args) {

		System.out.println("Mode Défenseur");
		System.out.println("Bienvenue dans le mode Défenseur!");
		System.out.println("Veuillez définir un nombre à 4 chiffres compris entre 0000 à 9999!");
		System.out.println("C'est fait, garder bien votre combinaison à 4 chiffres en tête\nMaintenat c'est à moi de jouer!");
		System.out.println("Voici ma première proposition: " + getRandom());
		
		//Le joueur va comparer la combi proposée à la sienne avec les valeurs + = ou -
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez la comparer à votre combinaison et m'indiquer pour chacun de ses chiffres les comparatifs +,= ou -: ");
		String clue = sc.nextLine();
		System.out.println("Vous avez saisi : " + clue);
		
		sc.close();
	}

}
