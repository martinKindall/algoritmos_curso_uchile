public class ArbolBinario {
	String val;
	ArbolBinario izq, der;
	
	public ArbolBinario(String v) {
		val = v;
		izq = null;
		der = null;
	}
	
	public ArbolBinario(String v, ArbolBinario i, ArbolBinario d) {
		val = v;
		izq = i;
		der = d;
	}
}
