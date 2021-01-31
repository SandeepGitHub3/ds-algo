package com.sherlock.learn.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {

	public static void main(String[] args) {
		int[] nums = new int[3];
		nums[0]=1;
		nums[1]=2;
		nums[2]=3;
		List<List<Integer>> finalList = new ArrayList<>();
		permuteHelper(finalList,new ArrayList<Integer>(),nums,0);
		System.out.println(finalList);
	}

	private static void permuteHelper(List<List<Integer>> list, List<Integer> tempList, int [] nums,int currentIndex){
		if(tempList.size() == nums.length){
			list.add(new ArrayList<>(tempList));
		} else{
			int choose = nums[currentIndex];
			tempList.add(choose);
			permuteHelper(list,tempList, Arrays.copyOfRange(nums,currentIndex+1,nums.length-1),currentIndex+1);
			//tempList.remove(currentIndex);
			/*for(int i = 0; i <= tempList.size(); i++){
				tempList.add(i, nums[tempList.size()]);
				permuteHelper(list, tempList, nums);
				tempList.remove(i);
			}*/
		}
	}
}
