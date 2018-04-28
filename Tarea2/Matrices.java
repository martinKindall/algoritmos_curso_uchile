import java.util.Scanner;

public class Matrices{

	public static int multiplicarMatrices(int[] p, int[][] m, int[][] s)
	{
		int nMatrices = p.length;
		int n = nMatrices - 1;

		for (int i=1; i <= n; i++) 
		{
			m[i][i] = 0;
		}

		for (int l=2; l<=n; l++) 
		{
			for (int i=1; i<=n-l+1; i++) 
			{
				int j = i + l - 1;
				m[i][j] = Integer.MAX_VALUE;
				for (int k=i; k<=j-1; k++) 
				{
					int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
					if(q < m[i][j])
					{
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}	
		}

		return m[1][n];
	}

	static void printStringArray(String[] palabras)
	{
		for(int i = 0; i < palabras.length; i++)
		{
			if (palabras[i] == null) break;
			System.out.println(palabras[i]);
		}
	}

	static void printMatrix(int[][] mat)
	{
		System.out.println("Matriz = \n");
		for(int i = 0; i < mat.length; i++)
		{
			System.out.print("|");
			for(int j = 0; j < mat.length; j++)
			{
				System.out.print(mat[i][j]);
				System.out.print("|");
			}
			System.out.print("\n");
		}
	}

	static String printParentesis(int[][] s, int i, int j)
	{
		if (i == j)
		{
			return ".";
		}
		else
		{
			return 
				"(" + 
				printParentesis(s, i, s[i][j]) +
				printParentesis(s, s[i][j] + 1, j) +
				")";
		}
	}

	public static void main(String[] args) 
	{
		// tests();
		
		String[] inputs = new String[10];
		matrixParser(inputs);
	}

	static void matrixParser(String[] inputs)
	{
		Scanner scanner = new Scanner(System.in);

	    String line = scanner.nextLine(); 
	    int index = -1;

		while (line.compareTo("") != 0) 
		{
			++index;
			inputs[index] = line;
		    line = scanner.nextLine(); 
		}

		printStringArray(inputs);
	}

	static void tests()
	{
		int[] matrices = {100, 10, 100, 10};
		int nMatrices = matrices.length;

		int[][] steps = new int[nMatrices][nMatrices];
		int[][] costos = new int[nMatrices][nMatrices];

		int costo = multiplicarMatrices(matrices, costos, steps);

		System.out.println("El costo de multiplicar es: " + costo);

		System.out.println("La matriz de pasos es: ");
		printMatrix(steps);

		System.out.println("La matriz de costos es: ");
		printMatrix(costos);

		System.out.println(printParentesis(steps, 1, nMatrices-1));
	}
}