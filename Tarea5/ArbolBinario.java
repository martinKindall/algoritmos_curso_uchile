public class ArbolBinario {
	int car, freq;
	ArbolBinario izq, der;
	
	public ArbolBinario(int car, int freq) {
		this.car = car;
		this.freq = freq;
		izq = null;
		der = null;
	}

	public ArbolBinario(int car, int freq, ArbolBinario arb1, ArbolBinario arb2)
	{
		this.car = car;
		this.freq = freq;
		this.izq = arb1;
		this.der = arb2;
	}
	
	public static boolean esMayor(ArbolBinario arb1, ArbolBinario arb2)
	{
		return arb1.freq > arb2.freq;
	}
}
