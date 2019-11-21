package com.lavanya.utile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lavanya.common.DefenseurChallengerDuel;

public class Utile  implements DefenseurChallengerDuel {

		public int properties(String key) throws IOException {
			int confValue = Configuration.getInstance().getProperty(key);
			return confValue;
		}
	
	//M�thode getRandom: l'ordinateur doit proposer au joueur une combinaison � 4 chiffres
		public List<Integer> getRandom(int min, int max) throws IOException {

			
			List<Integer> randomCombi = new ArrayList<>();
			Random random = new Random();

			for (int i = 0; i < properties("digit"); i++) {
				int j = min + random.nextInt(max - min);
				randomCombi.add(j);
				}
			
			

			return randomCombi;
		}
		
//		M�thode de type bool�en pour d�finir si l'ordinateur a donn� une combinaison 4 chiffres gagnante 
		public boolean winAnswer(List<String> playerAnswer) throws IOException {

			// Cr�ation d'une liste qui correspond � la r�ponse du joueur que l'ordi attend
			// pour gagner
			List<String> winCombi = new ArrayList<>();
			
			for (int i = 0; i < properties("digit"); i++) {
				winCombi.add("=");
			}

			boolean win = playerAnswer.equals(winCombi);
			return win;
		}

}
