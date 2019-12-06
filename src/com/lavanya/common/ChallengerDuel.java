package com.lavanya.common;

import java.io.IOException;
import java.util.List;

public interface ChallengerDuel {
	List<Integer> playerCombi();
	List<Character> computerAnswer(List<Integer> x, List<Integer> y) throws IOException;
}
