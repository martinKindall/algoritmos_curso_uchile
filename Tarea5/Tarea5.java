import java.io.*;

public class Tarea5{

	public static void main(String[] args) throws IOException
	{
	    int[] frecuencias = getFrecuenciasFromFile("ElCidC1.txt", "ISO-8859-1");
	}

	public static int[] getFrecuenciasFromFile(String textFile, String encoding) throws IOException
	{
		BufferedReader reader = 
		    new BufferedReader(
		    	new InputStreamReader(
		    		new FileInputStream(textFile),
		    		encoding)
    	);

        String line = null;
        int[] frecuencias = new int[300];

        while ((line = reader.readLine()) != null) 
        {
            for (int idx=0; idx<line.length(); idx++) 
            {
            	char currChar = line.charAt(idx);
            	// System.out.println((int)currChar);
            	frecuencias[(int)currChar]++;
           	}	

        }

        return frecuencias;
	}
}