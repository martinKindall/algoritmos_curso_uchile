import java.util.Scanner;

public class PilaArena 
{
	public static void main(String[] args) 
	{
		Pila pilaArena = new Pila(ingresarN());

		pilaArena.moverArena();
		pilaArena.mostrar();
	}

	public static int ingresarN()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese N: ");
		int granos = scanner.nextInt();

		return granos;
	}
}

class Pila
{
	int nroVeces = 0;
	int nGranos;
	int ladoTablero;
	int[][] tablero;

	public Pila(int nGranos)
	{
		this.nGranos = nGranos;
		this.ladoTablero = estimarTableroSegunNGranos(nGranos);
		this.tablero = new int[this.ladoTablero][this.ladoTablero];
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
	public void moverArena()
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
	public void recMoverArena(int i, int j)
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

	public void mostrar()
	{
		Ventana ventana = new Ventana(800, "Pila de arena");
		ventana.mostrarMatriz(this.tablero);
	}
}
