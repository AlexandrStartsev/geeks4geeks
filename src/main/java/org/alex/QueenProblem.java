package org.alex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://practice.geeksforgeeks.org/problems/n-queen-problem/0 
 * 
 *  instead of keeping track of currently placed queens this algorithm marks fields under attack with {@code bit = 1} in array of bits.
 *  working board is represented by linear array of bytes. 
 *  all possible bit matrices representing attack coverage can be precomputed and merged with working board with {@code or} operation.    
 *  
 *  it is non-recursive version. Instead, manual stack of previous decisions is maintained. 
 * 
 *   
 * */

public class QueenProblem {
	
	private final int size;
	
	public QueenProblem(int size) {
		this.size = size;
	}
	
	private static Function<Integer, byte []>allocate = size -> new byte[(int)Math.round(Math.ceil(((double)size*size)/8.))];
	
	private void setBit(byte[] board, int rank, int file) {
		int pos = rank*size + file;
		board[pos >> 3] |= (1 << (pos & 0x7));
	}
	
	private boolean isBitSet(byte[] board, int pos) {
		return (board[pos >> 3] & (1 << (pos & 0x7))) != 0;
	}
	
	private void unsetBit(byte[] board, int rank, int file) {
		int pos = rank*size + file;
		if(isBitSet(board, pos)) {
			board[pos >> 3] -= (1 << (pos & 0x7));
		}
	}	
	
	private byte[] mask(int index) {
		byte [] board = allocate.apply(size);
		int rank = index / size;
		int file = index - rank*size;
		for(int i = 0; i < size; i++) {
			setBit(board, rank, i);
			setBit(board, i, file);
			if(rank - i >= 0 && file - i >= 0)       setBit(board, rank - i, file - i);
			if(rank - i >= 0 && file + i < size)     setBit(board, rank - i, file + i);
			if(rank + i < size && file - i >= 0)     setBit(board, rank + i, file - i);
			if(rank + i < size && file + i < size)   setBit(board, rank + i, file + i);
		}
		unsetBit(board, rank, file);
		return board;
	}
	
	private byte[] cover(byte []currentBoard, byte []attackMask) {
		byte [] newBoard = Arrays.copyOf(currentBoard, currentBoard.length);
		for(int i = 0; i < currentBoard.length; i++) {
			newBoard[i] |= attackMask[i];
		}
		return newBoard;
	}
	
	public String toBits(byte board[]) {
		return IntStream.range(0, size).mapToObj(rank ->
			IntStream.range(0, size).mapToObj(file -> isBitSet(board, rank*size + file) ? "1" : "0").collect(Collectors.joining(" "))
		).collect(Collectors.joining("\n"));
	}
	
	public String toIndices(byte board[]) {
		List<Integer> indexes = new ArrayList<>();
		for(int i = 0; i < size*size; i++) {
			if(!isBitSet(board, i)) {
				indexes.add(i - (i / size) * size + 1);
			}
		}
		return indexes.stream().map(Object::toString).collect(Collectors.joining(" "));
	}
	
	public List<byte[]> solve() {
		List<byte []> solutions = new ArrayList<>();
		byte [] board = allocate.apply(size);
		byte [][] masks = IntStream.range(0, size*size).mapToObj(i -> mask(i)).toArray(byte [][]::new); 
		Stack<byte []> boardStack = new Stack<>();
		Stack<Integer> posStack = new Stack<>();
		
		for(int currentRow = 0, pos = 0; ; ) {
			while(pos >= (currentRow + 1)*size) {
				// unable to place queen -- restore from stack
				if(posStack.isEmpty()) {
					return solutions;
				} 
				pos = posStack.pop() + 1;
				board = boardStack.pop();
				currentRow = posStack.size();
			}
			if(!isBitSet(board, pos)) { // cell not under attack - cover with mask and proceed to next row
				boardStack.push(board);
				posStack.push(pos);
				board = cover(board, masks[pos]);
				pos = (++currentRow)*size;
				if(currentRow == size) {
					solutions.add(board);
					currentRow --;
				}
			} else {
				pos++;
			}
		}
	}
	
	public static void main(String[] args) {
	    try(Scanner ix = new Scanner(System.in)) {
		    for(int i = ix.nextInt(); i > 0; i--) {
		    	QueenProblem qp = new QueenProblem(ix.nextInt());
		    	List<byte[]> sln = qp.solve();
		    	System.out.println(sln.size() > 0 ? sln.stream().map(qp::toIndices).collect(Collectors.joining("] [", "[", "]")) : "-1");
		    }
	    }
	}	
}
