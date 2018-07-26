import java.io.*;

public class Tarea5{

	public static void main(String[] args) throws IOException
	{
		tests();

	    int[] frecuencias = getFrecuenciasFromFile("ElCidC1.txt", "ISO-8859-1");
	    ArbolBinario huffman = algoritmoHuffman(frecuencias);
	    Pila freqOrdenadas = heapSort(frecuencias);

	    System.out.println("Fin");
	}

	public static int[] getFrecuenciasFromFile(String textFile, String encoding) throws IOException
	{
		BufferedReader reader = 
		    new BufferedReader(
		    	new InputStreamReader(
		    		new FileInputStream(textFile),
		    		encoding)
    	);

        String line = null;
        int[] frecuencias = new int[300];

        while ((line = reader.readLine()) != null) 
        {
            for (int idx=0; idx<line.length(); idx++) 
            {
            	char currChar = line.charAt(idx);
            	frecuencias[(int)currChar]++;
           	}	

        }

        return frecuencias;
	}

	public static ArbolBinario algoritmoHuffman(int[] frecuencias)
	{
		ColaPrior cola = fromArrayToCola(frecuencias);

		while(cola.getSize() > 1)
		{
			ArbolBinario menor1 = cola.extraer();
			ArbolBinario menor2 = cola.extraer();

			ArbolBinario nuevoAr = new ArbolBinario(0, menor1.freq + menor2.freq, menor1, menor2);

			cola.insertar(nuevoAr);
		}

		return cola.extraer();
	}

	/**
	 * [heapSort: ordena un arreglo de mayor a menor, omite los valores nulos o negativos]
	 */
	public static Pila heapSort(int[] arreglo)
	{
		ColaPrior cola = fromArrayToCola(arreglo);
		Pila pila = new Pila();
		while(cola.getSize() > 0)
		{
			pila.apilar(cola.extraer());
		}

		return pila;
	}

	public static ColaPrior fromArrayToCola(int[] array)
	{
		ColaPrior cola = new ColaPrior();

		for (int idx=0; idx<array.length; idx++) 
		{
			if (array[idx] > 0)
			{
				cola.insertar(new ArbolBinario(idx, array[idx]));
			}
		}

		return cola;
	}

	public static void tests()
	{
		ColaPrior colaP = new ColaPrior();
		colaP.insertar(new ArbolBinario(0, 2));
		colaP.insertar(new ArbolBinario(0, 9));
		colaP.insertar(new ArbolBinario(0, 6));
		colaP.insertar(new ArbolBinario(0, 20));
		colaP.insertar(new ArbolBinario(0, 15));
		colaP.insertar(new ArbolBinario(0, 4));
		colaP.insertar(new ArbolBinario(0, 7));
		colaP.insertar(new ArbolBinario(0, 1));

		ArbolBinario menor = colaP.extraer();
		assert(ArbolBinario.esMayor(new ArbolBinario(0, 2), menor));
		assert(!ArbolBinario.esMayor(new ArbolBinario(0, 0), menor));

		ArbolBinario menor6 = colaP.extraer();
		assert(ArbolBinario.esMayor(new ArbolBinario(0, 3), menor6));
		assert(!ArbolBinario.esMayor(new ArbolBinario(0, 1), menor6));

		ArbolBinario menor2 = colaP.extraer();
		assert(ArbolBinario.esMayor(new ArbolBinario(0, 5), menor2));
		assert(!ArbolBinario.esMayor(new ArbolBinario(0, 3), menor2));

		ArbolBinario menor3 = colaP.extraer();
		assert(ArbolBinario.esMayor(new ArbolBinario(0, 7), menor3));
		assert(!ArbolBinario.esMayor(new ArbolBinario(0, 5), menor3));

		ArbolBinario menor4 = colaP.extraer();
		assert(ArbolBinario.esMayor(new ArbolBinario(0, 8), menor4));
		assert(!ArbolBinario.esMayor(new ArbolBinario(0, 6), menor4));

		ArbolBinario menor5 = colaP.extraer();
		assert(ArbolBinario.esMayor(new ArbolBinario(0, 10), menor5));
		assert(!ArbolBinario.esMayor(new ArbolBinario(0, 8), menor5));
	}
}