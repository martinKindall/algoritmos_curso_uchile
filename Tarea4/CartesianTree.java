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
		this.padre = null;
	}

	public CartesianTree(int v, double w, CartesianTree padre) 
	{
		numX = v;
		numY = w;
		izq = null;
		der = null;
		this.padre = padre;
	}
	
	public CartesianTree insertar(int v, double w)
	{
		CartesianTree nodoInsertado = this.insertarAbb(v, w);
		return nodoInsertado.rotar();
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
}
