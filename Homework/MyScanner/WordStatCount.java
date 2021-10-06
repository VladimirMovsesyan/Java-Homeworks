import java.io.*;
import java.util.Arrays;

public class WordStatCount {

    public static Boolean isAllowedSymbol(char x) {
        return ((Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'' || Character.isLetter(x)));
    }

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        String[] dict = new String[1];
        int wordCnt = 0;
        int[] dictCnt;

        try {
            MyScanner in = new MyScanner(inputFile, "utf8");
            while (in.hasNextWord()) {
                String word = in.nextWord();
                if (wordCnt < dict.length) {
                    dict[wordCnt++] = word;
                } else {
                    dict = Arrays.copyOf(dict, dict.length * 2);
                    dict[wordCnt++] = word;
                }
                dict[wordCnt - 1] = dict[wordCnt - 1].toLowerCase();
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Some problems with input file: " + e.getMessage());
        }

        dictCnt = new int[dict.length];

        for (int i = 0; i < wordCnt; i++) {
            if (dict[i] == null) {
                continue;
            }
            for (int j = i + 1; j < wordCnt; j++) {
                if (dict[i].equals(dict[j])) {
                    dictCnt[i] += 1;
                    dict[j] = null;
                    dictCnt[j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < wordCnt - 1; i++) {
            for (int j = 0; j < wordCnt - i - 1; j++) {
                if (dictCnt[j] > dictCnt[j + 1]) {
                    int temp = dictCnt[j];
                    dictCnt[j] = dictCnt[j + 1];
                    dictCnt[j + 1] = temp;
                    String temp2 = dict[j];
                    dict[j] = dict[j + 1];
                    dict[j + 1] = temp2;
                }
            }
        }

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf8"));
            for (int i = 0; i < wordCnt; i++) {
                if (dict[i] != null) {
                    out.write(dict[i] + " " + (dictCnt[i] + 1));
                    out.newLine();
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Some problems with output file: " + e.getMessage());
        }
    }
}