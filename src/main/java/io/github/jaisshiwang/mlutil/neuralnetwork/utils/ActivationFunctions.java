package io.github.jaisshiwang.mlutil.neuralnetwork.utils;

import io.github.jaisshiwang.mlutil.matrix.Matrix;
/**
 * Utility class providing common activation functions.
 */
public class ActivationFunctions {

    /**
     * ReLU activation function.
     */
    public static final ActivationFunction RELU = input -> {
        Matrix output = input.copy();
        for (int i = 0; i < output.getRows(); i++) {
            for (int j = 0; j < output.getCols(); j++) {
                output.set(i, j, Math.max(0, output.get(i, j)));
            }
        }
        return output;
    };

    /**
     * Sigmoid activation function.
     */
    public static final ActivationFunction SIGMOID = input -> {
        Matrix output = input.copy();
        for (int i = 0; i < output.getRows(); i++) {
            for (int j = 0; j < output.getCols(); j++) {
                output.set(i, j, 1 / (1 + Math.exp(-output.get(i, j))));
            }
        }
        return output;
    };

    public static final ActivationFunction TANH = input -> {
        Matrix output = input.copy();
        for (int i = 0; i < output.getRows(); i++) {
            for (int j = 0; j < output.getCols(); j++) {
                output.set(i, j, Math.tanh(output.get(i, j)));
            }
        }
        return output;
    };
    
    public static final ActivationFunction LEAKY_RELU = input -> {
        Matrix output = input.copy();
        for (int i = 0; i < output.getRows(); i++) {
            for (int j = 0; j < output.getCols(); j++) {
                output.set(i, j, Math.max(0.01 * output.get(i, j), output.get(i, j)));
            }
        }
        return output;
    };
}
