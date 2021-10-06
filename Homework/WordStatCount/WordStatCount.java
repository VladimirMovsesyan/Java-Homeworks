import java.io.*;
import java.util.*;

public class WordStatCount {

    public static int parseInd(String s) {
        if (s.indexOf("#") < 0) {
            return 0;
        }
        return Integer.parseInt(s.substring(s.indexOf("#") + 1, s.indexOf("$")));
    }

    public static int parseCount(String s) {
        if (s.indexOf("$") < 0) {
            return 0;
        }
        return Integer.parseInt(s.substring(s.indexOf("$") + 1));
    }

    public static String parseWord(String s) {
        return s.substring(0, s.indexOf("#"));
    }

    public static Boolean isAllowedSymbol(char x) {
        return ((Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'' || Character.isLetter(x)));
    }

    public static String[] sort(String[] words, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (parseWord(words[j]).compareTo(parseWord(words[j + 1])) > 0) {
                    String temp = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = temp;
                }
            }
        }
        return words;
    }

    public static String[] getAns(String[] words, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (parseCount(words[j]) >= parseCount(words[j + 1])) {
                    if (parseCount(words[j]) > parseCount(words[j + 1])) {
                        String temp = words[j];
                        words[j] = words[j + 1];
                        words[j + 1] = temp;
                    }
                    
                    if (parseCount(words[j]) == parseCount(words[j + 1]) && parseInd(words[j]) > parseInd(words[j + 1])) {
                        String temp = words[j];
                        words[j] = words[j + 1];
                        words[j + 1] = temp;
                    }
                }
            }
        }
        return words;
    }

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        // String inputFile = "input.txt";
        // String outputFile = "output.txt";

        // index == #, count == $

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
                    dict[wordCnt - 1] = dict[wordCnt - 1].toLowerCase() + "#" + Integer.toString(wordCnt - 1);
                    i = j - 1;
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Some problems with input file: " + e.getMessage());
        }

        dict = sort(dict, wordCnt);

        for (int i = 0; i < wordCnt; i++) {
            int j = i + 1;
            while (j < wordCnt && parseWord(dict[j]).equals(parseWord(dict[i]))) {
                dict[j] = "";
                j++;
            }
            dict[i] = dict[i] + "$" + Integer.toString(j - i);
            i = j - 1;
        }

        dict = getAns(dict, wordCnt);

        /*
        or 1
        not 1
        that 1
        is 1
        the 1
        question 1
        to 2
        be 2
        */

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf8"));
            for (int i = 0; i < wordCnt; i++) {
                if (dict[i].indexOf("#") >= 0) {
                    out.write(dict[i].substring(0, dict[i].indexOf("#")) + " " + parseCount(dict[i]));
                    out.newLine();
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Some problems with output file: " + e.getMessage());
        }

    }
}