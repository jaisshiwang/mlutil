package io.github.jaisshiwang.mlutil.matrix.decomposition;

import io.github.jaisshiwang.mlutil.matrix.DenseMatrix;
import io.github.jaisshiwang.mlutil.matrix.Matrix;
import io.github.jaisshiwang.mlutil.matrix.MatrixUtils;

/**
 * Performs Cholesky decomposition on a symmetric, positive-definite matrix.
 * Cholesky decomposition factors a matrix into the product of a lower triangular matrix and its transpose.
 */
public class CholeskyDecomposition {

    private final Matrix L;

    /**
     * Constructs a CholeskyDecomposition of the given symmetric, positive-definite matrix.
     * 
     * @param matrix The matrix to decompose.
     * @throws IllegalArgumentException if the matrix is not symmetric.
     */
    public CholeskyDecomposition(Matrix matrix) {
        if (!MatrixUtils.isSymmetric(matrix)) {
            throw new IllegalArgumentException("Cholesky decomposition requires a symmetric matrix.");
        }

        int n = matrix.getRows();
        L = new DenseMatrix(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += L.get(i, k) * L.get(j, k);
                }

                if (i == j) {
                    L.set(i, j, Math.sqrt(matrix.get(i, i) - sum));
                } else {
                    L.set(i, j, (1.0 / L.get(j, j) * (matrix.get(i, j) - sum)));
                }
            }
        }
    }

    /**
     * Returns the lower triangular matrix from the Cholesky decomposition.
     * 
     * @return The lower triangular matrix L.
     */
    public Matrix getL() {
        return L;
    }

    /**
     * Returns the transpose of the lower triangular matrix from the Cholesky decomposition.
     * This is equivalent to the upper triangular matrix in Cholesky decomposition.
     * 
     * @return The transpose of L (upper triangular matrix).
     */
    public Matrix getLT() {
        return L.transpose();
    }
}
       
