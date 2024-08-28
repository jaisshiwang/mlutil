package io.github.jaisshiwang.mlutil.neuralnetwork.utils;

import io.github.jaisshiwang.mlutil.matrix.Matrix;

/**
 * Interface representing an activation function in a neural network.
 */
public interface ActivationFunction {

    /**
     * Applies the activation function to the input matrix.
     *
     * @param input The input matrix.
     * @return The output matrix after applying the activation function.
     */
    Matrix apply(Matrix input);
}

