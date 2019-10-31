package com.lavanya.defenseur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Defenseur {
	

	public static void menuStart() {
		System.out.println("Mode D�fenseur");
		System.out.println("Bienvenue dans le mode D�fenseur!");
		System.out.println("Veuillez d�finir un nombre � 4 chiffres compris entre 0000 � 9999!");
		System.out.println(
				"C'est fait? Garder bien en t�te votre combinaison � 4 chiffres\nMaintenant c'est � moi de jouer!");
	}

//M�thode getRandom: l'ordinateur doit proposer au joueur une combinaison � 4 chiffres
	public static List<Integer> getRandom(int min, int max) {

		List<Integer> randomCombi = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			int j = min + random.nextInt(max - min);
			randomCombi.add(j);
		}
		

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
		System.out.println(
				"Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)");
		String clue = sc.nextLine();
//		sc.close();	
		List<String> playerClue = Arrays.asList(clue.split(","));
		return playerClue;

	}

//	M�thode de type bool�en pour d�finir si l'ordinateur a donn� une combinaison 4 chiffres gagnante 
	public static boolean winAnswer(List<String> playerAnswer) {

		// Cr�ation d'une liste qui correspond � la r�ponse du joueur que l'ordi attend
		// pour gagner
		List<String> winCombi = new ArrayList<>();
		winCombi.add("=");
		winCombi.add("=");
		winCombi.add("=");
		winCombi.add("=");

		boolean win = playerAnswer.equals(winCombi);
		return win;
	}

//	L'ordinateur va g�n�rer une nouvelle combinaison en fonction des indices +,= ou - 
//	indiqu� par le joueur
	public static List<Integer> runConditions(List<String> y, List<Integer> x, int m) {

		List<Integer> randomCombiNewTry = new ArrayList<>();
		
		for (int j = 0, k = 0; j < y.size(); j++, k++) {
			
			int min = 0;
			int max = 0;
	
			
			if (y.get(j).contentEquals("+")) {
				switch (j) {
					case 0:
						addRangeZero(x.get(k) + 1,rangeZero.get(m-1).getMax());
						min = rangeZero.get(m).getMin();
						max = rangeZero.get(m).getMax();
						break;
					case 1:
						addRangeOne(x.get(k) + 1,rangeOne.get(m-1).getMax());
						min = rangeOne.get(m).getMin();
						max = rangeOne.get(m).getMax();
						break;
					case 2:
						addRangeTwo(x.get(k) + 1,rangeTwo.get(m-1).getMax());
						min = rangeTwo.get(m).getMin();
						max = rangeTwo.get(m).getMax();
						break;
					case 3:
						addRangeThree(x.get(k) + 1,rangeThree.get(m-1).getMax());
						min = rangeThree.get(m).getMin();
						max = rangeThree.get(m).getMax();
						break;
				}
		
				int z = newRandom(min, max);
				randomCombiNewTry.add(z);

			}

			if (y.get(j).contentEquals("-")) {
				switch (j) {
					case 0:
						addRangeZero(rangeZero.get(m-1).getMin(),x.get(k));
						min = rangeZero.get(m).getMin();
						max = rangeZero.get(m).getMax();
						break;
					case 1:
						addRangeOne(rangeOne.get(m-1).getMin(),x.get(k));
						min = rangeOne.get(m).getMin();
						max = rangeOne.get(m).getMax();
						break;
					case 2:
						addRangeTwo(rangeTwo.get(m-1).getMin(),x.get(k));
						min = rangeTwo.get(m).getMin();
						max = rangeTwo.get(m).getMax();
						break;
					case 3:
						addRangeThree(rangeThree.get(m-1).getMin(),x.get(k));
						min = rangeThree.get(m).getMin();
						max = rangeThree.get(m).getMax();
						break;
				}
						
				int z = newRandom(min, max);
				randomCombiNewTry.add(z);
			}

			if (y.get(j).contentEquals("=")) {
				int z = x.get(k);
				randomCombiNewTry.add(z);
			}
		}
		
		return randomCombiNewTry;

	}
	
	public static List<Proposition> range = new ArrayList<Proposition>();
	public static List<Proposition> rangeZero = new ArrayList<Proposition>();
	public static List<Proposition> rangeOne = new ArrayList<Proposition>();
	public static List<Proposition> rangeTwo = new ArrayList<Proposition>();
	public static List<Proposition> rangeThree = new ArrayList<Proposition>();
	 
    static {
    	
        range.add(new Proposition(0,10));
        rangeZero.add(new Proposition(0,10));
        rangeOne.add(new Proposition(0,10));
        rangeTwo.add(new Proposition(0,10));
        rangeThree.add(new Proposition(0,10));
        
 
    }
    
    public static void addRangeZero(int min, int max) {
    	rangeZero.add(new Proposition(min,max));
    }
    
    public static void addRangeOne(int min, int max) {
    	rangeOne.add(new Proposition(min,max));
    }
    
    public static void addRangeTwo(int min, int max) {
    	rangeTwo.add(new Proposition(min,max));
    }
    
    public static void addRangeThree(int min, int max) {
    	rangeThree.add(new Proposition(min,max));
    }

// Cr�ation de la boucle for pour donner � l'ordi la possibilit� d'avoir 3
// essais dans le jeu
	public static void threeAttempt(List<Integer> x) {
		for (int i=0, m = 1; i<2; i++, m++) {
			
			List<String> y = playerAnswer();
		
			
			if (winAnswer(y) == true){
				System.out.println("J'ai gagn�!!!");
				break;
				
			}
			
						
			else {					
				
				x = runConditions(y, x, m);
				System.out.println("Voila ma nouvelle proposition " + x);
				
				if (i == 1) {
					List<String> yLast = playerAnswer();
				
				
					if (winAnswer(yLast) == false) {
						System.out.println("Vous avez gagn�!!!");
					}
					else {
						System.out.println("J'ai gagn�!!!");
					}
				}
				
			}
		}
	}

	
	public static void main(String[] args) {

		menuStart();
		
		int min = range.get(0).getMin();
		int max = range.get(0).getMax();
		List<Integer> x = getRandom(min, max);
		
		System.out.println("Voici ma premi�re proposition: " + x);
		
		threeAttempt(x);

	}

}

//Voici ma premi�re proposition: [9, 3, 2, 5]
//Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)
//-,+,=,-
//Voila ma nouvelle proposition [4, 5, 2, 4]
//Exception in thread "main" Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)
//java.util.NoSuchElementException: No line found
//	at java.util.Scanner.nextLine(Unknown Source)
//	at com.lavanya.defenseur.Defenseur.playerAnswer(Defenseur.java:47)
//	at com.lavanya.defenseur.Defenseur.main(Defenseur.java:93)

//Mode D�fenseur
//Bienvenue dans le mode D�fenseur!
//Veuillez d�finir un nombre � 4 chiffres compris entre 0000 � 9999!
//C'est fait? Garder bien en t�te votre combinaison � 4 chiffres
//Maintenat c'est � moi de jouer!
//Voici ma premi�re proposition: [4, 6, 6, 3]
//Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)
//-,=,+,-
//Exception in thread "main" java.lang.IllegalArgumentException: bound must be positive
//	at java.util.Random.nextInt(Unknown Source)
//	at com.lavanya.defenseur.Defenseur.newRandom(Defenseur.java:37)
//	at com.lavanya.defenseur.Defenseur.main(Defenseur.java:113)
