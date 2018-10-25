package org.alex;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Playground {

	
	public static void main(String[] args) {
		Consumer<Character> act = System.out::println;
		act.accept("zzz".chars().mapToObj(i -> (char)i).iterator().next());
		
		System.out.println(IntStream.rangeClosed(5, 5).max().orElse(3));
		//new TreeMap(); 
		//Collections.
		
		//int q = Spliterator. OfInt. IMMUTABLE;
		//IntStream.iterate(0, i -> i + 1).sorted().parallel().limit(100).peek(System.out::println).count();
		// parallel().
		/*try {
			new ForkJoinPool().submit(new MyAction()).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
