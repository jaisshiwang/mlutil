package io.github.jaisshiwang.mlutil.matrix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class MatrixUtilsTest {

    @Test
    void testRandomMatrix() {
        Matrix matrix = MatrixUtils.randomMatrix(3, 3, -5, 5);
        assertEquals(3, matrix.getRows());
        assertEquals(3, matrix.getCols());

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                assertTrue(matrix.get(i, j) >= -5 && matrix.get(i, j) <= 5);
            }
        }
    }

    @Test
    void testIdentityMatrix() {
        Matrix identity = MatrixUtils.identity(3);
        assertEquals(3, identity.getRows());
        assertEquals(3, identity.getCols());

        for (int i = 0; i < identity.getRows(); i++) {
            for (int j = 0; j < identity.getCols(); j++) {
                if (i == j) {
                    assertEquals(1.0, identity.get(i, j));
                } else {
                    assertEquals(0.0, identity.get(i, j));
                }
            }
        }
    }

    @Test
    void testListToMatrix() {
        List<Double> list = Arrays.asList(1.0, 2.0, 3.0, 4.0);
        Matrix matrix = MatrixUtils.listToMatrix(list, 2, 2, DenseMatrix.class);

        assertEquals(2, matrix.getRows());
        assertEquals(2, matrix.getCols());
        assertEquals(1.0, matrix.get(0, 0));
        assertEquals(4.0, matrix.get(1, 1));
    }
}
