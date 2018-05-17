import java.util.Scanner;

public class Derivar_polaca{

	static String operaciones = "+-/*";

	public static void main(String[] args) 
	{
		tests();
		
		System.out.print("Ingrese expresion en notacion polaca inversa: ");
		Scanner scanner = new Scanner(System.in);
	    String expresion = scanner.nextLine();

		System.out.print("Ingrese variable con la cual derivar: ");
	    String variable = scanner.nextLine();

	    ArbolBinario result = polacaToArbol(expresion);
	    ArbolBinario simple = simplificarArbol(derivarArbol(result, variable));

	    System.out.println(arbolToInFijo(simple));
	    // System.out.println(arbolToInFijoFullParentesis(simple));
	}

	static ArbolBinario polacaToArbol(String exp)
	{
		String elems[] = exp.split(" ");
		Pila pila = new Pila();
		int exp_size = elems.length;

		for (int i=0; i<exp_size; i++) 
		{
			String curr_char = elems[i];

			if (operaciones.contains(curr_char))
			{
				ArbolBinario der = pila.desapilar();
				ArbolBinario izq = pila.desapilar();

				pila.apilar(new ArbolBinario(curr_char, izq, der));
			}
			else
			{
				pila.apilar(new ArbolBinario(curr_char));
			}
		}

		return pila.desapilar();
	}

	static boolean esConstante(String val, String variable)
	{
		return !operaciones.contains(val) && !(variable.equals(val) || variable.equals("-"+val));
	}

	static ArbolBinario derivarArbol(ArbolBinario arbol, String variable)
	{
		if (arbol == null) return null;

		ArbolBinario aIzq = arbol.izq;
		ArbolBinario aDer = arbol.der;

		if (operaciones.contains(arbol.val))
		{
			switch (arbol.val) 
			{
				case "+":
					return new ArbolBinario("+", derivarArbol(aIzq, variable), derivarArbol(aDer, variable));

				case "-":
					return new ArbolBinario("-", derivarArbol(aIzq, variable), derivarArbol(aDer, variable));

				case "*":
					if (esConstante(aIzq.val, variable))
					{
						return new ArbolBinario("*", aIzq, derivarArbol(aDer, variable));
					}
					else if (esConstante(aDer.val, variable))
					{
						return new ArbolBinario("*", derivarArbol(aIzq, variable), aDer);
					}

					return new ArbolBinario(
						"+",
						new ArbolBinario("*", derivarArbol(aIzq, variable), aDer),
						new ArbolBinario("*", aIzq, derivarArbol(aDer, variable))
						);

				case "/":
					if (esConstante(aDer.val, variable))
					{
						return new ArbolBinario("/", derivarArbol(aIzq, variable), aDer);
					}

					return new ArbolBinario(
						"/",
						new ArbolBinario(
							"-",
							new ArbolBinario("*", derivarArbol(aIzq, variable), aDer),
							new ArbolBinario("*", aIzq, derivarArbol(aDer, variable))
							),
						new ArbolBinario("*", aDer, aDer)
						);

				default:   
					break;
			}
		}

		if (arbol.val.equals(variable))
			return new ArbolBinario("1");

		if(arbol.val.equals("-" + variable))
			return new ArbolBinario("-1");

		return new ArbolBinario("0");
	}

	static ArbolBinario simplificarArbol(ArbolBinario arbol)
	{
		if (arbol == null) return null;

		ArbolBinario aIzq = simplificarArbol(arbol.izq);
		ArbolBinario aDer = simplificarArbol(arbol.der);

		if (operaciones.contains(arbol.val))
		{
			String izq = aIzq.val;
			String der = aDer.val;

			String cero = "0";
			String uno = "1";
			String menos = "-";

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
					else if(!der.equals(menos) && der.contains(menos))
					{
						aDer.val = der.replace(menos, "");
						return new ArbolBinario("-", aIzq, aDer);
					}
					break;

				case "-":
					if(der.equals(cero))
					{
						return aIzq;
					}
					else if(izq.equals(cero))
					{
						if(!operaciones.contains(der))
						{
							if (der.contains(menos))
							{
								aDer.val = der.replace(menos, "");
							}
							else
							{
								aDer.val = menos + der;
							}
							return aDer;
						}
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

		String valor = arbol.val;

		ArbolBinario aIzq = arbol.izq;
		ArbolBinario aDer = arbol.der;

		String operacionesMenor = "+-";

		String par1Izq = "";
		String par1Der = "";
		String par2Izq = "";
		String par2Der = "";

		if (operaciones.contains(valor))
		{
			if (operacionesMenor.contains(valor))
			{
				if ("-".contains(valor) && operacionesMenor.contains(aDer.val))
				{
					par2Izq = "(";
					par2Der = ")";
				}

				return arbolToInFijo(aIzq) + " " + valor + " " + par2Izq + arbolToInFijo(aDer) + par2Der;
			}

			if (operacionesMenor.contains(aIzq.val) || ("/".contains(valor) && operaciones.contains(aIzq.val)))
			{
				par1Izq = "(";
				par1Der = ")";
			}

			if (operacionesMenor.contains(aDer.val) || ("/".contains(valor) && operaciones.contains(aDer.val)))
			{
				par2Izq = "(";
				par2Der = ")";
			}

			return par1Izq + arbolToInFijo(aIzq) + par1Der + " " + valor + " " + par2Izq + arbolToInFijo(aDer) + par2Der;
		}

		return valor;
	}

	static String arbolToInFijoFullParentesis(ArbolBinario arbol)
	{
		if (arbol == null) return "";

		if (operaciones.contains(arbol.val))
		{
			return "(" + arbolToInFijoFullParentesis(arbol.izq) + ")" + " " + arbol.val + " " + "(" + arbolToInFijoFullParentesis(arbol.der) + ")";
		}

		return arbol.val;
	}

	static void tests()
	{
		ArbolBinario result = polacaToArbol("x y * 3 x * + x *");
		assert arbolToInFijo(simplificarArbol(derivarArbol(result, "x"))).equals("(y + 3) * x + x * y + 3 * x");
		assert arbolToInFijo(simplificarArbol(derivarArbol(result, "y"))).equals("x * x");
		
		result = polacaToArbol("2 x 3 / * y x - +");
		assert arbolToInFijo(simplificarArbol(derivarArbol(result, "x"))).equals("2 * 1 / 3 - 1");
		assert arbolToInFijo(simplificarArbol(derivarArbol(result, "y"))).equals("1");

		result = polacaToArbol("1 x / x /");
		assert arbolToInFijo(simplificarArbol(derivarArbol(result, "x"))).equals("(-1 / (x * x) * x - 1 / x) / (x * x)");
		assert arbolToInFijo(simplificarArbol(derivarArbol(result, "y"))).equals("0");
	}
}