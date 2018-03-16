package aux1;

public class Fraccion {
	public int num, den;

	public Fraccion(int num, int den)
	{
		this.num = num;
		this.den = den;
	}

	public Fraccion(String frac)
	{
		String[] partes = frac.split("/");
		this.num = Integer.parseInt(partes[0]);
		this.den = Integer.parseInt(partes[1]);
	}
}
