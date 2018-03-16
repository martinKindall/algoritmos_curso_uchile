package aux1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

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
		// crear funcion que cree stream de inputs
		// 
		// usar un reduce de streams que transforme cada elemento
		// del stream a una fraccion y las sume
		// 
		// evaluar el stream en el numero de fracciones elegido por el usuario
		// y hacerle reduce?
	}
}
