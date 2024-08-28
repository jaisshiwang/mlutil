package io.github.jaisshiwang.mlutil.matrix;

import io.github.jaisshiwang.mlutil.matrix.exceptions.MatrixDimensionException;
import io.github.jaisshiwang.mlutil.neuralnetwork.utils.ActivationFunction;

/**
 * Utility class for common matrix operations such as addition, subtraction, multiplication, and more.
 */
public class MatrixOperations {

    /**
     * Adds two matrices.
     * 
     * @param a The first matrix.
     * @param b The second matrix.
     * @return A new matrix representing the sum of the two matrices.
     * @throws MatrixDimensionException if the dimensions do not match.
     */
    public static Matrix add(Matrix a, Matrix b) {
        return a.add(b);
    }

    /**
     * Subtracts one matrix from another.
     * 
     * @param a The first matrix.
     * @param b The second matrix.
     * @return A new matrix representing the difference of the two matrices.
     * @throws MatrixDimensionException if the dimensions do not match.
     */
    public static Matrix subtract(Matrix a, Matrix b) {
        return a.subtract(b);
    }

    /**
     * Multiplies two matrices.
     * 
     * @param a The first matrix.
     * @param b The second matrix.
     * @return A new matrix representing the product of the two matrices.
     * @throws MatrixDimensionException if the dimensions are incompatible.
     */
    public static Matrix multiply(Matrix a, Matrix b) {
        return a.multiply(b);
    }

    /**
     * Multiplies a matrix by a scalar.
     * 
     * @param a The matrix.
     * @param scalar The scalar value to multiply by.
     * @return A new matrix with each element multiplied by the scalar.
     */
    public static Matrix scalarMultiply(Matrix a, double scalar) {
        return a.multiply(scalar);
    }

    /**
     * Transposes a matrix.
     * 
     * @param a The matrix to transpose.
     * @return A new matrix representing the transpose.
     */
    public static Matrix transpose(Matrix a) {
        return a.transpose();
    }

    /**
     * Applies the sigmoid function to each element of a matrix.
     * 
     * @param a The matrix.
     * @return A new matrix with the sigmoid function applied to each element.
     */
    public static Matrix sigmoid(Matrix a) {
        Matrix result = a.copy();
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result.set(i, j, 1 / (1 + Math.exp(-a.get(i, j))));
            }
        }
        return result;
    }

    /**
     * Applies the derivative of the sigmoid function to each element of a matrix.
     * 
     * @param a The matrix.
     * @return A new matrix with the sigmoid derivative applied to each element.
     */
    public static Matrix dsigmoid(Matrix a) {
        Matrix result = a.copy();
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                double sigmoidValue = result.get(i, j);
                result.set(i, j, sigmoidValue * (1 - sigmoidValue));
            }
        }
        return result;
    }

    /**
     * Applies the ReLU (Rectified Linear Unit) function to each element of a matrix.
     * 
     * @param a The matrix.
     * @return A new matrix with the ReLU function applied to each element.
     */
    public static Matrix reLU(Matrix a) {
        Matrix result = a.copy();
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result.set(i, j, Math.max(0, a.get(i, j)));
            }
        }
        return result;
    }

    public static Matrix applyActivationFunction(Matrix a, ActivationFunction activationFunction) {
        return activationFunction.apply(a);
    }

}
