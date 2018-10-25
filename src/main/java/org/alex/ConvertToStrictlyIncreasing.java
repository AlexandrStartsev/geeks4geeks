package org.alex;

import java.util.Scanner;

public class ConvertToStrictlyIncreasing {
    // https://practice.geeksforgeeks.org/problems/convert-to-strictly-increasing-array/0
	
	public int compute(int [] A) {
		int []V = new int[A.length];
		V[0] = 0;
		for(int x = 1; x < A.length; x++) {
			int min = x;
			for(int i = 1; i == 1 || min >= i; ++i) {
				if(A[x] >= A[x - i] + i) {
					min = Math.min(min, V[x - i] + i - 1);
				}
			}
			V[x] = min;
		}
		int min = A.length;
		for(int i = 1; min > i; ++i) {
			min = Math.min(min, V[A.length - i] + i - 1);
		}
		return min;
	}
	
	public static void main(String[] args) {
		ConvertToStrictlyIncreasing logic = new ConvertToStrictlyIncreasing();
	    try(Scanner ix = new Scanner(System.in)) {
		    for(int i = ix.nextInt(); i > 0; i--) {
		    	int N = ix.nextInt();
		    	int []array = new int[N];
		    	for(int j = 0; j < N; j++) array[j] = ix.nextInt();
		        System.out.println(logic.compute(array));
		    }
	    }
	}	
}
