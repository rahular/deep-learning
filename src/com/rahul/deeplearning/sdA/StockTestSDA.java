package com.rahul.deeplearning.sdA;

import java.util.Random;

import com.rahul.deeplearning.utils.IO;

public class StockTestSDA {
	
	private static void test_sda(int[][] train_X, int[][] train_Y) {
		Random rng = new Random(123);

		double pretrain_lr = 0.1;
		double corruption_level = 0.3;
		int pretraining_epochs = 1000;
		double finetune_lr = 0.1;
		int finetune_epochs = 500;

		int train_N = train_X.length;
		int test_N = 10;
		int n_ins = train_X[0].length;
		int n_outs = 2;
		int[] hidden_layer_sizes = { 200, 200 };
		int n_layers = hidden_layer_sizes.length;

		// construct SdA
		SdA sda = new SdA(train_N, n_ins, hidden_layer_sizes, n_outs, n_layers,
				rng);

		// pretrain
		sda.pretrain(train_X, pretrain_lr, corruption_level, pretraining_epochs);

		// finetune
		sda.finetune(train_X, train_Y, finetune_lr, finetune_epochs);

		// test data
		int[][] test_X = new int[test_N][];
		for (int i = 0; i < test_N; i++) {
			int randomIndex = (int) (Math.random() * train_N);
			// System.out.println(randomIndex);
			test_X[i] = train_X[randomIndex];
		}

		double[][] test_Y = new double[test_N][n_outs];

		// test
		for (int i = 0; i < test_N; i++) {
			sda.predict(test_X[i], test_Y[i]);
			for (int j = 0; j < n_outs; j++) {
				System.out.print(test_Y[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] trainX = IO.readData("./logs/vsmall");
		int[][] trainY = IO.getDataY("./logs/vsmall");
		test_sda(trainX, trainY);
	}
}
