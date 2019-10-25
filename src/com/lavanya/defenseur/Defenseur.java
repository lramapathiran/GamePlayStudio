package com.lavanya.defenseur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Defenseur {
	
	//Méthode getRandom: l'ordinateur doit proposer au joueur une combinaison à 4 chiffres
	public static List<Integer> getRandom(int min, int max) {
		
		List<Integer> randomCombi = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			int j = min + random.nextInt(max - min);
			randomCombi.add(j);
		};
		
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
		System.out.println("Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison proposée: ex +,=,=,-(merci d'utiliser le même format de réponse que dans l'exemple)");
		String clue = sc.nextLine();
		
		List<String> playerClue = Arrays.asList(clue.split(","));
		return playerClue;
	}
	

//	Méthode de type booléen pour définir si l'ordinateur a donné une combinaison 4 chiffres gagnante 
	public static boolean winAnswer(List<String> playerAnswer) {
	
//		Création d'une liste qui correspond à la réponse du joueur que l'ordi attend pour gagner
		List<String> winCombi = new ArrayList<>();
		winCombi.add("=");
		winCombi.add("=");
		winCombi.add("=");
		winCombi.add("=");
		
		boolean win = playerAnswer.equals(winCombi);
		return win;
	}
	
	
	
	
	public static void main(String[] args) {

		System.out.println("Mode Défenseur");
		System.out.println("Bienvenue dans le mode Défenseur!");
		System.out.println("Veuillez définir un nombre à 4 chiffres compris entre 0000 à 9999!");
		System.out.println("C'est fait? Garder bien en tête votre combinaison à 4 chiffres\nMaintenat c'est à moi de jouer!");
		
		
		List<Integer> x = getRandom(0,10); 
		System.out.println("Voici ma première proposition: " + x);	
				
		
//		Création de la boucle for pour donner à l'ordi la possibilité d'avoir 3 essais dans le jeu
		for (int i=0; i<2; i++) {
			
			List<String> y = playerAnswer();
			winAnswer(y);
			
			if (winAnswer(y) == true){
				System.out.println("J'ai gagné!!!");
				break;
				
			}
			
						
			else {			
				//L'ordinateur va générer une nouvelle combinaison en fonction des indices +,= ou - indiqué par le joueur		
				List<Integer> randomCombiNewTry = new ArrayList<>();
				
				
				for (int j=0, k=0; j < y.size(); j++, k++) {

					
//					Tentatives d'utilisation du switch, je ne sais quel argument passer dans switch() donc le code n'est pas bon et le switch est non fonctionnel!
					
//					boolean	a = playerClue.get(j).contentEquals("+");
//					boolean	b = playerClue.get(j).contentEquals("-");
//					boolean c= 	playerClue.get(j).contentEquals("=");
					
//					switch() {
//						case a:
//							int z = newRandom(x.get(k),10);
//							randomCombiNewTry.add(z);
//							break;
//						case b:
//							int z = newRandom(0,x.get(k));
//							randomCombiNewTry.add(z);
//							break;
//						case c:
//							int z = x.get(k);
//							randomCombiNewTry.add(z);
//							break;
//					}
					
//					Je n'ai pas encore trouvé de solution pour adapter le min et max pour chaque if statement! car au tour 2 (i=2) de mon for, la valeur du min
//					et max changent selon qu'au tour précédent le joueur avait dit + ou -! Il y a 4 possibilités au total!
					if (y.get(j).contentEquals("+")) {
						int z = newRandom((x.get(k))+1,10);
						randomCombiNewTry.add(z);
					
					}
					
					if (y.get(j).contentEquals("-")) {
						int z = newRandom(0,x.get(k));
						randomCombiNewTry.add(z);
					}
					
					if (y.get(j).contentEquals("=")) {
						int z = x.get(k);
						randomCombiNewTry.add(z);
					}
				}
				
				System.out.println("Voila ma nouvelle proposition " + randomCombiNewTry);
				
			}
			
			
//			Si "sc.close" est décommenté la boucle ne se fait pas et j'ai le message d'erreur: Pourquoi?
//			Exception in thread "main" Voila ma nouvelle proposition [9, 9, 6, 8]
//					Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison proposée: ex +,=,=,-(merci d'utiliser le même format de réponse que dans l'exemple)
//					java.util.NoSuchElementException: No line found
//						at java.util.Scanner.nextLine(Unknown Source)
//						at com.lavanya.defenseur.Defenseur.main(Defenseur.java:53)
//			sc.close();	
			
		}
		
//		Petit souci ici si l'ordi gagne avec le boolean win == true il faudrait que le code ci-dessous ne s'execute pas mais je ne vois pas où le placer du coup!
		List<String> yLast = playerAnswer();
		winAnswer(yLast);
	
		if (winAnswer(yLast) == false) {
			System.out.println("Vous avez gagné!!!");
		}
		else {
			System.out.println("J'ai gagné!!!");
		}
	}

}
