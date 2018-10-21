package org.alex;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PhoneDirectory {
	// https://practice.geeksforgeeks.org/problems/phone-directory/0
	
	static class DictNode {
		boolean isWord;
		Map<Character, DictNode> follow;
		public DictNode() {
			isWord = false;
			follow = new HashMap<>();
		}
		
		void storeWord(Iterator<Character> rest) {
			if(!rest.hasNext()) {
				isWord = true;
			} else {
				Character c = rest.next();
				follow.computeIfAbsent(c, x -> new DictNode()).storeWord(rest);
			}
		}
		
		void storeWord(String word) {
			storeWord(word.chars().mapToObj(i -> (char)i).iterator());
		}
		
		DictNode queryWord(Iterator<Character> str) {
			Character c = str.next();
			DictNode next = follow.getOrDefault(c, new DictNode());
			return str.hasNext() ? next.queryWord(str) : next;
		}
		
		DictNode queryWord(String word) {
			return queryWord(word.chars().mapToObj(i -> (char)i).iterator());
		}
		
		void allWords(String prefix, Collection<String> storage) {
			if(isWord) storage.add(prefix);
			follow.forEach((k, v) -> v.allWords(prefix + k, storage));
		}
		
		Collection<String> allWords(String prefix) {
			TreeSet<String> ret = new TreeSet<>();
			queryWord(prefix).allWords(prefix, ret);
			return ret; 
		}
	}
	
	public Collection<String> compute(List<String> words, String query) {
		DictNode root = new DictNode();
		words.forEach(root::storeWord);
		Collection<String> ret = 
			IntStream.range(0, query.length())
				.mapToObj(i -> query.substring(0, i + 1))
				.map(root::allWords)
				.map(c -> c.stream().collect(Collectors.joining(" ")))
				.map(s -> s.isEmpty() ? "0" : s)
				.collect(Collectors.toList());
		return ret;
	}
	
	public static void main (String[] args) {
	    try(Scanner ix = new Scanner(System.in)) {
		    for(int i = Integer.parseInt(ix.nextLine()); i > 0; i--) {
		    	int N = Integer.parseInt(ix.nextLine());
		    	String []array = ix.nextLine().split(" ");
		    	String str = ix.nextLine();
		    	new PhoneDirectory().compute(Arrays.asList(array), str).forEach(System.out::println);
		    }
	    }
	}
}
