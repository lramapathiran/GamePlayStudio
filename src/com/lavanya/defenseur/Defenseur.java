package com.lavanya.defenseur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Defenseur {
	
	//M�thode getRandom: l'ordinateur doit proposer au joueur une combinaison � 4 chiffres
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

		System.out.println("Mode D�fenseur");
		System.out.println("Bienvenue dans le mode D�fenseur!");
		System.out.println("Veuillez d�finir un nombre � 4 chiffres compris entre 0000 � 9999!");
		System.out.println("C'est fait? Garder bien en t�te votre combinaison � 4 chiffres\nMaintenat c'est � moi de jouer!");
		System.out.println("Voici ma premi�re proposition: " + getRandom());
		
		//Le joueur va comparer la combi propos�e � la sienne avec les valeurs + = ou -
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format)");
		String clue = sc.nextLine();
		
		//Conversion de la r�ponse du joueur en String List ArrayList		
		List<String> playerClue = Arrays.asList(clue.split(","));
		
		
		System.out.println("Vous avez saisi : " + playerClue);
		
		
		
		sc.close();
	}

}
