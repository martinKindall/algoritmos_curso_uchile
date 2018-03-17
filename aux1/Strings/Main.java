package Strings;

import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		// testRotacionCircular();
		
		// testInvertirPalabra();
		// testEsPalindrome();

		// testOracionPalindrome();

		testStringContieneCaracteres();
		testStringCompleto();
	}

	public static boolean esRotacionCicular(String r, String w)
	{
		if (r.equals(w)) return true;

		for(int i=0; i<r.length(); ++i)
		{
			r = r.substring(1, r.length()) + r.substring(0,1);

			System.out.println(r);

			if (r.equals(w)) return true;
		}

		return false;
	}

	public static boolean esRotacionCicular_v2(String r, String w)
	{
		if (r.equals(w)) return true;

		return (r+r).contains(w);
	}

	public static boolean palabraEsPalindrome(String s)
	{
		return s.equals(invertirPalabra(s));
	}

	public static String invertirPalabra(String s)
	{
		String invertida = new StringBuilder(s).reverse().toString();
		return invertida;
	}

	public static boolean oracionPalindrome(String s)
	{
		String oracionSinEspacios = s.replace(" ", "");

		return palabraEsPalindrome(oracionSinEspacios);
	}

	public static boolean wordContainsCharacters(String s, String characters)
	{
		if (s.equals("") || characters.equals("")) return true;

		String first = characters.substring(0,1);

		if (s.contains(first))
		{
			return wordContainsCharacters(s.replace(first, ""), characters.substring(1,characters.length()));
		}

		return false;
	}

	/* tests */

	public static void testRotacionCircular()
	{
		String word_1 = "algoritmo";
		String word_2 = "ritmoalgo";
		String word_3 = "ritmoalga";

		System.out.println(esRotacionCicular(word_1, word_2));
		System.out.println(esRotacionCicular(word_1, word_3));

		System.out.println(esRotacionCicular_v2(word_1, word_2));
		System.out.println(esRotacionCicular_v2(word_1, word_3));
	}

	public static void testEsPalindrome()
	{
		String palindrome_1 = "aviva";
		String palindrome_2 = "aboba";
		String palindrome_3 = "reconocer";
		String palindrome_4 = "reconocera";

		System.out.println(palabraEsPalindrome(palindrome_1));
		System.out.println(palabraEsPalindrome(palindrome_2));
		System.out.println(palabraEsPalindrome(palindrome_3));
		System.out.println(palabraEsPalindrome(palindrome_4));
	}

	public static void testInvertirPalabra()
	{
		String word = "elefante";
		String inverted = "etnafele";

		if (!inverted.equals(invertirPalabra(word)))
		{
			System.out.println("error al invertir palabra");
		}
	}

	public static void testOracionPalindrome()
	{
		System.out.println("");
		System.out.println("oraciones palindromes");

		String orPalindrome_1 = "anita lava la tina";
		String orPalindrome_2 = "anita lava la tinas";

		System.out.println(oracionPalindrome(orPalindrome_1));
		System.out.println(oracionPalindrome(orPalindrome_2));
	}

	public static void testStringContieneCaracteres()
	{
		String palabra = "qwasdferqewrqeasdfrqerzxcvqerqwr";
		String chars = "qwer";

		System.out.println(wordContainsCharacters(palabra, chars));
	}

	public static void testStringCompleto()
	{
		String word_1 = "esta oracion no contiene el abecedario";
		String stringCompleto = "a b c asd e f g h i tyiuj k l , m n o zcxvp q r s t u v wfdg x y z.p{}{";
		String chars = "abcdefghijklmnopqrstuvwxyz";

		if (wordContainsCharacters(word_1, chars))
		{
			System.out.println("Error en el string completo test1");
		}

		if (!wordContainsCharacters(stringCompleto, chars))
		{
			System.out.println("Error en el string completo test2");
		}
	}
}
