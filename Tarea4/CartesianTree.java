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
		nodoInsertado.corregirAlturas();
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

			// para corregir referencias de padres a hijos 
			// en caso que no ocurran mas rotaciones
			if (padre.numX > numX)
			{
				padre.izq = this;
			}
			else
			{
				padre.der = this;
			}

			return padre.rotar();
		}

		if (padre.numX > numX)
		{
			padre.izq = der;

			// se debe corregir tambien el puntero padre de los hijos a rotar
			if (der != null)
			{
				der.padre = padre;
			}

			der = padre;
			padre = padre.padre;
			der.padre = this;
		}
		else
		{
			padre.der = izq;

			if (izq != null)
			{
				izq.padre = padre;
			}

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
		return this.costoTotal() * 1.0 / this.numNodos();
	}

	public void corregirAlturas()
	{
		if (padre == null)
		{
			altura = 1;
		}
		else
		{
			altura = padre.altura + 1;
		}

		if (izq != null)
			izq.corregirAlturas();

		if (der != null)
			der.corregirAlturas();
	}
}
