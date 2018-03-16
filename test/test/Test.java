package test;

import java.util.stream.*;

public class Test{

	public static void main(String[] args)
	{
		int edad = 28;
		System.out.println("Hola mundo!");
		System.out.println("Tu edad es: " + edad);

		for (int i=10; i>-1; --i) 
		{
			System.out.println("Quedan "+i);
		}

		System.out.println("Finalizado!");
		System.out.println("Ahora un ciclo con streams");

		IntStream.range(1, 11).forEach(i -> System.out.println("Numero "+i));
	}
}