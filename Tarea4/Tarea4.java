import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Tarea4{

	public static void main(String[] args) throws FileNotFoundException
	{
		tests();

		parte1();
		parte2();
	}

	public static void prueba1()
	{
		System.out.print("Ingrese par ordenado: ");
		Scanner scanner = new Scanner(System.in);
	    String expresion = scanner.nextLine();

	    String[] parOrd = expresion.split(" ");
		int numX = Integer.parseInt(parOrd[0]);
		double numY = Double.parseDouble(parOrd[1]);

		CartesianTree tree = new CartesianTree(numX, numY);

		while (true) 
		{
		    expresion = scanner.nextLine(); 
		    if (expresion.compareTo("end") == 0) break;

		    parOrd = expresion.split(" ");
			numX = Integer.parseInt(parOrd[0]);
			numY = Double.parseDouble(parOrd[1]);

			tree = tree.insertar(numX, numY);
		}

		System.out.println(tree.imprimir());
	}

	public static void parte1() throws FileNotFoundException
	{
		File file = new File("ejemplos.txt");
		Scanner scanner = new Scanner(file);
	    String expresion = scanner.nextLine();

	    String parOrd[] = expresion.split(" ");
		int numX = Integer.parseInt(parOrd[0]);
		double numY = Double.parseDouble(parOrd[1]);

		CartesianTree tree = new CartesianTree(numX, numY);

		int nEspacios = 0;
		int nTest = 1;

		System.out.println("---------- Test " + nTest++ + " ----------");
		
	    while(true)
	    {	
	    	if (nEspacios == 2 || !scanner.hasNextLine())
	    	{
	    		parOrd = expresion.split(" ");
		    	numX = Integer.parseInt(parOrd[0]);
				numY = Double.parseDouble(parOrd[1]);

				if (!scanner.hasNextLine())
				{
					tree = tree.insertar(numX, numY);
				}

	    		System.out.println(tree.imprimir());
	    		System.out.println(tree.costoPromedio());

	    		if (!scanner.hasNextLine())
				{
					break;
				}

				tree = new CartesianTree(numX, numY);

	    		System.out.println("---------- Test " + nTest++ + " ----------");
				expresion = scanner.nextLine();
	    		nEspacios = 0;

	    		continue;
	    	}

	    	if (expresion.compareTo("") == 0)
	    	{
	    		nEspacios++;
	    		expresion = scanner.nextLine();
	    		continue;
	    	}

	    	if (expresion.compareTo("Solucion:") == 0)
	    	{
	    		nEspacios = 0;
	    		expresion = scanner.nextLine();

	    		if (expresion.compareTo(tree.imprimir()) == 0)
	    			System.out.println("Paso el test de insercion");
	    		else
	    			System.out.println("Ocurrio un error en insercion..");

	    		expresion = scanner.nextLine();
	    		double costoProm = Double.parseDouble(expresion);

	    		if (costoProm == tree.costoPromedio())
	    			System.out.println("Paso el test de costo promedio");
	    		else
	    			System.out.println("Ocurrio un error en costo promedio..");

	    		expresion = scanner.nextLine();
	    		continue;
	    	}

	    	parOrd = expresion.split(" ");
	    	numX = Integer.parseInt(parOrd[0]);
			numY = Double.parseDouble(parOrd[1]);
			tree = tree.insertar(numX, numY);

			expresion = scanner.nextLine();
	    }

	    System.out.println("---------- Fin de Tests ----------");
	}

	public static void parte2()
	{	
		int expMin = 10;
		int expMax = 16;
		int nroRepeticiones = 10;

		double promediosFinales[] = new double[expMax - expMin + 1];

		for (int exp = expMin; exp <= expMax; exp++) 
		{
			double costoAcum = 0;
			int indice = exp - expMin;	

			for (int idy = 1; idy <= nroRepeticiones; idy++) 
			{
				costoAcum += Treap.costoPromedioHastaN((int)Math.pow(2, exp));
			}

			promediosFinales[indice] = costoAcum / nroRepeticiones;
		}

		for (int idx=0; idx < promediosFinales.length; idx++) 
		{
			System.out.println(promediosFinales[idx]);
		}
	}

	public static void tests()
	{	
		// test insercion en ABB
		String correcto1 = "[][](12,22.0)[](25,8.0)[][](42,17.0)[][][](78,15.0)(63,10.0)(56,6.0)[](90,5.0)(37,3.0)";

		CartesianTree treeTest = new CartesianTree(37, 3);
		treeTest.insertarAbb(25, 8);
		treeTest.insertarAbb(12, 22);
		treeTest.insertarAbb(90, 5);
		treeTest.insertarAbb(56, 6);
		treeTest.insertarAbb(42, 17);
		treeTest.insertarAbb(63, 10);
		treeTest.insertarAbb(78, 15);

		assert(treeTest.imprimir().compareTo(correcto1) == 0);
		assert(treeTest.costoTotal() == 24);
		assert(treeTest.costoPromedio() == 3);

		// test de rotaciones
		String correcto2 = "[][][][](78,15.0)(3,3.0)(2,2.0)";

		CartesianTree treeTest2 = new CartesianTree(78, 15);
		treeTest2 = treeTest2.insertar(3, 3);
		treeTest2 = treeTest2.insertar(2, 2);

		assert(treeTest2.imprimir().compareTo(correcto2) == 0);

		String correcto3 = "[][](42,17.0)[][](78,15.0)(63,10.0)";

		CartesianTree treeTest3 = new CartesianTree(78, 15);
		treeTest3 = treeTest3.insertar(63, 10);
		treeTest3 = treeTest3.insertar(42, 17);

		assert(treeTest3.imprimir().compareTo(correcto3) == 0);

		CartesianTree treeTest4 = new CartesianTree(78, 15);
		treeTest4 = treeTest4.insertar(63, 10);
		treeTest4 = treeTest4.insertar(42, 17);
		treeTest4 = treeTest4.insertar(56, 6);
		treeTest4 = treeTest4.insertar(90, 5);
		treeTest4 = treeTest4.insertar(12, 22);
		treeTest4 = treeTest4.insertar(25, 8);
		treeTest4 = treeTest4.insertar(37, 3);

		assert(treeTest4.imprimir().compareTo(correcto1) == 0);
		assert(treeTest4.costoPromedio() == 3);

		// creando e insertando treaps
		Treap treapTest = new Treap(37);
		treapTest.insertar(63);
		treapTest.insertar(42);
	}
}