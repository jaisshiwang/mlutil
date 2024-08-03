package io.github.shiwangjais.mlutil.NeuralNetwork;
import java.util.List;

public class NeuralNetwork {
	
	Matrix weights_ih;
    Matrix weights_ho;
    Matrix bias_h;
    Matrix bias_o;	
	
	public NeuralNetwork(Matrix weights_ih, Matrix weights_ho, Matrix bias_h, Matrix bias_o) {
		// type is a flag to determine if the weights selected are random or given by evolutionary algorithm.
		this.weights_ih = weights_ih;
		this.weights_ho = weights_ho;
		
		this.bias_h= bias_h;
		this.bias_o= bias_o;
	}

	public List<Double> predict(double[] x)
	{
		Matrix input = Matrix.fromArray(x);
		Matrix hidden = Matrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.reLU();
		
		Matrix output = Matrix.multiply(weights_ho,hidden);
		output.add(bias_o);
		output.reLU();
		
		return output.toArray();
	}
}