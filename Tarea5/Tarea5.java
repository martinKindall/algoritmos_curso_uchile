import java.io.*;

public class Tarea5{

	public static void main(String[] args) throws IOException
	{
		tests();

	    int[] frecuencias = getFrecuenciasFromFile(args[0], "UTF-8");
	    Huffman textoComprimido = Huffman.comprimir(frecuencias);
	    textoComprimido.mostrarCompresion(args[1], true);

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

        int symbol;
        int[] frecuencias = new int[300];

        while ((symbol = reader.read()) != -1) 
        {
			frecuencias[symbol]++;
        }
        
        reader.close();
        return frecuencias;
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