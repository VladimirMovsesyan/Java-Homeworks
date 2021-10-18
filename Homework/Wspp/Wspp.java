import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class Wspp {

    public static Boolean isAllowedSymbol(char x) {
        return ((Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'' || Character.isLetter(x)));
    }

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        int wordCnt = 0;

        LinkedHashMap<String, intList> mp = new LinkedHashMap<>();

        try {
            MyScanner in = new MyScanner(inputFile, "utf8");
            while (in.hasNextWord()) {
                String word = in.nextWord();
                word = word.toLowerCase();

                intList temp = mp.getOrDefault(word, new intList());
                temp.add(++wordCnt);
                mp.put(word, temp);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Some problems with input file: " + e.getMessage());
        }

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf8"));
            for (String s : mp.keySet()) {
                out.write(s);
                out.write(" ");
                out.write(mp.get(s).toString());
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Some problems with output file: " + e.getMessage());
        }
    }
}