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
		CartesianTree nodoInsertado = this.insertarAbb(v, w, this);
		return nodoInsertado.rotar();
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
		}
		else
		{
			padre.der = izq;
			izq = padre;
			padre = padre.padre;
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
