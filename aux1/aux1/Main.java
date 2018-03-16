package aux1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Fraccion prueba1 = new Fraccion(1, 2);
		Fraccion prueba2 = new Fraccion("300/40");
		System.out.println("probando");

		Scanner scanner = new Scanner(System.in);

		System.out.println("n?");
		int nroFracciones = scanner.nextInt();

		System.out.println("Fraccion 1?");
	}
}
