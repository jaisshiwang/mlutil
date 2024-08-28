package io.github.jaisshiwang.mlutil.neuralnetwork;

import io.github.jaisshiwang.mlutil.matrix.DenseMatrix;
import io.github.jaisshiwang.mlutil.matrix.Matrix;
import io.github.jaisshiwang.mlutil.neuralnetwork.utils.NeuralNetworkUtils;

import java.util.List;

/**
 * Represents a neural network with multiple layers.
 */
public class NeuralNetwork {

    private final List<Layer> layers;

    /**
     * Constructs a NeuralNetwork with the specified layers.
     *
     * @param layers The layers of the neural network.
     */
    public NeuralNetwork(List<Layer> layers) {
        this.layers = layers;
    }

    /**
     * Performs a forward pass through the network to make a prediction.
     *
     * @param input The input data as an array.
     * @return The output of the network as a list of doubles.
     */
    public List<Double> predict(double[] input) {
        // Use DenseMatrix.fromArray instead of MatrixUtils.fromArray
        Matrix currentOutput = DenseMatrix.fromArray(input);

        for (Layer layer : layers) {
            currentOutput = layer.forward(currentOutput);
        }

        return NeuralNetworkUtils.matrixToList(currentOutput);
    }

    /**
     * Adds a layer to the neural network.
     *
     * @param layer The layer to add.
     */
    public void addLayer(Layer layer) {
        layers.add(layer);
    }
}

