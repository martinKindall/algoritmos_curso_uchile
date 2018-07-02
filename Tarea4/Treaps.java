import java.util.Scanner;

public class Treaps{

	public static void main(String[] args) 
	{
		tests();

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
		double costoProm = treeTest4.costoPromedio();
		assert(treeTest4.costoPromedio() == 3);
	}
}