package com.lavanya.duel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.lavanya.common.ChallengerDuel;
import com.lavanya.common.DefenseurChallengerDuel;
import com.lavanya.common.DefenseurDuel;
import com.lavanya.utile.Proposition;
import com.lavanya.utile.Utile;
import com.lavanya.utile.UtileChallengerDuel;
import com.lavanya.utile.UtileDefenseurDuel;


public class Duel {
	
	static DefenseurChallengerDuel dCD = new Utile();
	static DefenseurDuel dD = new UtileDefenseurDuel();
	static ChallengerDuel cD = new UtileChallengerDuel();
	
	public static void menuStart() {
		System.out.println("Mode Duel");
		System.out.println("Bienvenue dans le mode Duel!");
		System.out.println("Je vais d�finir un nombre secret � 4 chiffres! ex: 8,6,5,1");
		System.out.println("Veuillez d�finir �galement votre nombre secret � 4 chiffres compris entre 0,0,0,0 � 9,9,9,9!");
		System.out.println(
				"C'est fait? Garder bien en t�te votre combinaison � 4 chiffres");
		System.out.println("Nous sommes en mode duel, le premier � d�couvrir la combinaison secr�te de l'autre gagne!");
		System.out.println("Vous �tes pr�t? Allez je commence!");
	}
		
		public static void executeDuel(List<Integer> x1, List<Integer> x2, List<Proposition> range) throws IOException {
			
			List<String> z1;
			List<String> z2 = null;
			int i = 0;
			
			do {
				z1 = dD.playerAnswer();
				if (dCD.winAnswer(z1) == true) {
					System.out.println("J'ai gagn�!! Le Duel est termin�!");
					System.out.println("La r�ponse �tait: " + x2);
					break;
				}
				
				else {
					System.out.println("Mince! Je dois retenter ma chance, A vous de jouer maintenant!");
					if (i > 0) {System.out.println("Pour rappel, l'indice obtenu au tour pr�c�dent �tait: " + z2);}
				}
				
				List<Integer> y = cD.playerCombi();
				z2 = cD.computerAnswer(x2,y);
				if (dCD.winAnswer(z2) == true) {
					System.out.println("Vous avez gagn�!! Le duel est termin�");
					break;
				}
				else {
					System.out.println("D�sol�, retentez votre chance au tour prochain!");
					System.out.println("Voici quelques indications qui pourraient vous aider: " + z2);
					System.out.println("A mon tour!");
				}
				
				x1 = dD.runConditions(z1,x1,range);
				System.out.println("Voici ma nouvelle r�ponse: " + x1);
				i++;
				
			}while(dCD.winAnswer(z1) == false || dCD.winAnswer(z2) == false);
//			
		}
		
	public static void main(String[] args) throws IOException {
		
		menuStart();
		List<Integer> x1= dCD.getRandom(dCD.properties("min"),dCD.properties("max"));
		List<Integer> x2= dCD.getRandom(dCD.properties("min"),dCD.properties("max"));
		System.out.println("Voici ma premi�re proposition: " + x1);
		List<Proposition> range = dD.rangeArray();
		executeDuel(x1, x2, range);
		

	}

}
