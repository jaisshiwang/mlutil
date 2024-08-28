package io.github.jaisshiwang.mlutil.matrix;

/**
 * A dense matrix implementation that stores all elements in a 2D array.
 */
public class DenseMatrix extends Matrix {
    private double[][] data;

    /**
     * Constructs a DenseMatrix with the specified number of rows and columns.
     * The elements are initialized to random values between -1 and 1.
     * 
     * @param rows Number of rows.
     * @param cols Number of columns.
     */
    public DenseMatrix(int rows, int cols) {
        super(rows, cols);
        this.data = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    /**
     * Constructs a DenseMatrix with the specified number of rows, columns, and data.
     * 
     * @param rows Number of rows.
     * @param cols Number of columns.
     * @param data 2D array representing the matrix data.
     * @throws IllegalArgumentException if the data dimensions do not match the specified rows and columns.
     */
    public DenseMatrix(int rows, int cols, double[][] data) {
        super(rows, cols);
        if (data.length != rows || data[0].length != cols) {
            throw new IllegalArgumentException("Data dimensions do not match specified rows and columns.");
        }
        this.data = data;
    }

    @Override
    public double get(int row, int col) {
        return data[row][col];
    }

    @Override
    public void set(int row, int col, double value) {
        data[row][col] = value;
    }

    @Override
    public double[] getRow(int row) {
        return data[row];
    }

    @Override
    public Matrix add(Matrix other) {
        validateDimensions(other);
        DenseMatrix result = new DenseMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, this.get(i, j) + other.get(i, j));
            }
        }
        return result;
    }

    @Override
    public Matrix subtract(Matrix other) {
        validateDimensions(other);
        DenseMatrix result = new DenseMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, this.get(i, j) - other.get(i, j));
            }
        }
        return result;
    }

    @Override
    public Matrix multiply(Matrix other) {
        validateMultiplication(other);
        DenseMatrix result = new DenseMatrix(this.rows, other.getCols());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getCols(); j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.get(i, k) * other.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    @Override
    public Matrix multiply(double scalar) {
        DenseMatrix result = new DenseMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, this.get(i, j) * scalar);
            }
        }
        return result;
    }

    @Override
    public Matrix transpose() {
        DenseMatrix result = new DenseMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(j, i, this.get(i, j));
            }
        }
        return result;
    }

    @Override
    public Matrix copy() {
        double[][] copiedData = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, copiedData[i], 0, cols);
        }
        return new DenseMatrix(rows, cols, copiedData);
    }

    public static DenseMatrix fromArray(double[] array) {
        double[][] data = new double[array.length][1];
        for (int i = 0; i < array.length; i++) {
            data[i][0] = array[i];
        }
        return new DenseMatrix(array.length, 1, data);
    }
    
}