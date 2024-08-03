package io.github.shiwangjais.mlutil.matrix;

import java.util.ArrayList;
import java.util.List;

class Matrix {
	double [][]data;
	int rows;
    int cols;
	
	public Matrix(int rows,int cols) {
		data= new double[rows][cols];
		this.rows=rows;
		this.cols=cols;

        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                data[i][j]=Math.random()*2-1;
            }
        }
	}

	public Matrix(int rows, int cols, double[][] data){
		this.rows = rows;
		this.cols = cols;
		this.data = data;
	}
	
	
	public void add(int scaler)
	{
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				this.data[i][j]+=scaler;
			}
			
		}
	}
	
	public void add(Matrix m)
	{
		if(cols!=m.cols || rows!=m.rows) {
			return;
		}
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				this.data[i][j]+=m.data[i][j];
			}
		}
	}
	
	public static Matrix fromArray(double[]x)
	{
		Matrix temp = new Matrix(x.length,1);
		for(int i =0;i<x.length;i++){
			temp.data[i][0]=x[i];
		}
		return temp;
		
	}
	
	public List<Double> toArray() {
		List<Double> temp = new ArrayList<Double>();
		for(int i=0; i<rows; i++)
		{
			for(int j=0;j<cols;j++)
			{
				temp.add(data[i][j]);
			}
		}
		return temp;
	}

    // From array to matrix of some column length
    static double[][] transform(double[] arr, int N) {
        int M = (arr.length + N - 1) / N;
        double[][] mat = new double[M][];
        int start = 0;
        for (int r = 0; r < M; r++) {
            int L = Math.min(N, arr.length - start);
            mat[r] = java.util.Arrays.copyOfRange(arr, start, start + L);
            start += L;
        }
        return mat;
    }

	public double[][] listToMatrix(List<Double> arr, int rows, int cols){
		double[][] result = new double[rows][cols];
        int i = 0;
        int j = 0;
            for (Double value : arr) {
                result[i][j] = value;
                j++;
                if(j > cols - 1){
                    i++;
                    j = 0;
                }
            }
        return result;
	}
    

	public static Matrix subtract(Matrix a, Matrix b) {
		Matrix temp=new Matrix(a.rows,a.cols);
		for(int i=0;i<a.rows;i++)
		{
			for(int j=0;j<a.cols;j++)
			{
				temp.data[i][j]=a.data[i][j]-b.data[i][j];
			}
		}
		return temp;
	}

	public static Matrix transpose(Matrix a) {
		Matrix temp=new Matrix(a.cols,a.rows);
		for(int i=0;i<a.rows;i++)
		{
			for(int j=0;j<a.cols;j++)
			{
				temp.data[j][i]=a.data[i][j];
			}
		}
		return temp;
	}

	public static Matrix multiply(Matrix a, Matrix b) {
		Matrix temp=new Matrix(a.rows,b.cols);
		for(int i=0;i<temp.rows;i++)
		{
			for(int j=0;j<temp.cols;j++)
			{
				double sum=0;
				for(int k=0;k<a.cols;k++)
				{
					sum+=a.data[i][k]*b.data[k][j];
				}
				temp.data[i][j]=sum;
			}
		}
		return temp;
	}
	
	public void multiply(Matrix a) {
		for(int i=0;i<a.rows;i++)
		{
			for(int j=0;j<a.cols;j++)
			{
				this.data[i][j]*=a.data[i][j];
			}
		}
	}
	
	public void multiply(double a) {
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				this.data[i][j]*=a;
			}
		}
		
	}
	
	public void sigmoid() {
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
				this.data[i][j] = 1/(1+Math.exp(-this.data[i][j])); 
		}
		
	}
	
	public Matrix dsigmoid() {
		Matrix temp=new Matrix(rows,cols);
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
				temp.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
		}
		return temp;	
	}

	public void reLU(){
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
				if(this.data[i][j] <=0)
					this.data[i][j] = 0; 
		}
	}

	public void reduceRows(){
		Matrix temp = new Matrix((this.rows-1), this.cols);
		for (int i = 0; i<(this.rows-1); i++){
			for (int j = 0; j < this.cols; j++){
				temp.data[i][j] = this.data[i][j];
			}
		}
		this.data = temp.data;
		this.rows = this.rows-1;
	}

	public Matrix copyMatrix(Matrix m){
		Matrix temp = new Matrix(m.rows, m.cols);
		for (int i=0; i<m.rows; i++){
			for (int j=0; j<m.cols; j++)
				temp.data[i][j] = m.data[i][j];
		}
		return temp;	
	}
}	
