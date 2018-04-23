public class p1 {

	public static void main(String[] args) {
		//System.out.println(lanzarMoneda());
		// System.out.println(juegoConMonedas());
		
		int numJuegos = 10000;
		int alicia = 0;
		int roberto = 0;

		for (; numJuegos>0; --numJuegos) {
			if (juegoConMonedas()){
				++roberto;
			}
			else{
				++alicia;
			}
		}

		System.out.println("Alicia gano "+alicia+" veces y Roberto "+roberto+" veces.");
	}

	static boolean lanzarMoneda(){
		return Math.random() > 0.5;
	}

	static boolean juegoConMonedas()
	{
		boolean ant1=false, ant2=false;
		int estados = 0;

		while(true){
			boolean actual = lanzarMoneda();
			if (estados == 0){
				ant2 = actual;
				++estados;
			}
			else if(estados == 1){
				ant1 = ant2;
				ant2 = actual;
				++estados;
			}
			else{
				if (ant1 && ant2 && !actual) return false;
				if (ant1 && !ant2 && !actual) return true;
				// if (!ant1 && !ant2 && actual) return true;   // version justa
				ant1 = ant2;
				ant2 = actual;
			}
		}
	}
}