package com.lavanya.randomnumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumber {
	
	public static List<Integer> getRandom(){		
		List<Integer> randomCombi = new ArrayList<>();
		randomCombi.add(0);
		randomCombi.add(0);
		randomCombi.add(0);
		randomCombi.add(0); 
		
		Random random = new Random();

		for (int i = 0; i < randomCombi.size(); i++){
			int j = random.nextInt(10);
			randomCombi.set(i,j);
		};
		
		return randomCombi;
	}
	
	


	public static void main(String[] args) {
		System.out.println(getRandom());

	}

}