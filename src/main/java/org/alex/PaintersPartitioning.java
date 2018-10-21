package org.alex;

import java.util.Scanner;
import java.util.stream.IntStream;

public class PaintersPartitioning {
	
	// https://practice.geeksforgeeks.org/problems/the-painters-partition-problem/0
	
	public int compute(int workersLeft, int []J) {
		int [][]M = IntStream.range(0, J.length).mapToObj(i -> new int[workersLeft]).toArray(int [][]::new);
		M[0][0] = J[0];
		for(int i = 1; i < J.length; i++) {
			M[i][0] = M[i-1][0] + J[i];
		}
		for(int j = 1; j < workersLeft; j++) {
			for(int i = 0; i < J.length; i++) {
				int jj = j, ii = i;
				M[i][j] = IntStream.rangeClosed(j, i).map(x -> Math.max(M[x-1][jj-1], IntStream.rangeClosed(x, ii).map(q -> J[q]).sum())).min().orElse(M[i][j-1]);
			}
		}
		return M[J.length-1][workersLeft - 1];
	}
	
	public static void main(String[] args) {
		PaintersPartitioning logic = new PaintersPartitioning();
	    try(Scanner ix = new Scanner(System.in)) {
		    for(int i = ix.nextInt(); i > 0; i--) {
		    	int K = ix.nextInt();
		    	int N = ix.nextInt();
		    	int []array = new int[N];
		    	for(int j = 0; j < N; j++) array[j] = ix.nextInt();
		        System.out.println(logic.compute(K, array));
		    }
	    }
	}
}
