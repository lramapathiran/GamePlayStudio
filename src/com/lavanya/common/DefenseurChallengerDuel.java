package com.lavanya.common;

import java.io.IOException;
import java.util.List;

public interface DefenseurChallengerDuel {
	int properties(String key) throws IOException;
	List<Integer> getRandom(int min, int max) throws IOException;
	boolean winAnswer(List<Character> y) throws IOException;
}
