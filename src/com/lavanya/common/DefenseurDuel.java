package com.lavanya.common;

import java.io.IOException;
import java.util.List;

import com.lavanya.utile.Proposition;

public interface DefenseurDuel {
	
	Integer newRandom(int min, int max);
	List<String> playerAnswer();
	List<Proposition> rangeArray() throws IOException;
	List<Integer> runConditions(List<String> y, List<Integer> x, List<Proposition> range);
}
