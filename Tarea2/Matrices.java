import java.util.Scanner;

public class Matrices{

	static int MAX_SIZE = 100;
	static String EOF = "EOF";

	public static void main(String[] args) 
	{
		tests();
		
		String[] inputs = matrixParser(MAX_SIZE);
		// printStringArray(inputs);

		for (int i=0; i<inputs.length; i++) 
		{
			System.out.println(stringMultiplicarMatrices(inputs[i]));
		}
	}

	static String stringMultiplicarMatrices(String s)
	{
		int[] matrices = strToIntArr(s);
		int nMatrices = matrices.length;

		int[][] separadores = new int[nMatrices][nMatrices];
		int[][] costos = new int[nMatrices][nMatrices];

		multiplicarMatrices(matrices, costos, separadores);
		// printMatrix(separadores);
		// printMatrix(costos);

		return printParentesis(separadores, 1, nMatrices-1);
	}

	static int multiplicarMatrices(int[] p, int[][] costos, int[][] separadores)
	{
		int nMatrices = p.length;
		int n = nMatrices - 1;

		for (int i=1; i <= n; i++) 
		{
			costos[i][i] = 0;
		}

		for (int l=2; l<=n; l++) 
		{
			for (int i=1; i<=n-l+1; i++) 
			{
				int j = i + l - 1;
				costos[i][j] = Integer.MAX_VALUE;
				for (int k=i; k<=j-1; k++) 
				{
					int q = costos[i][k] + costos[k+1][j] + p[i-1]*p[k]*p[j];
					if(q < costos[i][j])
					{
						costos[i][j] = q;
						separadores[i][j] = k;
					}
				}
			}	
		}

		return costos[1][n];
	}

	static void printStringArray(String[] palabras)
	{
		for(int i = 0; i < palabras.length; i++)
		{
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

	static int[] strToIntArr(String s)
	{
		String[] strMatrices = s.split(" ");
		int nMatrices = strMatrices.length;

		int[] matrices = new int[nMatrices];

		for (int i=0; i<nMatrices; i++) 
		{
			matrices[i] = Integer.parseInt(strMatrices[i]);
		}

		return matrices;
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

	static String[] matrixParser(int maxSize)
	{
		String[] inputs = new String[maxSize];

		Scanner scanner = new Scanner(System.in);

	    String line = scanner.nextLine(); 
	    int index = -1;

		while (line.compareTo(EOF) != 0 && index < maxSize) 
		{
			++index;
			inputs[index] = line;
		    line = scanner.nextLine(); 
		}

		if (index < maxSize - 1)
		{
			String[] resize = new String[index+1];

			for (int i=0; i<=index; i++) 
			{
				resize[i] = inputs[i];
			}

			return resize;
		}

		return inputs;
	}

	static void tests()
	{
		assert(stringMultiplicarMatrices("10 100 10 100").compareTo("((..).)") == 0);
		assert(stringMultiplicarMatrices("2 3 5 4").compareTo("((..).)") == 0);
		assert(stringMultiplicarMatrices("2 3 5 4 1 2").compareTo("((.(.(..))).)") == 0);
		assert(stringMultiplicarMatrices("10 30 5 60").compareTo("((..).)") == 0);
	}
}