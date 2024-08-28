package io.github.jaisshiwang.mlutil.neuralnetwork;

import io.github.jaisshiwang.mlutil.matrix.DenseMatrix;
import io.github.jaisshiwang.mlutil.matrix.Matrix;
import io.github.jaisshiwang.mlutil.neuralnetwork.utils.ActivationFunctions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NeuralNetworkTest {

    private NeuralNetwork neuralNetwork;

    @BeforeEach
    void setUp() {
        // Set up a simple 2-layer neural network for testing
        Layer inputLayer = new Layer(
            new DenseMatrix(2, 2, new double[][]{{0.5, 0.5}, {0.5, 0.5}}),
            new DenseMatrix(2, 1, new double[][]{{0.5}, {0.5}}),
            ActivationFunctions.SIGMOID
        );

        Layer outputLayer = new Layer(
            new DenseMatrix(1, 2, new double[][]{{0.5, 0.5}}),
            new DenseMatrix(1, 1, new double[][]{{0.5}}),
            ActivationFunctions.SIGMOID
        );

        // Use an ArrayList instead of Arrays.asList
        neuralNetwork = new NeuralNetwork(new ArrayList<>(Arrays.asList(inputLayer, outputLayer)));
    }


    @Test
    void testPredict() {
        // Test prediction for input [1.0, 1.0]
        double[] input = {1.0, 1.0};
        List<Double> output = neuralNetwork.predict(input);

        // Validate the output
        assertNotNull(output);
        assertEquals(1, output.size());
        assertTrue(output.get(0) > 0 && output.get(0) < 1, "Output should be between 0 and 1 due to sigmoid activation");
    }

    @Test
    void testAddLayer() {
        // Add an additional layer and verify it works correctly
        Layer newLayer = new Layer(
            new DenseMatrix(1, 1, new double[][]{{0.8}}),
            new DenseMatrix(1, 1, new double[][]{{0.2}}),
            ActivationFunctions.RELU
        );

        neuralNetwork.addLayer(newLayer);

        double[] input = {1.0, 1.0};
        List<Double> output = neuralNetwork.predict(input);

        // Validate the output
        assertNotNull(output);
        assertEquals(1, output.size());
        // No specific assertion here because the actual value depends on weights, just checking for no exceptions
    }
}

