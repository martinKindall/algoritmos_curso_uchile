import java.util.Scanner;

public class PilaArena {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Ingrese N: ");
		int nroFrac = scanner.nextInt();

		Ventana ventana = new Ventana(800, "Primera ventana");
		int[][] tablero = new int[nroFrac][nroFrac];

		tablero[1][1] = 3;
		tablero[0][1] = 2;
		tablero[1][0] = 2;
		tablero[2][2] = 1;
		tablero[2][1] = 0;

		ventana.mostrarMatriz(tablero);
	}

}
