package com.sherlock.learn.backtracking;

import java.util.ArrayList;
import java.util.List;

public class DiceSum {

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		List<List<Integer>> finalList = new ArrayList<>();
		diceSum(3, 0, 15, new ArrayList(), finalList);
		Long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(finalList);
	}

	private static void diceSum(int dice, int sumSoFar, int desiredSum, List<Integer> chosen, List<List<Integer>> finalList) {
		if (dice == 0) {
			//if (sumSoFar == desiredSum)
				finalList.add(new ArrayList<>(chosen));
		} else {
			for (int i = 1; i <= 6; i++) {
				if((sumSoFar + i + (1*(dice-1)))<=desiredSum &&
						(sumSoFar + i + (6*(dice-1)))>=desiredSum) {
					System.out.println("Dice:"+ dice + "SumSoFar" + sumSoFar);
					chosen.add(i);
					diceSum(dice - 1, sumSoFar + i, desiredSum, chosen, finalList);
					chosen.remove(chosen.size() - 1);
				}
			}
		}
	}
}
