package com.lavanya.common;

import java.io.IOException;
import java.util.List;

import com.lavanya.utile.Proposition;

public interface DefenseurDuel {
	
	List<Integer> playerCombi(String secretCombi);
	Integer newRandom(int min, int max);
	List<Character> playerAnswer();
	List<Character> computerPropositionCheck(List<Integer> y, List<Integer> x) throws IOException;
	void validationPlayerClue(List<Character> x, List<Character> y);
	List<Proposition> rangeArray() throws IOException;
	List<Integer> runConditions(List<Character> y, List<Integer> x, List<Proposition> range);
}
