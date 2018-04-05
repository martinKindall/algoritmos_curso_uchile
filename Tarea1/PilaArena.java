import java.util.Scanner;

public class PilaArena 
{
	final static boolean recursivo = false;

	public static void main(String[] args) 
	{
		pilaTest();

		Tablero pilaArena = new Tablero(ingresarN());

		if (recursivo)
		{
			pilaArena.recMoverArena();
		}
		else
		{
			pilaArena.moverArena();
		}

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
		Tablero pilaArena = new Tablero(5);
		pilaArena.moverArena();

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

class Tablero
{
	final static boolean mejorado = false;

	double celdasRevisadas = 0;
	int nroVeces = 0;
	int nGranos;
	int montoResta;
	int ladoTablero;
	int[][] tablero;

	public Tablero(int nGranos)
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
	 */
	public void moverArena()
	{
		int indiceCentro = --this.ladoTablero / 2;

		this.tablero[indiceCentro][indiceCentro] = this.nGranos;

		int ladoVirtual = 1;
		int montoResta;

		for (int i=indiceCentro; i<=indiceCentro+ladoVirtual/2; ++i) 
		{
			for (int j=indiceCentro-ladoVirtual/2; j<=indiceCentro+ladoVirtual/2;) 
			{
				this.celdasRevisadas += 1;

				if (this.tablero[i][j] > 3)
				{
					// esta optimizacion permite recorrer el arreglo solo en donde hay data (podemos verlo
					// como un arreglo virtual). A medida que nuestro tablero va 'creciendo' se aumenta el
					// lado del arreglo que queremos recorrer
					if (this.ladoTablero > 9 && (
						i == indiceCentro-ladoVirtual/2))
					{
						ladoVirtual += 2;
					}

					this.nroVeces += 1;

					this.montoResta = mejorado ? this.tablero[i][j]/4 : 1;

					this.tablero[i][j] -= this.montoResta * 4;	
					this.tablero[i+1][j] += this.montoResta;	
					this.tablero[i][j+1] += this.montoResta;	
					this.tablero[i-1][j] += this.montoResta;	
					this.tablero[i][j-1] += this.montoResta;	

					if (this.tablero[i][j] <= 3)
					{
						--i;
						--j;
					}
				}
				else
				{
					// estos ajustes son para ver si el derrumbe causo mas derrumbes,
					// y volver a chequear las 4 celdas afectadas
					// partiendo por la de arriba de la cruz
					++j;
				}
			}	
		}
	}

	/**
	 * [recMoverArena: funciona para N muy bajo, de forma recursiva]
	 * efecto -> ordena el arena de acuerdo a la regla de derrumbes
	 */
	public void recMoverArena()
	{
		int indiceCentro = --this.ladoTablero / 2;

		this.tablero[indiceCentro][indiceCentro] = this.nGranos;

		this.celdasRevisadas += 1;

		if (this.tablero[indiceCentro][indiceCentro] > 3)
		{
			this.nroVeces += 1;

			this.montoResta = mejorado ? this.tablero[indiceCentro][indiceCentro]/4 : 1;

			this.tablero[indiceCentro][indiceCentro] -= 4 * this.montoResta;	
			this.tablero[indiceCentro+1][indiceCentro] += this.montoResta;	
			this.tablero[indiceCentro][indiceCentro+1] += this.montoResta;	
			this.tablero[indiceCentro-1][indiceCentro] += this.montoResta;	
			this.tablero[indiceCentro][indiceCentro-1] += this.montoResta;

			this.recMoverArena(indiceCentro, indiceCentro);
			this.recMoverArena(indiceCentro+1, indiceCentro);
			this.recMoverArena(indiceCentro, indiceCentro+1);
			this.recMoverArena(indiceCentro-1, indiceCentro);
			this.recMoverArena(indiceCentro, indiceCentro-1);
		}
	}

	private void recMoverArena(int i, int j)
	{
		this.celdasRevisadas += 1;

		if (this.tablero[i][j] > 3)
		{
			this.nroVeces += 1;

			this.montoResta = mejorado ? this.tablero[i][j]/4 : 1;

			this.tablero[i][j] -= 4 * this.montoResta;	
			this.tablero[i+1][j] += this.montoResta;	
			this.tablero[i][j+1] += this.montoResta;	
			this.tablero[i-1][j] += this.montoResta;	
			this.tablero[i][j-1] += this.montoResta;

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

		System.out.println("Se usó " + this.nroVeces + " veces la logica de derrumbes de arena revisando " + this.celdasRevisadas + " celdas.");

		ventana.mostrarMatriz(this.tablero);
	}
}
