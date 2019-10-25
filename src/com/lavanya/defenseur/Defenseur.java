package com.lavanya.defenseur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Defenseur {
	
	Scanner scan = new Scanner(System.in);
	
	//M�thode getRandom: l'ordinateur doit proposer au joueur une combinaison � 4 chiffres
	public static List<Integer> getRandom(int min, int max) {
		List<Integer> randomCombi = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			int j = min + random.nextInt(max - min);
			randomCombi.add(j);
		};
		
		return randomCombi;
	}
	
	public static Integer newRandom(int min, int max) {
				
		Random random = new Random();
	
			int j = min + random.nextInt(max - min);
		
		return j;
	}
	
	
	
	public static void main(String[] args) {

		System.out.println("Mode D�fenseur");
		System.out.println("Bienvenue dans le mode D�fenseur!");
		System.out.println("Veuillez d�finir un nombre � 4 chiffres compris entre 0000 � 9999!");
		System.out.println("C'est fait? Garder bien en t�te votre combinaison � 4 chiffres\nMaintenat c'est � moi de jouer!");
		
		
		List<Integer> x = getRandom(0,10); 
		System.out.println("Voici ma premi�re proposition: " + x);	
		
		List<String> winCombi = new ArrayList<>();
		winCombi.add("=");
		winCombi.add("=");
		winCombi.add("=");
		winCombi.add("=");
		
		for (int i=0; i<2; i++) {
			//Le joueur va comparer la combi propos�e � la sienne avec les valeurs + = ou -
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)");
			String clue = sc.nextLine();
			
			//Conversion de la r�ponse du joueur en String List ArrayList		
			List<String> playerClue = Arrays.asList(clue.split(","));
			
	
			System.out.println("Vous avez saisi : " + playerClue);
			
				
			boolean win = playerClue.equals(winCombi);
			
			if (win == true){
				System.out.println("J'ai gagn�!!!");
				break;
				
			}
			
						
			else {			
				//L'ordinateur va g�n�rer une nouvelle combinaison en fonction des indices +,= ou - indiqu� par le joueur		
				List<Integer> randomCombiNewTry = new ArrayList<>();
				
				
				for (int j=0, k=0; j < playerClue.size(); j++, k++) {
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
					
					if (playerClue.get(j).contentEquals("+")) {
						int z = newRandom((x.get(k))+1,10);
						randomCombiNewTry.add(z);
					
					}
					
					if (playerClue.get(j).contentEquals("-")) {
						int z = newRandom(0,x.get(k));
						randomCombiNewTry.add(z);
					}
					
					if (playerClue.get(j).contentEquals("=")) {
						int z = x.get(k);
						randomCombiNewTry.add(z);
					}
					
		
				}
				
				System.out.println("Voila ma nouvelle proposition " + randomCombiNewTry);
			
			}
//			Si d�comment� la boucle ne se fait pas et j'ai le message d'erreur
//			Exception in thread "main" Voila ma nouvelle proposition [9, 9, 6, 8]
//					Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)
//					java.util.NoSuchElementException: No line found
//						at java.util.Scanner.nextLine(Unknown Source)
//						at com.lavanya.defenseur.Defenseur.main(Defenseur.java:53)
//			sc.close();	
			
//			if (i == 3) {
//				System.out.println("Vous avez gagn�!");
//			}
		}
		
		Scanner scLast = new Scanner(System.in);
		System.out.println("Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)");
		String clueLast = scLast.nextLine();
			
		
		List<String> playerClueLast = Arrays.asList(clueLast.split(","));
		boolean winLast = playerClueLast.equals(winCombi);
			
		if (winLast == false) {
			System.out.println("Vous avez gagn�!!!");
		}
		
		
	}

}
