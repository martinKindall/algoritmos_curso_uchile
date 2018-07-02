public class CartesianTree 
{
	int numX;
	double numY;
	int altura;

	CartesianTree izq, der, padre;
	
	public CartesianTree(int v, double w) 
	{
		numX = v;
		numY = w;
		izq = null;
		der = null;
		this.padre = null;
		altura = 1;
	}

	public CartesianTree(int v, double w, CartesianTree padre) 
	{
		numX = v;
		numY = w;
		izq = null;
		der = null;
		this.padre = padre;
		if (padre != null)
		{
			altura = padre.altura + 1;
		}
		else
		{
			altura = 1;
		}
	}
	
	public CartesianTree insertar(int v, double w)
	{
		CartesianTree nodoInsertado = this.insertarAbb(v, w);
		nodoInsertado = nodoInsertado.rotar();
		return nodoInsertado;
	}

	public CartesianTree insertarAbb(int v, double w)
	{
		if (numX == v)
		{
			return this;
		}

		if (numX > v)
		{
			if (izq == null)
			{
				izq = new CartesianTree(v, w, this);
				return izq;
			}

			return izq.insertarAbb(v, w);
		}
		else
		{
			if (der == null)
			{
				der = new CartesianTree(v, w, this);
				return der;
			}
			return der.insertarAbb(v, w);
		}
	}

	public CartesianTree rotar()
	{
		if (padre == null || padre.numY <= numY)
		{
			if (padre == null) return this;
			return padre.rotar();
		}

		if (padre.numX > numX)
		{
			padre.izq = der;
			der = padre;
			padre = padre.padre;
			der.padre = this;
		}
		else
		{
			padre.der = izq;
			izq = padre;
			padre = padre.padre;
			izq.padre = this;
		}

		return this.rotar();
	}

	public String imprimir()
	{
		String izqMsg, derMsgM;

		if (izq == null)
		{
			izqMsg = "[]";
		}
		else
		{
			izqMsg = izq.imprimir();
		}

		if (der == null)
		{
			derMsgM = "[]";
		}
		else
		{
			derMsgM = der.imprimir();
		}

		return izqMsg + derMsgM + "(" + numX + "," + numY +")";		
	}

	public double costoTotal()
	{
		double costoIzq, costoDer;

		if (izq == null)
		{
			costoIzq = 0;
		} 
		else
		{
			costoIzq = izq.costoTotal();
		}
		if (der == null)
		{
			costoDer = 0;
		} 
		else
		{
			costoDer = der.costoTotal();
		}

		return altura + costoIzq + costoDer;
	}

	public int numNodos()
	{
		int cantidadIzq = 0, cantidadDer = 0;
		if (izq != null)
			cantidadIzq = izq.numNodos();
		if (der != null)
			cantidadDer = der.numNodos();

		return 1 + cantidadIzq + cantidadDer;
	}

	public double costoPromedio()
	{
		return this.costoTotal() / this.numNodos();
	}
}
