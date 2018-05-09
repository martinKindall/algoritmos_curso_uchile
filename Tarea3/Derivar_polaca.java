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

	    System.out.println(arbolToInFijo(result));
	    System.out.println(arbolToInFijo(derivarArbol(result, "x")));
	    System.out.println(arbolToInFijo(simplificarArbol(derivarArbol(result, "x"))));
	    System.out.println(arbolToInFijo(derivarArbol(result, "y")));
	    System.out.println(arbolToInFijo(simplificarArbol(derivarArbol(result, "y"))));
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

	static ArbolBinario derivarArbol(ArbolBinario arbol, String variable)
	{
		if (arbol == null) return null;

		switch (arbol.val) 
		{
			case "+":
				return new ArbolBinario("+", derivarArbol(arbol.izq, variable), derivarArbol(arbol.der, variable));

			case "-":
				return new ArbolBinario("-", derivarArbol(arbol.izq, variable), derivarArbol(arbol.der, variable));

			case "*":
				return new ArbolBinario(
					"+",
					new ArbolBinario("*", derivarArbol(arbol.izq, variable), arbol.der),
					new ArbolBinario("*", arbol.izq, derivarArbol(arbol.der, variable))
					);

			case "/":
				return new ArbolBinario(
					"/",
					new ArbolBinario(
						"-",
						new ArbolBinario("*", derivarArbol(arbol.izq, variable), arbol.der),
						new ArbolBinario("*", arbol.izq, derivarArbol(arbol.der, variable))
						),
					new ArbolBinario("*", arbol.der, arbol.der)
					);

			default:   // cuando es numero o variable
				if (arbol.val.equals(variable))
					return new ArbolBinario("1");

				return new ArbolBinario("0");
		}
	}

	static ArbolBinario simplificarArbol(ArbolBinario arbol)
	{
		if (arbol == null) return null;

		String operaciones = "+-/*";
		ArbolBinario aIzq = simplificarArbol(arbol.izq);
		ArbolBinario aDer = simplificarArbol(arbol.der);

		if (operaciones.contains(arbol.val))
		{
			String izq = aIzq.val;
			String der = aDer.val;

			String cero = "0";
			String uno = "1";

			switch (arbol.val) 
			{
				case "+":
					if (izq.equals(cero))
					{
						return aDer;
					}
					else if(der.equals(cero))
					{
						return aIzq;
					}
					break;

				case "-":
					if(der.equals(cero))
					{
						return aIzq;
					}
					break;

				case "*":
					if (izq.equals(cero) || der.equals(cero))
					{
						return new ArbolBinario(cero);
					}
					if (izq.equals(uno))
					{
						return aDer;
					}
					else if(der.equals(uno))
					{
						return aIzq;
					}
					break;

				case "/":
					if(izq.equals(cero))
					{
						return new ArbolBinario(cero);
					}
					if(der.equals(uno))
					{
						return aIzq;
					}
					break;

				default:   
					break;
			}
		}
		return new ArbolBinario(arbol.val, aIzq, aDer);
	}

	static String arbolToInFijo(ArbolBinario arbol)
	{
		if (arbol == null) return "";

		String operaciones = "+-/*";

		if (operaciones.contains(arbol.val))
			return "(" + arbolToInFijo(arbol.izq) + arbol.val + arbolToInFijo(arbol.der) + ")";

		return arbol.val;
	}

	static void tests()
	{

	}
}