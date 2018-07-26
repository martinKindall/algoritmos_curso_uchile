public class ListaEnlazada {
	ArbolBinario arbol;
	ListaEnlazada sig;
	
	public ListaEnlazada(ArbolBinario arbol) {
		this.arbol = arbol;
		this.sig = null;
	}
	
	public ListaEnlazada(ArbolBinario arbol, ListaEnlazada sig) {
		this.arbol = arbol;
		this.sig = sig;
	}
}