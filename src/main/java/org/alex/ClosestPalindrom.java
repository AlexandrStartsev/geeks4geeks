package org.alex;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

/*
   https://practice.geeksforgeeks.org/problems/closest-palindrome/0

    Closest Palindrome 
     as of 10/14/18 statistics 
     Submissions: 22203   Accuracy: 6.38%   Difficulty: Hard 

     code below passes. 
  
*/


public class ClosestPalindrom {

	public Long complete(final int []cur, final int pos) {
		long value = 0;
		int p;
		for(p = 0; p < pos; p++) {
			value = value*10 + cur[p];
		}
		for(; p < cur.length; p++) {
			value = value*10 + cur[cur.length - p - 1];
		}  
		return Long.valueOf(value);
	}
	
	void premutate(final int []input, final int pos, Consumer<Long> report) {
		boolean pastHalf = input.length % 2 == 0 ? pos == input.length/2 : pos == (input.length + 1)/2;
		if(pastHalf) {
			report.accept(this.complete(input, pos));
		} else {
			for(int i = -1; i <= 1; i++) {
				int [] next = Arrays.copyOf(input, input.length);
				int a = next[pos] + i;
				next[pos] = a > 9 ? 0 : a < 0 ? 9 : a;
				premutate(next, pos+1, report);
			}
		}
	}
	
	long currentClosest, currentDiff;
	
	public String compute(final String number) {
		int [] input = Arrays.asList(number.split("")).stream().mapToInt(s -> s.charAt(0) - '0').toArray();
		long origin = Long.valueOf(number);
		this.currentClosest = 0;
		this.currentDiff = origin;
		
		Stream.of(Arrays.copyOfRange(input, 1, input.length), input).forEach( 
			data ->	this.premutate(data, 0, value -> {
				long diff = Math.abs(value - origin);
				if(diff < this.currentDiff) {
					this.currentClosest = value;
					this.currentDiff = diff;
				}
			})
		);
		
		return String.valueOf(this.currentClosest);
	}
	
	public static void main (String[] args) {
	    ClosestPalindrom logic = new ClosestPalindrom();
	    try(Scanner ix = new Scanner(System.in)) {
		    for(int i = ix.nextInt(); i > 0; i--) {
		        System.out.println(logic.compute(String.valueOf(ix.nextLong())));
		    }
	    }
	}
}
