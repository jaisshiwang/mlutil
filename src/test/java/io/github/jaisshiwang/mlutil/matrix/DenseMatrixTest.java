package io.github.jaisshiwang.mlutil.matrix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DenseMatrixTest {

    @Test
    void testFromArray() {
        double[] array = {1.0, 2.0, 3.0};
        DenseMatrix matrix = DenseMatrix.fromArray(array);
        assertEquals(3, matrix.getRows());
        assertEquals(1, matrix.getCols());
        assertEquals(1.0, matrix.get(0, 0));
        assertEquals(2.0, matrix.get(1, 0));
        assertEquals(3.0, matrix.get(2, 0));
    }

    @Test
    void testAdd() {
        DenseMatrix matrix1 = new DenseMatrix(2, 2, new double[][]{{1, 2}, {3, 4}});
        DenseMatrix matrix2 = new DenseMatrix(2, 2, new double[][]{{5, 6}, {7, 8}});
        Matrix result = matrix1.add(matrix2);
        assertArrayEquals(new double[]{6, 8}, result.getRow(0));
        assertArrayEquals(new double[]{10, 12}, result.getRow(1));
    }

    @Test
    void testMultiply() {
        DenseMatrix matrix1 = new DenseMatrix(2, 3, new double[][]{{1, 2, 3}, {4, 5, 6}});
        DenseMatrix matrix2 = new DenseMatrix(3, 2, new double[][]{{7, 8}, {9, 10}, {11, 12}});
        Matrix result = matrix1.multiply(matrix2);
        assertArrayEquals(new double[]{58, 64}, result.getRow(0));
        assertArrayEquals(new double[]{139, 154}, result.getRow(1));
    }
}

