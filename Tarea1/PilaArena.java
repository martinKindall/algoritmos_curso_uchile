import java.util.Scanner;

public class PilaArena {

	public static void main(String[] args) {
		
		int nGranos = ingresarN();
		int ladoTablero = estimarTableroSegunNGranos(nGranos);

		// test(ladoTablero);
		
		Ventana ventana = new Ventana(800, "Pila de arena");

		int[][] tablero = new int[ladoTablero][ladoTablero];

		moverArena(tablero, ladoTablero, nGranos);
		ventana.mostrarMatriz(tablero);
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

	/**
	 * [moverArena: simula los derrumbes de una cantidad nGranos en una celda determinada por el indice puntoInicial]
	 * efecto -> modifica el arreglo tablero segun las reglas de derrumbes de arena
	 * @param tablero      
	 * @param puntoInicial
	 * @param nGranos     
	 */
	public static void moverArena(int[][] tablero, int ladoTablero, int nGranos)
	{
		int indiceCentro = --ladoTablero / 2;

		tablero[indiceCentro][indiceCentro] = nGranos;

		for (int i=0; i<ladoTablero; ++i) 
		{
			for (int j=0; j<ladoTablero; ++j) 
			{
				if (tablero[i][j] > 3)
				{
					tablero[i][j] -= 4;	
					tablero[i+1][j] += 1;	
					tablero[i][j+1] += 1;	
					tablero[i-1][j] += 1;	
					tablero[i][j-1] += 1;	

					// estos ajustes son para ver si el derrumbe causo mas derrumbes,
					// y volver a chequear las 4 celdas afectadas
					// partiendo por la de arriba de la cruz
					i -= 1;
					j -= 1;

					// Aca conviene mejor hacer una llamada recursiva para mover arena
					// a una porcion menor del tablero, con un lado reducido por ejemplo, 
					// de manera que los derrumbes sean revisados de manera local y no tengan que hacer revisar filas completas
				}
			}	
		}
	}

	/**
	 * [checkDerrumbeRecursivo: funciona para N muy bajo]
	 * @param tablero 
	 * @param i       
	 * @param j       
	 */
	public static void checkDerrumbeRecursivo(int[][] tablero, int i, int j)
	{
		if (tablero[i][j] > 3)
		{
			tablero[i][j] -= 4;	
			tablero[i+1][j] += 1;	
			tablero[i][j+1] += 1;	
			tablero[i-1][j] += 1;	
			tablero[i][j-1] += 1;

			checkDerrumbeRecursivo(tablero, i, j);
			checkDerrumbeRecursivo(tablero, i+1, j);
			checkDerrumbeRecursivo(tablero, i, j+1);
			checkDerrumbeRecursivo(tablero, i-1, j);
			checkDerrumbeRecursivo(tablero, i, j-1);
		}
	}
}
