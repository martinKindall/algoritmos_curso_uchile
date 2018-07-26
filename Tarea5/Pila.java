public class Pila {
	
	ListaEnlazada lista; 
	int size;

	public Pila() {
		lista = null;
		size = 0;
	}

	public void apilar(ArbolBinario arbol) {
		lista = new ListaEnlazada(arbol, lista);
		size++;
	}

	public ArbolBinario desapilar() {
		ArbolBinario top = null;
		if (size > 0)
		{
			top = lista.arbol;
			lista = lista.sig;
			size--;
		}
		return top;
	}

	public boolean estaVacia() {
		return size == 0;
	}
}
