package org.alex;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

	static class Impl {
	    final Map<Integer, Integer> cache;
	    final int maxCapacity;
	    
	    public Impl(int N) {
	        this.maxCapacity = N;
	        this.cache = new LinkedHashMap<>();
	    }
	    
	    public int get(int x) {
	    	final Integer key = Integer.valueOf(x);
	    	final Integer val = this.cache.remove(key);
	    	if(val != null) {
	    		this.cache.put(key, val);
	    		return val.intValue();
	    	}
	    	return -1;
	    }
	    
	    public void set(int x, int y) {
	    	final Integer key = Integer.valueOf(x);
	    	this.cache.remove(key);
	    	this.cache.put(key, Integer.valueOf(y));
	    	while(this.cache.size() > this.maxCapacity) {
	    		this.cache.remove(this.cache.keySet().stream().findFirst().get());
	    	}	
	    }
	}
	
	public static void main(String[] args) {
		
	}

}
