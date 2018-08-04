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
				System.out.println("Tabla llena");
				return;
			}
		}

		tabla[hash] = num;
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