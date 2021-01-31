package com.sherlock.learn.sorting;

import java.util.Arrays;

/*
Result--No of Elements : millisecs
10000 : 5
20000 : 6
40000 : 20
80000 : 19
160000 : 56
320000 : 129
640000 : 273
1280000 : 469
2560000 : 506
5120000 : 979
10240000 : 2203
20480000 : 5545
40960000 : 9482
*/
public class MergeSort {

	public static int[] mergeSort(int[] arr) {
		//System.out.println("mergeSort" + Arrays.toString(arr));
		int length = arr.length;
		if (length == 1)
			return arr;

		int[] leftHalf = Arrays.copyOfRange(arr, 0, (length + 1) / 2);
		int[] rightHalf = Arrays.copyOfRange(arr, (length + 1) / 2, length);
		int[] leftHalfResult = mergeSort(leftHalf);
		int[] rightHalfResult = mergeSort(rightHalf);
		return merge(leftHalfResult, rightHalfResult);
	}

	private static int[] merge(int[] leftArray, int[] rightArray) {
		//System.out.println("merge" + Arrays.toString(leftArray) + "&" + Arrays.toString(rightArray));
		int[] target = new int[leftArray.length + rightArray.length];
		int leftArrayIndex = 0;
		int rightArrayIndex = 0;
		int targetIndex = 0;

		while (leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length) {
			if (leftArray[leftArrayIndex] < rightArray[rightArrayIndex]) {
				target[targetIndex] = leftArray[leftArrayIndex];
				leftArrayIndex++;
			} else {
				target[targetIndex] = rightArray[rightArrayIndex];
				rightArrayIndex++;
			}
			targetIndex++;
		}

		if (leftArrayIndex != leftArray.length) {
			System.arraycopy(leftArray, leftArrayIndex, target, targetIndex, leftArray.length-leftArrayIndex);
		} else {
			System.arraycopy(rightArray, rightArrayIndex, target, targetIndex, rightArray.length-rightArrayIndex);
		}
		//System.out.println("Merged Result : " + Arrays.toString(target));
		return target;
	}
}
