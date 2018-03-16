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

	public static int mcd(int a, int b)
	{
		if (b == 0)
		{
			return a;
		}
		return mcd(b, a%b);
	}

	public void simplificar()
	{
		int mcd = mcd(this.num, this.den);
		this.num /= mcd;
		this.den /= mcd;
	}

	public Fraccion suma(Fraccion other)
	{
		int num = other.num * this.den + this.num * other.den;
		int den = other.den * this.den;

		Fraccion result = new Fraccion(num, den);
		result.simplificar();

		return result;
	}

	public String toString()
	{
		return this.num + "/" + this.den;
	}
}
