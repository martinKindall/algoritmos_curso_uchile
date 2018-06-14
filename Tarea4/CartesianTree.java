public class CartesianTree 
{
	int numX;
	double numY;

	CartesianTree izq, der;
	
	public CartesianTree(int v, double w) 
	{
		numX = v;
		numY = w;
		izq = null;
		der = null;
	}
	
	public CartesianTree(int v, double w, CartesianTree i, CartesianTree d) 
	{
		numX = v;
		numY = w;
		izq = i;
		der = d;
	}
}
