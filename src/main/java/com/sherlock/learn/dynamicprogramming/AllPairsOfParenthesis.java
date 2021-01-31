package com.sherlock.learn.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPairsOfParenthesis {

	public static void main(String[] args) {
		List<String> set = new ArrayList<>();
		System.out.println(generateAllPairsOfParenthesis(3, set).toString());
	}

	private static List<String> generateAllPairsOfParenthesis(int i, List<String> set) {

		System.out.println("Evaluate for :" + i);
		if (i == 1) {
			set.add("()");
			return set;
		} else {
			generateAllPairsOfParenthesis(i - 1, set).forEach(s -> {
						set.add("(" + s + ")");
						set.add("()" + s);
						set.add(s + "()");
					}
			);
		}
		return set;
	}
}
