package com.lavanya.utile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.lavanya.common.ChallengerDuel;

public class UtileChallengerDuel implements ChallengerDuel {
	
	
//	M�thode pour que le joueur fasse ses propositions de combinaisons
//	et conversion de ses propositions en array
	public List<Integer> playerCombi() {

		List<Integer> answerInt = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Veuillez saisir votre proposition, ex: 0,9,1,7 (Utilisez le m�me format que dans l'exemple):");
		String input = sc.nextLine();
		List<String> answerString = Arrays.asList(input.split(","));
		
//		sc.close();
		
		for (String str : answerString) {
			answerInt.add(Integer.parseInt(str));
		}

		return answerInt;

	}

//	M�thode pour comparer la r�ponse du joueur � celle que l'ordinateur � g�n�rer et indication (+,=,-)
//	pour que le joueur puisse proposer une nouvelle r�ponse
	public List<String> computerAnswer(List<Integer> x, List<Integer> y) {

		List<String> computerClue = new ArrayList<>();
		for (int i = 0, j = 0; i < 4; i++, j++) {

			if (x.get(i) > y.get(j)) {
				computerClue.add("+");
			}

			if (x.get(i) < y.get(j)) {
				computerClue.add("-");
			}

			if (x.get(i) == y.get(j)) {
				computerClue.add("=");
			}

		}

		return computerClue;
	}

}
