package com.lavanya.utile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lavanya.common.ChallengerDuel;
import com.lavanya.common.DefenseurChallengerDuel;

public class UtileChallengerDuel implements ChallengerDuel {
	
	static DefenseurChallengerDuel dCD = new Utile();
	
//	Méthode pour que le joueur fasse ses propositions de combinaisons
//	et conversion de ses propositions en array
	public List<Integer> playerCombi() {

		List<Integer> answerInt = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Veuillez saisir votre proposition, ex: 0978");
		String input = sc.nextLine();
		
		// For each character in the String 
        // add it to the List 
        for (char ch : input.toCharArray()) { 
        	
        	int i = Character.getNumericValue(ch);
        	answerInt.add(i); 
        } 
  

		return answerInt;

	}

//	Méthode pour comparer la réponse du joueur à celle que l'ordinateur à générer et indication (+,=,-)
//	pour que le joueur puisse proposer une nouvelle réponse
	public List<Character> computerAnswer(List<Integer> x, List<Integer> y) throws IOException {

		List<Character> computerClue = new ArrayList<>();
		for (int i = 0, j = 0; i < dCD.properties("digit"); i++, j++) {

			if (x.get(i) > y.get(j)) {
				computerClue.add('+');
			}

			if (x.get(i) < y.get(j)) {
				computerClue.add('-');
			}

			if (x.get(i) == y.get(j)) {
				computerClue.add('=');
			}

		}

		return computerClue;
	}

}
