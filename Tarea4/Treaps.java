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

		CartesianTree tree = new CartesianTree(numX, numY);

		while (true) 
		{
		    expresion = scanner.nextLine(); 
		    if (expresion.compareTo("end") == 0) break;

		    parOrd = expresion.split(" ");
			numX = Integer.parseInt(parOrd[0]);
			numY = Double.parseDouble(parOrd[1]);

			tree.insertar(numX, numY);
		}
	}
}