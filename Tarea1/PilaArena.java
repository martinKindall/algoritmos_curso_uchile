import java.util.Scanner;

public class PilaArena {

	public static void main(String[] args) {
		
		int nGranos = ingresarN();
		int ladoTablero = estimarTableroSegunNGranos(nGranos);

		// test(ladoTablero);
		
		Ventana ventana = new Ventana(800, "Pila de arena");

		int[][] tablero = new int[ladoTablero][ladoTablero];
	}

	public static int ingresarN()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese N: ");
		int granos = scanner.nextInt();

		return granos;
	}

	public static void test(int ladoTablero)
	{
		Ventana ventana = new Ventana(800, "Primera ventana");
		int[][] tablero = new int[ladoTablero][ladoTablero];

		tablero[1][1] = 3;
		tablero[0][1] = 2;
		tablero[1][0] = 2;
		tablero[2][2] = 1;
		tablero[2][1] = 0;

		ventana.mostrarMatriz(tablero);
	}

	/**
	 * [estimarTableroSegunNGranos: estima el lado del tablero cuadrado para que quepan todos los granos de arena]
	 * @param  granos [int]
	 * @return        [int]
	 */
	public static int estimarTableroSegunNGranos(int granos)
	{
		int lado = (int)Math.sqrt(granos);

		return lado % 2 == 0 ? lado+1 : lado;
	}
}
