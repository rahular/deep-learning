package com.rahul.deeplearning.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class IO {
	private static String path = "./logs/big";

	public static int[][] getDataX() {
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

	public static int[][] getDataY(String path) {
		String line;
		ArrayList<Integer> evals = new ArrayList<Integer>();
		int[][] train_Y = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path
					+ ".bpnl")));
			while ((line = br.readLine()) != null) {
				evals.add(Integer.parseInt(line.split(" ")[1]));
			}
			br.close();

			train_Y = new int[evals.size()][];
			for (int i = 0; i < evals.size(); i++) {
				train_Y[i] = new int[2];
				if (evals.get(i) > 0)
					train_Y[i][0] = 1;
				else
					train_Y[i][1] = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return train_Y;
	}

	public static void storeData(int[][] data) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(path + ".bin"));
			oos.writeObject(data);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int[][] readData(String path) {
		int[][] data = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					path + ".bin"));
			data = (int[][]) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void main(String[] args) {
		int[][] data = getDataX();
		storeData(data);
	}
}
