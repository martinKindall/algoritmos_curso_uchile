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
		String correcto1 = "[][](12,22.0)[](25,8.0)[][](42,17.0)[][][](78,15.0)(63,10.0)(56,6.0)[](90,5.0)(37,3.0)";

		CartesianTree treeTest = new CartesianTree(37, 3);
		treeTest.insertarAbb(25, 8, null);
		treeTest.insertarAbb(12, 22, null);
		treeTest.insertarAbb(90, 5, null);
		treeTest.insertarAbb(56, 6, null);
		treeTest.insertarAbb(42, 17, null);
		treeTest.insertarAbb(63, 10, null);
		treeTest.insertarAbb(78, 15, null);

		assert(treeTest.imprimir().compareTo(correcto1) == 0);

		CartesianTree treeTest2 = new CartesianTree(78, 15);
	}
}