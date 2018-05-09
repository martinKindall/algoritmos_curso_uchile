import java.util.Scanner;
import java.util.Arrays;

public class Derivar_polaca{

	public static void main(String[] args) 
	{
		tests();
		
		System.out.print("Ingrese expresion en notacion polaca inversa: ");
		Scanner scanner = new Scanner(System.in);

	    String expresion = scanner.nextLine();

	    ArbolBinario result = polacaToArbol(expresion);

	    System.out.println(result);
	}

	static ArbolBinario polacaToArbol(String exp)
	{
		String elems[] = exp.split(" ");
		Pila pila = new Pila();
		int exp_size = elems.length;
		String operaciones = "+-/*";

		for (int i=0; i<exp_size; i++) 
		{
			String curr_char = elems[i];

			if (operaciones.contains(curr_char))
			{
				ArbolBinario der = pila.desapilar();
				ArbolBinario izq = pila.desapilar();

				pila.apilar(new ArbolBinario(curr_char, izq, der));
			}else
			{
				pila.apilar(new ArbolBinario(curr_char));
			}
		}

		return pila.desapilar();
	}

	static void tests()
	{

	}
}