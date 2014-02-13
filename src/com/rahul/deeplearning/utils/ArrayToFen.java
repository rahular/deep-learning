package com.rahul.deeplearning.utils;

import java.util.HashMap;

public class ArrayToFen {
	/**
	 * blank square 	- 1111 - number
	 * white pawn 		- 0001 - P
	 * black pawn 		- 1001 - p
	 * white knight 	- 0010 - N
	 * black knight 	- 1010 - n
	 * white bishop 	- 0011 - B
	 * black bishop 	- 1011 - b
	 * white rook 		- 0100 - R
	 * black rook 		- 1100 - r
	 * white queen 		- 0101 - Q
	 * black queen 		- 1101 - q
	 * white king 		- 0110 - K
	 * black king 		- 1110 - k
	 */
	
	static HashMap<String, String> map;
	
	public static void initialize() {
		map = new HashMap<String, String>();
		map.put("0001", "P");
		map.put("1001", "p");
		map.put("0010", "N");
		map.put("1010", "n");
		map.put("0011", "B");
		map.put("1011", "b");
		map.put("0100", "R");
		map.put("1100", "r");
		map.put("0101", "Q");
		map.put("1101", "q");
		map.put("0110", "K");
		map.put("1110", "k");
	}
	
	public static String convert(int[] bits) {
		StringBuilder sb = new StringBuilder();
		for(int i : bits) {
			sb.append(i);
		}
		return convert(sb.toString());
	}
	
	public static String convert(String bits) {
		StringBuilder sb = new StringBuilder();		
		int i, blanks = 0, rows = 0;

		// BOARD STATE
		for (i = 0; i < bits.length() - 6; i = i + 4) {
			String piece = bits.substring(i, i + 4);
			if (piece.equals("1111")) {
				blanks++;
			} else {
				if (blanks > 0) {
					sb.append(blanks);
					blanks = 0;
				}
				sb.append(map.get(piece));
			}
			rows++;
			if (rows % 8 == 0) {
				if (blanks > 0) {
					sb.append(blanks);
					blanks = 0;
				}
				sb.append('/');
			}
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.append(' ');

		// GAME STATE
		// Whose turn it is
		if(bits.charAt(i++) == '1') sb.append('w');
		else sb.append('b');
		
		sb.append(' ');
		boolean flag = false;
		
		// White can castle king side
		if(bits.charAt(i++) == '1') {
			sb.append('K');
			flag = true;
		}
		
		// White can castle queen side
		if(bits.charAt(i++) == '1') {
			sb.append('Q');
			flag = true;
		}
		
		// Black can castle king side
		if(bits.charAt(i++) == '1') {
			sb.append('k');
			flag = true;
		}
		
		// Black can castle queen side
		if(bits.charAt(i++) == '1') {
			sb.append('q');
			flag = true;
		}
		if(!flag) sb.append('-');
		sb.append(' ');
		
		// Is en-passant possible? <= cannot be recovered
		// 50 move rule <= cannot be recovered
		sb.append("- 0 1");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String bits1 = "1100111111111101111111001110111110011001100111111111001010011001111111111010100111111010111111111111111111111111111111111111111111110010000111111111111111111111111111111111111111111111000111110001000111110001000100011111111101001111001101010110111111111011001000";
		String bits2 = "1100111111111101111111001110111110011001100111111111100110011001111111111010100111111010111111111111111111111111100111111111111111111111000111111111111111111111111111111111111111111011000111110001000111110001000100011011111101001111001101010110111111110100011000";
		
		ArrayToFen.initialize();
		System.out.println(ArrayToFen.convert(bits1));
		System.out.println(ArrayToFen.convert(bits2));
}
}
