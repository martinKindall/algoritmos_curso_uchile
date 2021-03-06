package aux1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	static int globalIndex = 0;

	public static void main(String[] args) {
		// main1();
		main2();
	}

	public static void main1()
	{
		Fraccion prueba1 = new Fraccion(1, 2);
		Fraccion prueba2 = new Fraccion("300/40");
		System.out.println("probando");

		Scanner scanner = new Scanner(System.in);

		System.out.println("n?");
		int nroFrac = scanner.nextInt();
		Fraccion total = new Fraccion("0/1");

		for (int i=0; i<nroFrac; ++i) 
		{
			scanner = new Scanner(System.in);
			System.out.println("Fraccion "+(i+1)+"?");
			Fraccion actual = new Fraccion(scanner.nextLine());

			total = total.suma(actual);
		}

		System.out.println("La suma total es: "+total);
	}

	public static void main2()
	{
		// Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 1);
		// infiniteStream.limit(3).forEach(i -> System.out.println(i));

		Scanner intScanner = new Scanner(System.in);

		System.out.println("n?");
		int nroFrac = intScanner.nextInt();

		Stream<String> inputs = Stream.iterate(printAndScanLine("Fraccion "+index()+"?"), i -> printAndScanLine("Fraccion "+index()+"?"));

		Fraccion total = inputs
			.limit(nroFrac)
			.map(i -> new Fraccion(i))
			.reduce(new Fraccion("0/1"), (x, y) -> x.suma(y));

		System.out.println("La suma total es: "+total);
	}

	public static String printAndScanLine(String message)
	{
		Scanner stringScanner = new Scanner(System.in);

		System.out.println(message);
		return stringScanner.nextLine();
	}

	public static int index()
	{
		globalIndex += 1;
		return globalIndex;
	}
}
