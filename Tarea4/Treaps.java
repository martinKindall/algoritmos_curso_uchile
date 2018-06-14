import java.util.Scanner;

public class Treaps{

	public static void main(String[] args) 
	{
		System.out.print("Ingrese par ordenado: ");
		Scanner scanner = new Scanner(System.in);
	    String expresion = scanner.nextLine();

	    String[] parOrd = expresion.split(" ");
		int numX = Integer.parseInt(parOrd[0]);
		double numY = Double.parseDouble(parOrd[1]);

		System.out.println(numX);
		System.out.println(numY);

		CartesianTree tree = new CartesianTree(numX, numY);

		tree.insertar(25, 8);
	}
}