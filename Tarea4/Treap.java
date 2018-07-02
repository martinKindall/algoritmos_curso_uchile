public class Treap
{
	CartesianTree arbol;
	
	public Treap(int v) 
	{
		arbol = new CartesianTree(v, Math.random());
	}

	public void insertar(int v)
	{
		arbol = arbol.insertar(v, Math.random());
	}

	public String imprimir()
	{
		return arbol.imprimir();
	}

	public double costoPromedio()
	{
		return arbol.costoPromedio();
	}

	public static double costoPromedioHastaN(int n)
	{
		int numerosDesordenados[] = arregloDesordenado(n);

		Treap treap = new Treap(numerosDesordenados[0]);

		for (int aux=1; aux < n; aux++) 
		{
			treap.insertar(numerosDesordenados[aux]);
		}

		return treap.costoPromedio();
	}

	public static int[] arregloDesordenado(int n)
	{
		int numerosDesordenados[] = new int[n];
		for (int idx=0; idx<n; idx++) 
		{
			numerosDesordenados[idx] = idx+1;
		}

		for (int idx=n-1; idx >= 0; idx--)
		{
			int aleatorio = randInt(0, idx);

			// swap
			int aux = numerosDesordenados[idx];
			numerosDesordenados[idx] = numerosDesordenados[aleatorio];
			numerosDesordenados[aleatorio] = aux;
		}

		return numerosDesordenados;
	}

	public static int randInt(int x, int y)
	{
		return x + (int) (Math.random() * (y-x+1));
	}
}