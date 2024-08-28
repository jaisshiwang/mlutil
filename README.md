# ML Util

ML Util is a Java package for machine learning utilities, including matrix operations and neural network implementations.

## Package Structure

io.github.jaisshiwang.mlutil.matrix
├── Matrix.java
├── DenseMatrix.java
├── SparseMatrix.java
├── MatrixOperations.java
├── MatrixDecomposition.java
├── MatrixUtils.java
├── exceptions
│   ├── MatrixDimensionException.java
│   └── SingularMatrixException.java
└── decomposition
    ├── LUDecomposition.java
    ├── QRDecomposition.java
    └── CholeskyDecomposition.java
    
io.github.jaisshiwang.mlutil.neuralnetwork
├── NeuralNetwork.java
├── Layer.java
└── utils
    ├── ActivationFunction.java
    ├── ActivationFunctions.java
    └── NeuralNetworkUtils.java
    

## Usage

### Matrix

```java
import io.github.shiwangjais.mlutil.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        // Your code here
    }
}


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.