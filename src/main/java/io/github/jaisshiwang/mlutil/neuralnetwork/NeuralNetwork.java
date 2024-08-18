package io.github.jaisshiwang.mlutil.neuralnetwork;
//------------------------------------------//
//find a way to import with relative path//
import io.github.jaisshiwang.mlutil.matrix.Matrix;
import java.util.List;

public class NeuralNetwork {
	
	Matrix weghtsIh;
    Matrix weghtsHo;
    Matrix biasH;
    Matrix biasO;	
	
	public NeuralNetwork(Matrix weghtsIh, Matrix weghtsHo, Matrix biasH, Matrix biasO) {
		// type is a flag to determine if the weights selected are random or given by evolutionary algorithm.
		this.weghtsIh = weghtsIh;
		this.weghtsHo = weghtsHo;
		
		this.biasH= biasH;
		this.biasO= biasO;
	}

	public List<Double> predict(double[] x)
	{
		Matrix input = Matrix.fromArray(x);
		Matrix hidden = Matrix.multiply(weghtsIh, input);
		hidden.add(biasH);
		hidden.reLU();
		
		Matrix output = Matrix.multiply(weghtsHo,hidden);
		output.add(biasO);
		output.reLU();
		
		return output.toArray();
	}
}