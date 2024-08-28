package io.github.jaisshiwang.mlutil.matrix;

import io.github.jaisshiwang.mlutil.matrix.exceptions.MatrixDimensionException;

/**
 * Abstract class representing a matrix.
 * Provides basic operations that can be performed on matrices.
 */
public abstract class Matrix {
    protected int rows;
    protected int cols;

    /**
     * Constructs a Matrix with the specified number of rows and columns.
     * 
     * @param rows Number of rows in the matrix.
     * @param cols Number of columns in the matrix.
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

	/**
     * Retrieves a row from the matrix as an array.
     *
     * @param row The index of the row to retrieve.
     * @return An array containing the values in the specified row.
     */
    public abstract double[] getRow(int row);

    /**
     * Returns the number of rows in the matrix.
     * 
     * @return Number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the matrix.
     * 
     * @return Number of columns.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Gets the value at the specified row and column.
     * 
     * @param row The row index.
     * @param col The column index.
     * @return The value at the specified position.
     */
    public abstract double get(int row, int col);

    /**
     * Sets the value at the specified row and column.
     * 
     * @param row The row index.
     * @param col The column index.
     * @param value The value to set.
     */
    public abstract void set(int row, int col, double value);

    /**
     * Adds another matrix to this matrix.
     * 
     * @param other The matrix to add.
     * @return A new matrix representing the sum.
     * @throws MatrixDimensionException if the dimensions do not match.
     */
    public abstract Matrix add(Matrix other);

    /**
     * Subtracts another matrix from this matrix.
     * 
     * @param other The matrix to subtract.
     * @return A new matrix representing the difference.
     * @throws MatrixDimensionException if the dimensions do not match.
     */
    public abstract Matrix subtract(Matrix other);

    /**
     * Multiplies this matrix with another matrix.
     * 
     * @param other The matrix to multiply with.
     * @return A new matrix representing the product.
     * @throws MatrixDimensionException if the dimensions are incompatible.
     */
    public abstract Matrix multiply(Matrix other);

    /**
     * Multiplies this matrix by a scalar.
     * 
     * @param scalar The scalar value to multiply by.
     * @return A new matrix with each element multiplied by the scalar.
     */
    public abstract Matrix multiply(double scalar);

    /**
     * Transposes the matrix.
     * 
     * @return A new matrix representing the transpose.
     */
    public abstract Matrix transpose();

    /**
     * Creates a copy of this matrix.
     * 
     * @return A new matrix that is a copy of this matrix.
     */
    public abstract Matrix copy();

	/**
     * Swaps two rows in the matrix.
     *
     * @param row1 The first row index.
     * @param row2 The second row index.
     * @throws IllegalArgumentException if the row indices are out of bounds.
     */
    public abstract void swapRows(int row1, int row2);

    /**
     * Validates that the dimensions of this matrix match another matrix.
     * 
     * @param other The matrix to compare dimensions with.
     * @throws MatrixDimensionException if the dimensions do not match.
     */
    protected void validateDimensions(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new MatrixDimensionException("Matrix dimensions must match.");
        }
    }

    /**
     * Validates that the dimensions of this matrix are compatible with another matrix for multiplication.
     * 
     * @param other The matrix to compare dimensions with.
     * @throws MatrixDimensionException if the dimensions are incompatible.
     */
    protected void validateMultiplication(Matrix other) {
        if (this.cols != other.rows) {
            throw new MatrixDimensionException("Matrix dimensions must be compatible for multiplication.");
        }
    }
}