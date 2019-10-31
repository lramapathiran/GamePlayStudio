package com.lavanya.defenseur;

public class Proposition {
	
	public int max;
	public int min;
	
	public Proposition() {
		
	}
	
	public Proposition(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public int getMax() {
		return max;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	
	

}

