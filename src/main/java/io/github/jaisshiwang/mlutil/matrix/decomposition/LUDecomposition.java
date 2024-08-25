package io.github.jaisshiwang.mlutil.matrix.decomposition;

import io.github.jaisshiwang.mlutil.matrix.DenseMatrix;
import io.github.jaisshiwang.mlutil.matrix.Matrix;
import io.github.jaisshiwang.mlutil.matrix.MatrixUtils;
import io.github.jaisshiwang.mlutil.matrix.exceptions.SingularMatrixException;

/**
 * Performs LU decomposition on a square matrix.
 * LU decomposition factors a matrix as the product of a lower triangular matrix and an upper triangular matrix.
 */
public class LUDecomposition {

    private final Matrix L;
    private final Matrix U;
    private final Matrix P;

    /**
     * Constructs an LUDecomposition of the given square matrix.
     * 
     * @param matrix The square matrix to decompose.
     * @throws IllegalArgumentException if the matrix is not square.
     * @throws SingularMatrixException if the matrix is singular.
     */
    public LUDecomposition(Matrix matrix) {
        if (!MatrixUtils.isSquare(matrix)) {
            throw new IllegalArgumentException("LU decomposition requires a square matrix.");
        }

        int n = matrix.getRows();
        L = new DenseMatrix(n, n);
        U = matrix.copy();
        P = MatrixUtils.identity(n);

        for (int i = 0; i < n; i++) {
            double pivotValue = U.get(i, i);
            if (pivotValue == 0) {
                throw new SingularMatrixException("Matrix is singular and cannot be decomposed.");
            }
            for (int j = i + 1; j < n; j++) {
                double multiplier = U.get(j, i) / pivotValue;
                L.set(j, i, multiplier);
                for (int k = i; k < n; k++) {
                    U.set(j, k, U.get(j, k) - multiplier * U.get(i, k));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            L.set(i, i, 1.0);
        }
    }

    /**
     * Returns the lower triangular matrix from the LU decomposition.
     * 
     * @return The lower triangular matrix L.
     */
    public Matrix getL() {
        return L;
    }

    /**
     * Returns the upper triangular matrix from the LU decomposition.
     * 
     * @return The upper triangular matrix U.
     */
    public Matrix getU() {
        return U;
    }

    /**
     * Returns the permutation matrix from the LU decomposition.
     * 
     * @return The permutation matrix P.
     */
    public Matrix getP() {
        return P;
    }
}

