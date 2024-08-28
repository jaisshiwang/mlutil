package io.github.jaisshiwang.mlutil.matrix.exceptions;

/**
 * Exception thrown when matrix dimensions are incompatible for a given operation.
 */
public class MatrixDimensionException extends RuntimeException {
    public MatrixDimensionException(String message) {
        super(message);
    }
}