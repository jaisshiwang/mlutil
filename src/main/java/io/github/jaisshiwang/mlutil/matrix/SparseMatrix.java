package io.github.jaisshiwang.mlutil.matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * A sparse matrix implementation that efficiently stores non-zero elements using a nested map.
 */
public class SparseMatrix extends Matrix {
    private final Map<Integer, Map<Integer, Double>> data;

    /**
     * Constructs a SparseMatrix with the specified number of rows and columns.
     * 
     * @param rows Number of rows.
     * @param cols Number of columns.
     */
    public SparseMatrix(int rows, int cols) {
        super(rows, cols);
        this.data = new HashMap<>();
    }

    @Override
    public double get(int row, int col) {
        return data.getOrDefault(row, new HashMap<>()).getOrDefault(col, 0.0);
    }

    @Override
    public void set(int row, int col, double value) {
        if (value != 0) {
            data.computeIfAbsent(row, k -> new HashMap<>()).put(col, value);
        } else if (data.containsKey(row)) {
            data.get(row).remove(col);
            if (data.get(row).isEmpty()) {
                data.remove(row);
            }
        }
    }

    @Override
    public double[] getRow(int row) {
        double[] result = new double[cols];
        if (data.containsKey(row)) {
            for (Map.Entry<Integer, Double> entry : data.get(row).entrySet()) {
                result[entry.getKey()] = entry.getValue();
            }
        }
        return result;
    }

    @Override
    public Matrix add(Matrix other) {
        validateDimensions(other);
        SparseMatrix result = new SparseMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sum = this.get(i, j) + other.get(i, j);
                if (sum != 0) {
                    result.set(i, j, sum);
                }
            }
        }
        return result;
    }

    @Override
    public Matrix subtract(Matrix other) {
        validateDimensions(other);
        SparseMatrix result = new SparseMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double difference = this.get(i, j) - other.get(i, j);
                if (difference != 0) {
                    result.set(i, j, difference);
                }
            }
        }
        return result;
    }

    @Override
    public Matrix multiply(Matrix other) {
        validateMultiplication(other);
        SparseMatrix result = new SparseMatrix(this.rows, other.getCols());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getCols(); j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.get(i, k) * other.get(k, j);
                }
                if (sum != 0) {
                    result.set(i, j, sum);
                }
            }
        }
        return result;
    }

    @Override
    public Matrix multiply(double scalar) {
        SparseMatrix result = new SparseMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double product = this.get(i, j) * scalar;
                if (product != 0) {
                    result.set(i, j, product);
                }
            }
        }
        return result;
    }

    @Override
    public Matrix transpose() {
        SparseMatrix result = new SparseMatrix(cols, rows);
        for (Map.Entry<Integer, Map<Integer, Double>> rowEntry : data.entrySet()) {
            int row = rowEntry.getKey();
            for (Map.Entry<Integer, Double> colEntry : rowEntry.getValue().entrySet()) {
                int col = colEntry.getKey();
                result.set(col, row, colEntry.getValue());
            }
        }
        return result;
    }

    @Override
    public Matrix copy() {
        SparseMatrix result = new SparseMatrix(rows, cols);
        for (Map.Entry<Integer, Map<Integer, Double>> rowEntry : data.entrySet()) {
            int row = rowEntry.getKey();
            for (Map.Entry<Integer, Double> colEntry : rowEntry.getValue().entrySet()) {
                int col = colEntry.getKey();
                result.set(row, col, colEntry.getValue());
            }
        }
        return result;
    }

    /**
     * Swaps two rows in the sparse matrix.
     * If a row has no non-zero elements, it remains empty after the swap.
     *
     * @param row1 The first row index.
     * @param row2 The second row index.
     * @throws IllegalArgumentException if the row indices are out of bounds.
     */
    @Override
    public void swapRows(int row1, int row2) {
        if (row1 < 0 || row1 >= rows || row2 < 0 || row2 >= rows) {
            throw new IllegalArgumentException("Row index out of bounds.");
        }

        if (row1 == row2) return;

        Map<Integer, Double> temp = data.get(row1);
        data.put(row1, data.get(row2));
        data.put(row2, temp);
    }
}

