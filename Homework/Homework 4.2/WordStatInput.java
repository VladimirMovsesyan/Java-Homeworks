import java.io.*;
import java.util.*;

public class WordStatInput {

    public static String[] sort(String[] words, int wordCnt) {
        for (int i = 0; i < wordCnt - 1; i++) {
            for (int j = 0; j < wordCnt - i - 1; j++) {
                if (words[j].compareTo(words[j + 1]) > 0) {
                    String jopa = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = jopa;
                }
            }
        }
        return words;
    }

    public static Boolean isAllowedSymbol(char x) {
        return ((Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'' || Character.isLetter(x)));
    }

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        // String inputFile = "input.txt";
        // String outputFile = "output.txt";

        String[] dict = new String[1];
        int wordCnt = 0;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "utf8"));
            String currentLine;
            while (true) {
                currentLine = in.readLine();
                if (currentLine == null) {
                    break;
                }
                for (int i = 0; i < currentLine.length(); i++) {
                    if (!isAllowedSymbol(currentLine.charAt(i))) {
                        continue;
                    }
                    int j = i + 1;
                    while (j < currentLine.length() && isAllowedSymbol(currentLine.charAt(j))) {
                        j++;
                    }

                    if (wordCnt < dict.length) {
                        dict[wordCnt++] = currentLine.substring(i, j);
                    } else {
                        dict = Arrays.copyOf(dict, dict.length * 2);
                        dict[wordCnt++] = currentLine.substring(i, j);
                    }
                    dict[wordCnt - 1] = dict[wordCnt - 1].toLowerCase();
                    i = j - 1;
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Some problems with input file: " + e.getMessage());
        }

        dict = sort(dict, wordCnt);

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf8"));
            for (int i = 0; i < wordCnt; i++) {
                int j = i + 1;
                while (j < wordCnt && dict[j].equals(dict[i])) {
                    j++;
                }
                out.write(dict[i] + " " + (j - i));
                out.newLine();
                i = j - 1;
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Some problems with output file: " + e.getMessage());
        }

    }
}