package io.github.jaisshiwang.mlutil.matrix.exceptions;

/**
 * Exception thrown when a matrix is singular and cannot be decomposed or inverted.
 */
public class SingularMatrixException extends RuntimeException {
    public SingularMatrixException(String message) {
        super(message);
    }
}
