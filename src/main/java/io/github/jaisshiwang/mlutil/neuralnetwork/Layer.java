package io.github.jaisshiwang.mlutil.neuralnetwork;

import io.github.jaisshiwang.mlutil.matrix.Matrix;
import io.github.jaisshiwang.mlutil.neuralnetwork.utils.ActivationFunction;

/**
 * Represents a layer in a neural network.
 */
public class Layer {

    private final Matrix weights;
    private final Matrix biases;
    private final ActivationFunction activationFunction;

    /**
     * Constructs a Layer with the specified weights, biases, and activation function.
     *
     * @param weights The weight matrix for the layer.
     * @param biases The bias matrix for the layer.
     * @param activationFunction The activation function to apply to the layer's output.
     */
    public Layer(Matrix weights, Matrix biases, ActivationFunction activationFunction) {
        this.weights = weights;
        this.biases = biases;
        this.activationFunction = activationFunction;
    }

    /**
     * Performs a forward pass through this layer.
     *
     * @param input The input to the layer.
     * @return The output of the layer.
     */
    public Matrix forward(Matrix input) {
        Matrix output = weights.multiply(input);
        output.add(biases);
        output = activationFunction.apply(output);
        return output;
    }
}

