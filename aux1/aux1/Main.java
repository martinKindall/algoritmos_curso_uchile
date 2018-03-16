package aux1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
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
}
