import java.io.*;
import java.util.Arrays;

public class Huffman{

	ArbolBinario arbolHuffman;
	Pila freqOrdenadas;
	int carTotal;

	private Huffman(ArbolBinario arbol, Pila pila, int carTotal)
	{
		this.arbolHuffman = arbol;
		this.freqOrdenadas = pila;
		this.carTotal = carTotal;
	}

	public static Huffman comprimir(int[] frecuencias)
	{
		ArbolBinario arbolHuffman = algoritmoHuffman(frecuencias);
		Pila freqOrdenadas = heapSort(frecuencias);
		int caracteresTotales = Arrays.stream(frecuencias).sum();

		return new Huffman(arbolHuffman, freqOrdenadas, caracteresTotales);
	}

	public void mostrarCompresion(String output, boolean showSize) throws IOException
	{
		int fileSizeBits = 0;

		PrintWriter writer = new PrintWriter(output, "UTF-8");

		while(!this.freqOrdenadas.estaVacia())
		{
			ArbolBinario caracter = this.freqOrdenadas.desapilar();
			String codigo = encode(this.arbolHuffman, caracter.simbolos);
			fileSizeBits += codigo.length() * caracter.freq;
			String outputSym;
			if (caracter.simbolos == "»")
			{
				outputSym = "\\n";
			}
			else if(caracter.simbolos == "«")
			{
				outputSym = "\\r";
			}
			else
			{
				outputSym = caracter.simbolos;
			}

			writer.println("'" + outputSym + "' " + caracter.car + " " + codigo + " " + String.format("%.4f", caracter.freq*1.0/this.carTotal * 100) + "%");
		}

		writer.close();

		if (showSize)
		{
			System.out.println("El tamaño comprimido en bytes es: " + (int)(Math.ceil(fileSizeBits/8)));
		}
	}

	private static ArbolBinario algoritmoHuffman(int[] frecuencias)
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
	private static Pila heapSort(int[] arreglo)
	{
		ColaPrior cola = fromArrayToCola(arreglo);
		Pila pila = new Pila();
		while(cola.getSize() > 0)
		{
			pila.apilar(cola.extraer());
		}

		return pila;
	}

	/**
	 * [fromArrayToCola: transforma un array a una cola de prioridad de minimos, omite los valores nulos o negativos]
	 * @param  array [description]
	 * @return       [description]
	 */
	private static ColaPrior fromArrayToCola(int[] array)
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

	private static String encode(ArbolBinario huffmanCode, String simbolo)
	{
		if (!huffmanCode.simbolos.contains(simbolo))
		{
			return "No Valido";
		}

		if (huffmanCode.izq != null && huffmanCode.izq.simbolos.contains(simbolo))
		{
			return "0" + encode(huffmanCode.izq, simbolo);
		}

		if (huffmanCode.der != null && huffmanCode.der.simbolos.contains(simbolo))
		{
			return "1" + encode(huffmanCode.der, simbolo);
		}

		return "";
	}
}