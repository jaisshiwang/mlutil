package io.github.jaisshiwang.mlutil.matrix.decomposition;

import io.github.jaisshiwang.mlutil.matrix.DenseMatrix;
import io.github.jaisshiwang.mlutil.matrix.Matrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LUDecompositionTest {

    @Test
    void testLUDecomposition() {
        Matrix matrix = new DenseMatrix(3, 3, new double[][]{
            {2, 1, 1},
            {4, -6, 0},
            {-2, 7, 2}
        });

        LUDecomposition lu = new LUDecomposition(matrix);

        Matrix L = lu.getL();
        Matrix U = lu.getU();
        Matrix P = lu.getP();

        // Verify the L matrix
        assertEquals(1.0, L.get(0, 0));
        assertEquals(2.0, L.get(1, 0));
        assertEquals(-1.0, L.get(2, 0));
        assertEquals(1.0, L.get(2, 2));

        // Verify the U matrix
        assertEquals(2.0, U.get(0, 0));
        assertEquals(1.0, U.get(0, 1));
        assertEquals(1.0, U.get(0, 2));

        // Verify the P matrix
        assertEquals(1.0, P.get(0, 0));
        assertEquals(1.0, P.get(2, 2));
    }
}
