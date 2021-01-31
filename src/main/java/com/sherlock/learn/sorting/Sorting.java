package com.sherlock.learn.sorting;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

public class Sorting {

	public static void main(String[] args) {
		for (int i = 10000; i <= Integer.MAX_VALUE; ) {
			runSortDemo(i);
			i = i * 2;
		}
		//runSortDemo( 1000000);
	}

	private static void runSortDemo(int n) {
		int[] arr = generateUnsortedArrayOfElements(n);
		//System.out.println(Arrays.toString(arr));
		Instant start = Instant.now();
		int[] result = QuickSort.quickSort(arr);
		Instant finish = Instant.now();
		isSorted(result);
		//System.out.println("Sorted : " + isSorted(result));
		//System.out.println(Arrays.toString(result));
		System.out.println(n + " : " + Duration.between(start, finish).toMillis());

	}

	private static Boolean isSorted(int[] arr) {

		for (int i = 1; i <= arr.length - 1; i++) {
			if (!(arr[i - 1] <= arr[i])) {
				System.exit(1);
				return false;
			}
		}
		return true;
	}

	private static int[] generateUnsortedArrayOfElements(int n) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i < n; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		return list.stream().mapToInt(i -> i).toArray();
	}
}
