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

		
	class Pila
	{
		int nroVeces = 0;
		int lado;
		int[][] tablero;

		public Pila(int nGranos)
		{
			this.nGranos = nGranos;
			this.lado = estimarTableroSegunNGranos(nGranos);
			this.tablero = new int[this.lado][this.lado];
		}


		/**
		 * [estimarTableroSegunNGranos: estima el lado del tablero cuadrado para que quepan todos los granos de arena]
		 * @param  nGranos [int]
		 * @return        [int]
		 */
		private static int estimarTableroSegunNGranos(int nGranos)
		{
			int lado = (int)Math.sqrt(nGranos);

			return lado % 2 == 0 ? lado+1 : lado;
		}

		/**
		 * [moverArena: simula los derrumbes de una cantidad nGranos en una celda determinada por el indice puntoInicial]
		 * efecto -> modifica el arreglo tablero segun las reglas de derrumbes de arena
		 */
		private void moverArena()
		{
			int indiceCentro = --this.ladoTablero / 2;

			this.tablero[indiceCentro][indiceCentro] = this.nGranos;

			for (int i=0; i<this.ladoTablero; ++i) 
			{
				for (int j=0; j<this.ladoTablero; ++j) 
				{
					if (this.tablero[i][j] > 3)
					{
						this.tablero[i][j] -= 4;	
						this.tablero[i+1][j] += 1;	
						this.tablero[i][j+1] += 1;	
						this.tablero[i-1][j] += 1;	
						this.tablero[i][j-1] += 1;	

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
		 * [recMoverArena: funciona para N muy bajo, de forma recursiva]
		 * efecto -> ordena el arena de acuerdo a la regla de derrumbes
		 * @param i       
		 * @param j       
		 */
		private void recMoverArena(int i, int j)
		{
			if (this.tablero[i][j] > 3)
			{
				this.tablero[i][j] -= 4;	
				this.tablero[i+1][j] += 1;	
				this.tablero[i][j+1] += 1;	
				this.tablero[i-1][j] += 1;	
				this.tablero[i][j-1] += 1;

				this.recMoverArena(i, j);
				this.recMoverArena(i+1, j);
				this.recMoverArena(i, j+1);
				this.recMoverArena(i-1, j);
				this.recMoverArena(i, j-1);
			}
		}
	}
}
