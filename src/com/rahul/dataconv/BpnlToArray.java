package com.rahul.dataconv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BpnlToArray {
	private static String path = "./logs/big";

	private static int[][] getData() {
		String line;
		ArrayList<String> bits = new ArrayList<String>();
		int[][] train_X = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path
					+ ".bpnl")));
			while ((line = br.readLine()) != null) {
				bits.add(line.split(" ")[0]);
			}
			br.close();

			train_X = new int[bits.size()][];
			for (int i = 0; i < bits.size(); i++) {
				int len = bits.get(i).length();
				train_X[i] = new int[len];
				for (int j = 0; j < len; j++) {
					train_X[i][j] = (int) bits.get(i).charAt(j) - 48;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return train_X;
	}

	private static void storeData(int[][] data) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(path + ".bin"));
			oos.writeObject(data);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int[][] data = getData();
		storeData(data);
	}
}
