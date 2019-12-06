package com.lavanya.utile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.lavanya.common.DefenseurDuel;
import com.lavanya.utile.Utile;
import com.lavanya.common.DefenseurChallengerDuel;

public class UtileDefenseurDuel implements DefenseurDuel{
	

		static DefenseurChallengerDuel dCD = new Utile();
		
// 		Méthode pour convertir la combinaison secrète du joueur d'un type String-->Chatacter-->Integer
//		@param la combinaison secrète de type String entrée dans la console
//		return: Liste d'Interger qui correspond à la combi secrète du joueur à découvrir par l'ordi
		public List<Integer> playerCombi(String secretCombi) {
			
			List<Integer> playerCombi = new ArrayList<Integer>();
			
			for (char ch : secretCombi.toCharArray()) { 
	        	
	        	int i = Character.getNumericValue(ch);
	        	playerCombi.add(i); 
	        }
			 return playerCombi;
		}
	
	
	
//		Méthode pour créer une nouvelle combinaison à 4 chiffres par l'ordinateur
		public Integer newRandom(int min, int max) {

			Random random = new Random();
			int j = min + random.nextInt(max - min);
			return j;
		}
		
//		Méthode de comparaison de la réponse de l'ordi à la combi secrète du joueur par création d'une Liste +,=,-
//		@param: x (list Integer) correspond à la combi secrète à découvrir du joueur et y (list Integer) correspond à la proposition de combi 4 chiffres de l'ordi
//		return la liste Character de comparaison =,+,-
		public List<Character> computerPropositionCheck(List<Integer> x, List<Integer> y) throws IOException {
			
			List<Character> computerCheck = new ArrayList<>();
			for (int i = 0, j = 0; i < dCD.properties("digit"); i++, j++) {

				if (x.get(i) > y.get(j)) {
					computerCheck.add('+');
				}

				if (x.get(i) < y.get(j)) {
					computerCheck.add('-');
				}

				if (x.get(i) == y.get(j)) {
					computerCheck.add('=');
				}

			}

			return computerCheck;
			
		}
		
//		Méthode où Le joueur va comparer la combi proposée à la sienne avec les valeurs + = ou -
//		Conversion de la réponse du joueur en String List ArrayList
		public List<Character> playerAnswer() {
			
			List<Character> playerClue= new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison proposée: ex +,=,=,-(merci d'utiliser le même format de réponse que dans l'exemple)");
			String clue = sc.nextLine();
//			sc.close();	
			
			for(Character ch: clue.toCharArray()) {
				
				playerClue.add(ch);
			}
			
			return playerClue;

		}
		
//		Méthode pour comparer la réponse du joueur (suite à la proposition de l'ordi) à la réponse attendue +,=,-
//		@param Listes de type Character avec x étant la réponse du joueur et y celle attendue
		public void validationPlayerClue(List<Character> x, List<Character> y) {
			
			boolean comparison = x.equals(y);
			while(comparison == false) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Votre réponse est incorrecte! Veuillez vérifier votre saisie et la corriger ci-dessous:");
				String clue = sc.nextLine();
				
				int i=0;
				for(Character ch: clue.toCharArray()) {
					
					x.set(i,ch);
					i++;
				}
				
				comparison = x.equals(y);
			}
			
		
		}
		
		public List<Proposition> rangeArray() throws IOException {
			
			List<Proposition> limit = new ArrayList<Proposition>();
					
			for (int i = 0; i < dCD.properties("digit"); i++) {
				limit.add(new Proposition(0,10));
			}
			
			return limit;
		}
		
//		L'ordinateur va générer une nouvelle combinaison en fonction des indices +,= ou - 
//		indiqué par le joueur
		public List<Integer> runConditions(List<Character> y, List<Integer> x, List<Proposition> range) {
			
					
			List<Integer> randomCombiNewTry = new ArrayList<>();
			
			for (int j = 0, k = 0, z = 0; j < y.size(); j++, k++) {
				
				if (y.get(j).equals('+')) {
					range.get(j).setMin(x.get(k) + 1);
					int min = range.get(j).getMin();
					int max = range.get(j).getMax();
					
					z = newRandom(min, max);
				}

				if (y.get(j).equals('-')) {
					range.get(j).setMax(x.get(k));
					int min = range.get(j).getMin();
					int max = range.get(j).getMax();
					
					z = newRandom(min, max);
				}
				
				

				if (y.get(j).equals('=')) {
					z = x.get(k);
				}
				
				randomCombiNewTry.add(z);
			}
			
			
			return randomCombiNewTry;

		}
}
