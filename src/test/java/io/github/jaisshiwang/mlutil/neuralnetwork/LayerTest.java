package io.github.jaisshiwang.mlutil.neuralnetwork;

import io.github.jaisshiwang.mlutil.matrix.DenseMatrix;
import io.github.jaisshiwang.mlutil.matrix.Matrix;
import io.github.jaisshiwang.mlutil.neuralnetwork.utils.ActivationFunctions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LayerTest {

    @Test
    void testForwardPass() {
        Matrix weights = new DenseMatrix(2, 2, new double[][]{{1.0, 2.0}, {3.0, 4.0}});
        Matrix biases = new DenseMatrix(2, 1, new double[][]{{1.0}, {1.0}});
        Layer layer = new Layer(weights, biases, ActivationFunctions.RELU);

        Matrix input = new DenseMatrix(2, 1, new double[][]{{0.5}, {0.5}});
        Matrix output = layer.forward(input);

        assertEquals(2.0, output.get(0, 0));
        assertEquals(5.0, output.get(1, 0));
    }
}
