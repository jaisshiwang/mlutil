package io.github.jaisshiwang.mlutil.matrix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SparseMatrixTest {

    @Test
    void testSetAndGet() {
        SparseMatrix matrix = new SparseMatrix(3, 3);
        matrix.set(0, 1, 5.0);

        assertEquals(5.0, matrix.get(0, 1));
        assertEquals(0.0, matrix.get(1, 1));  // Default value for unset elements
    }

    @Test
    void testAddition() {
        SparseMatrix a = new SparseMatrix(2, 2);
        a.set(0, 0, 1.0);
        a.set(1, 1, 2.0);

        SparseMatrix b = new SparseMatrix(2, 2);
        b.set(0, 1, 3.0);
        b.set(1, 0, 4.0);

        Matrix result = a.add(b);

        assertEquals(1.0, result.get(0, 0));
        assertEquals(3.0, result.get(0, 1));
        assertEquals(4.0, result.get(1, 0));
        assertEquals(2.0, result.get(1, 1));
    }

    @Test
    void testSwapRows() {
        SparseMatrix matrix = new SparseMatrix(3, 3);
        matrix.set(0, 1, 5.0);
        matrix.set(2, 0, 3.0);

        matrix.swapRows(0, 2);

        assertEquals(3.0, matrix.get(0, 0));
        assertEquals(5.0, matrix.get(2, 1));
    }
}

