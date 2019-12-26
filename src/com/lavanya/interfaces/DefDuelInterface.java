package com.lavanya.interfaces;

import java.io.IOException;
import java.util.List;

import com.lavanya.utile.Proposition;

public interface DefDuelInterface {
	
	Integer newRandom(int min, int max);
	List<Character> playerAnswer() throws IOException;
	List<Character> validationPlayerClue(List<Character> playerClue, List<Character> playerAnswerExpected);
	List<Proposition> rangeArray() throws IOException;
	List<Integer> runConditions(List<Character> validPlayerClue, List<Integer> computerProposition, List<Proposition> range);
}
