package com.lavanya.challenger;

import java.io.IOException;
import java.util.List;

import com.lavanya.common.ChallengerDuel;
import com.lavanya.common.DefenseurChallengerDuel;
import com.lavanya.utile.Utile;
import com.lavanya.utile.UtileChallengerDuel;


public class Challenger {
	
	static DefenseurChallengerDuel dCD = new Utile();
	static ChallengerDuel cD = new UtileChallengerDuel();

	public static void menuStart() {
		System.out.println("Mode Challenger:");
		System.out.println("Bienvenue dans le mode Challenger!");
		System.out.println("Vous devez deviner une combinaison de 4 chiffres comprise entre 0,0,0,0 et 9,9,9,9!");
		System.out.println("Vous avez le droit à 3 tentatives!");
		System.out.println("Vous êtes prêt? Allez! A vous de jouer!");
	}
	


//	Méthode pour permettre plusieurs tentatives de réponses au joueur (ici 2 car une tentative a déjà été faite
//			en dehors de la boucle).
	public static void runConditions(List<Integer> x) throws IOException {
		
	List<Integer> y = cD.playerCombi();
			
		
		for (int i=0; i<2; i++) {
			
			System.out.println("Vous avez saisi: " + y);
			List<String> computerAnswer = cD.computerAnswer(x,y);
			
			if (dCD.winAnswer(computerAnswer) == true){
				
				System.out.println("Vous avez gagné");
				break;
			}
			
			else {
				System.out.println("Votre réponse est incorrect!");
				System.out.println("Voici quelques indications pour vous aider à trouver la réponse exacte: " + computerAnswer);
				y = cD.playerCombi();
			}
		
			if (i == 1) {
				
				List<String> computerAnswerLast = cD.computerAnswer(x,y);
				
				if (dCD.winAnswer(computerAnswerLast) == true) {
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

	public static void main(String[] args) throws IOException {

		menuStart();
		List<Integer> x = dCD.getRandom(dCD.properties("min"),dCD.properties("max"));

		runConditions(x);


	}

}
