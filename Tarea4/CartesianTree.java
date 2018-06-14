public class CartesianTree 
{
	int numX;
	double numY;

	CartesianTree izq, der, padre;
	
	public CartesianTree(int v, double w) 
	{
		numX = v;
		numY = w;
		izq = null;
		der = null;
		padre = null;
	}

	public CartesianTree(int v, double w, CartesianTree padre) 
	{
		numX = v;
		numY = w;
		izq = null;
		der = null;
		padre = padre;
	}
	
	public void insertar(int v, double w)
	{
		CartesianTree nodoInsertado = this.insertarAbb(v, w, this);
		this.rotar(nodoInsertado);
	}

	public CartesianTree insertarAbb(int v, double w, CartesianTree padre)
	{
		if (numX == v)
		{
			return null;
		}

		if (numX > v)
		{
			if (izq == null)
			{
				izq = new CartesianTree(v, w, padre);
				return izq;
			}

			return izq.insertarAbb(v, w, this);
		}
		else
		{
			if (der == null)
			{
				der = new CartesianTree(v, w, padre);
				return der;
			}
			return der.insertarAbb(v, w, this);
		}
	}

	public void rotar(CartesianTree nodo)
	{

	}
}
