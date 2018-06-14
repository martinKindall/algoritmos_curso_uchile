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

	public void insertar(int v, double w)
	{
		this.insertarAbb(v, w);
	}

	public void insertarAbb(int v, double w)
	{
		if (numX == v)
		{
			return;
		}

		if (numX > v)
		{
			if (izq == null)
			{
				izq = new CartesianTree(v, w);
			}
			else
			{
				izq.insertarAbb(v, w);
			}
		}
		else
		{
			if (der == null)
			{
				der = new CartesianTree(v, w);
			}
			else
			{
				der.insertarAbb(v, w);
			}
		}
	}
}
