package com.sherlock.learn.dynamicprogramming;

public class Coins {

	public static void main(String[] args) {
		int[] denoms = {25,10,5,1};

		System.out.println("numofWays" + makeChange(100,denoms,0));
	}

	private static int makeChange(int sum, int[] denoms, int index) {
		System.out.println("makeChange:" + "---" +sum + "---" + index);
		if(index>=denoms.length-1){
			System.out.println("return 1");
			return 1;
		}

		int denomAmount = denoms[index];
		int ways = 0;
		for(int i=0;(i*denomAmount)<=sum;i++){
			int amountRenaming = sum-(i*denomAmount);
			System.out.println("amountRenaming" + amountRenaming);
			ways = ways + makeChange(amountRenaming,denoms,index+1);
			System.out.println("ways: " + ways);
		}
		return ways;
	}
}
