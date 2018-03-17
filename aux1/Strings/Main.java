package Strings;

public class Main {

	public static void main(String[] args) {
		String word_1 = "algoritmo";
		String word_2 = "ritmoalgo";
		String word_3 = "ritmoalga";

		System.out.println(esRotacionCicular(word_1, word_2));
		System.out.println(esRotacionCicular(word_1, word_3));

		System.out.println(esRotacionCicular_v2(word_1, word_2));
		System.out.println(esRotacionCicular_v2(word_1, word_3));
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
}
