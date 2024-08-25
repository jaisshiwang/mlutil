package io.github.jaisshiwang.mlutil.neuralnetwork.utils;

import io.github.jaisshiwang.mlutil.matrix.DenseMatrix;
import io.github.jaisshiwang.mlutil.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for neural network-related functions.
 */
public class NeuralNetworkUtils {

    /**
     * Converts a matrix to a list of Doubles.
     *
     * @param matrix The matrix to convert.
     * @return A list of Doubles representing the matrix elements.
     */
    public static List<Double> matrixToList(Matrix matrix) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                result.add(matrix.get(i, j));
            }
        }
        return result;
    }

    /**
     * Converts a list of Doubles to a DenseMatrix.
     *
     * @param list The list of Doubles.
     * @param rows The number of rows for the resulting matrix.
     * @param cols The number of columns for the resulting matrix.
     * @return A DenseMatrix representing the list elements.
     */
    public static Matrix listToMatrix(List<Double> list, int rows, int cols) {
        double[][] data = new double[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = list.get(index++);
            }
        }
        return new DenseMatrix(rows, cols, data);
    }
}

