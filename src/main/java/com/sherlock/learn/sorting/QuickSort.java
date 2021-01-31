package com.sherlock.learn.sorting;

/*
Result--No of Elements : millisecs
10000 : 2
20000 : 2
40000 : 3
80000 : 9
160000 : 19
320000 : 33
640000 : 77
1280000 : 210
2560000 : 324
5120000 : 559
10240000 : 1104
20480000 : 2368
40960000 : 4892
*/
public class QuickSort {

	public static int[] quickSort(int[] arr){
		return quickSort(arr,0,arr.length-1);
	}

	private static int[] quickSort(int[] arr, int low, int high) {
		if(high<=low){
			return arr;
		}

		int pivot = partition(arr,low, high);
		quickSort(arr,low,pivot-1);
		quickSort(arr,pivot+1,high);
		return arr;
	}

	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i =low-1;

		for(int j=low;j<high;j++){
			if(arr[j]<=pivot){
				i++;
				int temp = arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
			}
		}

		int temp = arr[high];
		arr[high]=arr[i+1];
		arr[i+1]=temp;
		return i+1;
	}
}
