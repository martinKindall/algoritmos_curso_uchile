import java.util.Scanner;

public class PilaArena 
{
	final static boolean mejorado = true;

	public static void main(String[] args) 
	{
		pilaTest();

		Pila pilaArena = new Pila(ingresarN());

		pilaArena.moverArena(mejorado);
		pilaArena.mostrar();
	}

	public static int ingresarN()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese N: ");
		int granos = scanner.nextInt();

		return granos;
	}

	// tests
	
	public static void pilaTest()
	{
		Pila pilaArena = new Pila(5);
		pilaArena.moverArena(mejorado);

		int[][] tableroFinal = new int[3][3];
		tableroFinal[0][1] = 1;
		tableroFinal[1][0] = 1;
		tableroFinal[1][1] = 1;
		tableroFinal[1][2] = 1;
		tableroFinal[2][1] = 1;

		for (int idx=0; idx<3; ++idx) 
		{
			for (int idy=0; idy<3; ++idy) 
			{
				assert pilaArena.tablero[idx][idy] == tableroFinal[idx][idy];	
			}
		}
	}
}

class Pila
{
	double comparaciones = 0;
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
	 * @param  nGranos 
	 * @return int       
	 */
	private static int estimarTableroSegunNGranos(int nGranos)
	{
		int lado = (int)Math.sqrt(nGranos);

		return lado % 2 == 0 ? lado+1 : lado;
	}

	/**
	 * [moverArena: simula los derrumbes de una cantidad nGranos en una celda determinada por el indice puntoInicial]
	 * efecto -> modifica el arreglo tablero segun las reglas de derrumbes de arena]
	 * @param mejorado
	 */
	public void moverArena(boolean mejorado)
	{
		int indiceCentro = --this.ladoTablero / 2;

		this.tablero[indiceCentro][indiceCentro] = this.nGranos;

		for (int i=0; i<this.ladoTablero; ++i) 
		{
			for (int j=0; j<this.ladoTablero; ++j) 
			{
				this.comparaciones += 1;

				if (this.tablero[i][j] > 3)
				{
					this.nroVeces += 1;

					int montoResta = mejorado ? this.tablero[i][j]/4 : 1;

					this.tablero[i][j] -= montoResta * 4;	
					this.tablero[i+1][j] += montoResta;	
					this.tablero[i][j+1] += montoResta;	
					this.tablero[i-1][j] += montoResta;	
					this.tablero[i][j-1] += montoResta;	

					// estos ajustes son para ver si el derrumbe causo mas derrumbes,
					// y volver a chequear las 4 celdas afectadas
					// partiendo por la de arriba de la cruz
					i -= 1;
					j -= 1;
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

		System.out.println("Se usó " + this.nroVeces + " veces la logica de derrumbes de arena usando " + this.comparaciones + " comparaciones.");

		ventana.mostrarMatriz(this.tablero);
	}
}
