public class HashLineal{
	int[] tabla;

	HashLineal()
	{
		tabla = new int[10];
	}

	void insertar(int num)
	{
		int hash = h(num) % tabla.length;
		int inicio = hash;

		while(tabla[hash] != 0 && tabla[hash] != -1)
		{
			hash = (hash + 1) % tabla.length;

			if (hash == inicio)			
			{
				this.expandir();
			}
		}

		tabla[hash] = num;
	}


	void expandir()
	{
		int[] antiguo = tabla;
		tabla = new int[tabla.length*2];

		for (int idx=0; idx<antiguo.length; idx++) 
		{
			this.insertar(antiguo[idx]);
		}
	}


	void eliminarLazy(int num)
	{
		int hash = this.indexOf(num);

		if (hash < 0)
		{
			return;
		}
		tabla[hash] = -1;
	}


	boolean contiene(int num)
	{
		return this.indexOf(num) > -1; 
	}


	int indexOf(int num)
	{
		int hash = h(num) % tabla.length;
		int inicio = hash;

		while(tabla[hash] != 0)
		{
			if (tabla[hash] == num)
			{
				return hash;
			}

			hash = (hash + 1) % tabla.length;

			if (hash == inicio)
			{
				return -1;
			}
		}
		return -1;
	}


	static int h(int num)
	{
		return (int)Math.pow(num, 3);
	}


	static void tests()
	{
		HashLineal tabla = new HashLineal();

		for (int idx=1; idx<14; idx++) 
		{
			tabla.insertar(idx);
			System.out.println(tabla);
		}

		assert(tabla.contiene(1));
		assert(tabla.contiene(10));
		assert(tabla.contiene(9));
		assert(!tabla.contiene(14));

		tabla.eliminarLazy(9);
		System.out.println(tabla);
		assert(!tabla.contiene(9));
		
		tabla.insertar(14);
		System.out.println(tabla);
		assert(tabla.contiene(14));

		tabla.eliminarLazy(12);
		System.out.println(tabla);
		assert(!tabla.contiene(12));

		tabla.eliminarLazy(7);
		System.out.println(tabla);
		assert(!tabla.contiene(7));

		for (int idx=20; idx<31; idx++) 
		{
			tabla.insertar(idx);
			System.out.println(tabla);
		}
	}


	@Override
	public String toString() 
	{
		String arreglo = "";

		for (int i = 0; i < tabla.length; i++) {
		  arreglo += tabla[i] + " ";
		}

		return arreglo;
	}
}