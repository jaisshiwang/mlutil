package io.github.jaisshiwang.mlutil.matrix;

import java.util.List;

/**
 * Utility class providing additional matrix-related methods, such as identity matrix generation and random matrix creation.
 */
public class MatrixUtils {

    /**
     * Converts a matrix to a 1D array.
     * 
     * @param matrix The matrix.
     * @return A 1D array containing the matrix elements.
     */
    public static double[] toArray(Matrix matrix) {
        double[] array = new double[matrix.getRows() * matrix.getCols()];
        int index = 0;
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                array[index++] = matrix.get(i, j);
            }
        }
        return array;
    }

    /**
     * Generates an identity matrix of the specified size.
     * 
     * @param size The size of the identity matrix.
     * @return A new identity matrix.
     */
    public static Matrix identity(int size) {
        DenseMatrix identity = new DenseMatrix(size, size);
        for (int i = 0; i < size; i++) {
            identity.set(i, i, 1.0);
        }
        return identity;
    }

    /**
     * Checks if a matrix is square (has the same number of rows and columns).
     * 
     * @param matrix The matrix to check.
     * @return True if the matrix is square, false otherwise.
     */
    public static boolean isSquare(Matrix matrix) {
        return matrix.getRows() == matrix.getCols();
    }

    /**
     * Checks if a matrix is symmetric (equal to its transpose).
     * 
     * @param matrix The matrix to check.
     * @return True if the matrix is symmetric, false otherwise.
     */
    public static boolean isSymmetric(Matrix matrix) {
        if (!isSquare(matrix)) {
            return false;
        }
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                if (matrix.get(i, j) != matrix.get(j, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Generates a random matrix with the specified number of rows and columns.
     * 
     * @param rows Number of rows.
     * @param cols Number of columns.
     * @return A new matrix with random values.
     */
    public static Matrix randomMatrix(int rows, int cols, double minValue, double maxValue) {
        DenseMatrix matrix = new DenseMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double randomValue = minValue + Math.random() * (maxValue - minValue);
                matrix.set(i, j, randomValue);
            }
        }
        return matrix;
    }
    

    /**
     * Reduces a matrix by removing rows to match the specified new row count.
     * 
     * @param matrix The matrix to reduce.
     * @param newRowCount The new number of rows.
     * @return A new matrix with the reduced number of rows.
     * @throws IllegalArgumentException if the new row count is greater than or equal to the current row count.
     */
    public static Matrix reduceRows(Matrix matrix, int newRowCount) {
        if (newRowCount >= matrix.getRows()) {
            throw new IllegalArgumentException("New row count must be less than the current row count.");
        }
        DenseMatrix result = new DenseMatrix(newRowCount, matrix.getCols());
        for (int i = 0; i < newRowCount; i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                result.set(i, j, matrix.get(i, j));
            }
        }
        return result;
    }

     /**
     * Converts a list of Doubles to a Matrix of specified type.
     *
     * @param list The list of Doubles.
     * @param rows The number of rows for the resulting matrix.
     * @param cols The number of columns for the resulting matrix.
     * @param matrixClass The class type of the matrix to return (e.g., DenseMatrix.class).
     * @param <T> The type of matrix.
     * @return A matrix of type T representing the list elements.
     */
    public static <T extends Matrix> T listToMatrix(List<Double> list, int rows, int cols, Class<T> matrixClass) {
        double[][] data = new double[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = list.get(index++);
            }
        }
        try {
            return matrixClass.getDeclaredConstructor(int.class, int.class, double[][].class).newInstance(rows, cols, data);
        } catch (Exception e) {
            throw new RuntimeException("Error creating matrix instance", e);
        }
    }
}
