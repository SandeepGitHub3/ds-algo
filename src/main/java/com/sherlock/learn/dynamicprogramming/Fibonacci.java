package com.sherlock.learn.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class Fibonacci {

	public static void main(String[] args) {
		fiboHelper(10);
		fiboHelper(20);
		fiboHelper(30);
		fiboHelper(40);
		fiboHelper(50);
	}

	private static void fiboHelper(int i) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		System.out.print(memoisedFibo(i));
		stopwatch.stop();
		System.out.println("----"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}
	
	private static int fibo(int i) {
		if (i<=1)
			return i;
		else {
			return (fibo(i-1)+fibo(i-2));
		}
	}
	
	private static int memoisedFibo(int n) {
		int n1=0;
		int n2=1;
		int n3=n1+n2;
		
		for (int i=2;i<n;i++) {
			n1=n2;
			n2=n3;
			n3= n1+n2;
		}
		return n3;
	}
}