package com.lavanya.utile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.lavanya.common.DefenseurDuel;
import com.lavanya.utile.Utile;
import com.lavanya.common.DefenseurChallengerDuel;

public class UtileDefenseurDuel implements DefenseurDuel{
	
	static DefenseurChallengerDuel dCD = new Utile();
	
	//Méthode pour créer une nouvelle combinaison à 4 chiffres par l'ordinateur
		public Integer newRandom(int min, int max) {

			Random random = new Random();
			int j = min + random.nextInt(max - min);
			return j;
		}
		
//		Méthode où Le joueur va comparer la combi proposée à la sienne avec les valeurs + = ou -
//		Conversion de la réponse du joueur en String List ArrayList
		public List<String> playerAnswer() {

			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison proposée: ex +,=,=,-(merci d'utiliser le même format de réponse que dans l'exemple)");
			String clue = sc.nextLine();
//			sc.close();	
			List<String> playerClue = Arrays.asList(clue.split(","));
			return playerClue;

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
		public List<Integer> runConditions(List<String> y, List<Integer> x, List<Proposition> range) {
			
					
			List<Integer> randomCombiNewTry = new ArrayList<>();
			
			for (int j = 0, k = 0, z = 0; j < y.size(); j++, k++) {
				
				if (y.get(j).contentEquals("+")) {
					range.get(j).setMin(x.get(k) + 1);
					int min = range.get(j).getMin();
					int max = range.get(j).getMax();
					
					z = newRandom(min, max);
				}

				if (y.get(j).contentEquals("-")) {
					range.get(j).setMax(x.get(k));
					int min = range.get(j).getMin();
					int max = range.get(j).getMax();
					
					z = newRandom(min, max);
				}
				
				

				if (y.get(j).contentEquals("=")) {
					z = x.get(k);
				}
				
				randomCombiNewTry.add(z);
			}
			
			
			return randomCombiNewTry;

		}
}
