package com.lavanya.defenseur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Defenseur {
	
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

//M�thode pour cr�er une nouvelle combinaison � 4 chiffres par l'ordinateur
	public static Integer newRandom(int min, int max) {
				
		Random random = new Random();
		int j = min + random.nextInt(max - min);
		return j;
	}
	
//	M�thode o� Le joueur va comparer la combi propos�e � la sienne avec les valeurs + = ou -
//	Conversion de la r�ponse du joueur en String List ArrayList
	public static List<String> playerAnswer() {
				
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)");
		String clue = sc.nextLine();
		
		List<String> playerClue = Arrays.asList(clue.split(","));
		return playerClue;
	}
	

//	M�thode de type bool�en pour d�finir si l'ordinateur a donn� une combinaison 4 chiffres gagnante 
	public static boolean winAnswer(List<String> playerAnswer) {
	
//		Cr�ation d'une liste qui correspond � la r�ponse du joueur que l'ordi attend pour gagner
		List<String> winCombi = new ArrayList<>();
		winCombi.add("=");
		winCombi.add("=");
		winCombi.add("=");
		winCombi.add("=");
		
		boolean win = playerAnswer.equals(winCombi);
		return win;
	}
	
	
	
	
	public static void main(String[] args) {

		System.out.println("Mode D�fenseur");
		System.out.println("Bienvenue dans le mode D�fenseur!");
		System.out.println("Veuillez d�finir un nombre � 4 chiffres compris entre 0000 � 9999!");
		System.out.println("C'est fait? Garder bien en t�te votre combinaison � 4 chiffres\nMaintenat c'est � moi de jouer!");
		
		
		List<Integer> x = getRandom(0,10); 
		System.out.println("Voici ma premi�re proposition: " + x);	
				
		
//		Cr�ation de la boucle for pour donner � l'ordi la possibilit� d'avoir 3 essais dans le jeu
		for (int i=0; i<2; i++) {
			
			List<String> y = playerAnswer();
			winAnswer(y);
			
			if (winAnswer(y) == true){
				System.out.println("J'ai gagn�!!!");
				break;
				
			}
			
						
			else {			
				//L'ordinateur va g�n�rer une nouvelle combinaison en fonction des indices +,= ou - indiqu� par le joueur		
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
					
//					Je n'ai pas encore trouv� de solution pour adapter le min et max pour chaque if statement! car au tour 2 (i=2) de mon for, la valeur du min
//					et max changent selon qu'au tour pr�c�dent le joueur avait dit + ou -! Il y a 4 possibilit�s au total!
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
			
			
//			Si "sc.close" est d�comment� la boucle ne se fait pas et j'ai le message d'erreur: Pourquoi?
//			Exception in thread "main" Voila ma nouvelle proposition [9, 9, 6, 8]
//					Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)
//					java.util.NoSuchElementException: No line found
//						at java.util.Scanner.nextLine(Unknown Source)
//						at com.lavanya.defenseur.Defenseur.main(Defenseur.java:53)
//			sc.close();	
			
		}
		
//		Petit souci ici si l'ordi gagne avec le boolean win == true il faudrait que le code ci-dessous ne s'execute pas mais je ne vois pas o� le placer du coup!
		List<String> yLast = playerAnswer();
		winAnswer(yLast);
	
		if (winAnswer(yLast) == false) {
			System.out.println("Vous avez gagn�!!!");
		}
		else {
			System.out.println("J'ai gagn�!!!");
		}
	}

}
