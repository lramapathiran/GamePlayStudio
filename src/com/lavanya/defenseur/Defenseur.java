package com.lavanya.defenseur;

import java.util.ArrayList;
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
		System.out.println("C'est fait, garder bien votre combinaison � 4 chiffres en t�te\nMaintenat c'est � moi de jouer!");
		System.out.println("Voici ma premi�re proposition: " + getRandom());
		
		//Le joueur va comparer la combi propos�e � la sienne avec les valeurs + = ou -
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez la comparer � votre combinaison et m'indiquer pour chacun de ses chiffres les comparatifs +,= ou -: ");
		String clue = sc.nextLine();
		System.out.println("Vous avez saisi : " + clue);
		
		sc.close();
	}

}
