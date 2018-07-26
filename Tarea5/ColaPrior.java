public class ColaPrior {
	ArbolBinario[] cola;
	int size;

	public ColaPrior()
	{
		cola = new ArbolBinario[300];
		size = 0;
	}

	public void insertar(ArbolBinario arbol)
	{
		this.cola[size++] = arbol;

		for(int j=this.size-1; j > 0 && ArbolBinario.esMayor(this.cola[j/2], this.cola[j]); j/=2)
		{
			ArbolBinario aux = this.cola[j];
			this.cola[j] = this.cola[j/2];
			this.cola[j/2] = aux;
		}
	}

	public ArbolBinario extraer()
	{
		ArbolBinario menor = this.cola[0];
		this.cola[0] = this.cola[--this.size];

		int j = 1;
		while(2*j <= this.size)
		{
			int k = 2 * j - 1;

			if (k + 1 <= this.size-1 && ArbolBinario.esMayor(this.cola[k], this.cola[k+1]))
			{
				k = k + 1;
			}
			if (ArbolBinario.esMayor(this.cola[k], this.cola[j-1]))
			{
				break;
			}

			ArbolBinario aux = this.cola[j-1];
			this.cola[j-1] = this.cola[k];
			this.cola[k] = aux;
			j = k + 1;
		}

		return menor;
	}
}