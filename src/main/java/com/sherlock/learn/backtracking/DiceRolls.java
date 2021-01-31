package com.sherlock.learn.backtracking;

import java.util.ArrayList;
import java.util.List;

public class DiceRolls {

	public static void main(String[] args) {
		List<List<Integer>> finalList= new ArrayList<>();
		rollDice(3,new ArrayList(),finalList);
		System.out.println(finalList);
	}

	private static void rollDice(int dice, List<Integer> chosen,List<List<Integer>> finalList){
		if (dice==0){
			finalList.add(new ArrayList<>(chosen));
		}else{
			for(int i=1;i<=6;i++){
				chosen.add(i);
				rollDice(dice-1, chosen,finalList);
				chosen.remove(chosen.size()-1);
			}
		}
	}
}
