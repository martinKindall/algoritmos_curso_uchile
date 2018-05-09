import java.util.Scanner;
import java.util.Arrays;

public class Derivar_polaca{

	public static void main(String[] args) 
	{
		tests();
		
		System.out.print("Ingrese expresion en notacion polaca inversa: ");
		Scanner scanner = new Scanner(System.in);

	    String line = scanner.nextLine();

	    String elems[] = line.split(" ");

	    System.out.println(line);
	    System.out.println(Arrays.toString(elems));
	}

	static void tests()
	{

	}
}