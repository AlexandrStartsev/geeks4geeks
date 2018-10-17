package org.alex;

import java.util.Scanner;

public class SumDiffBits {
	//https://practice.geeksforgeeks.org/problems/find-sum-of-different-corresponding-bits-for-all-pairs/0

	public int calc(int[] array) {
		int N = array.length;		
		int []total = new int[32];
		int []bits = new int[N*32];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 32; j++) {
				total[j] += ( bits[i*32 + j] = (array[i] &(1<<j)) > 0 ? 1 : 0) ;
			}
		}
		long ret = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 32; j++) {
				ret += (bits[i*32 + j] == 0 ? total[j] : N - total[j]);
			}
		}
		
		return (int)(ret%1000000007);
	}
	
	public static void main (String[] args) {
		SumDiffBits logic = new SumDiffBits();
	    try(Scanner ix = new Scanner(System.in)) {
		    for(int i = ix.nextInt(); i > 0; i--) {
		    	int N = ix.nextInt();
		    	int []array = new int[N];
		    	for(int j = 0; j < N; j++) array[j] = ix.nextInt();
		        System.out.println(logic.calc(array));
		    }
	    }
	}
}
