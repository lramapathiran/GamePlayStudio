package com.lavanya.common;

import java.util.List;

public interface ChallengerDuel {
	List<Integer> playerCombi();
	List<String> computerAnswer(List<Integer> x, List<Integer> y);
}
