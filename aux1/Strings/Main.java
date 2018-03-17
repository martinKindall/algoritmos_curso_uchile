package Strings;

import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		// testRotacionCircular();
		
		testInvertirPalabra();
		testEsPalindrome();
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
}
