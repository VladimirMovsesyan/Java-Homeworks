import java.io.*;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Map;

public class WsppSortedPosition {

    public static boolean isAllowedSymbol(char x) {
        return Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'' || Character.isLetter(x);
    }

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        int stringnum = 1, pos = 1;

        Map<String, MyIntList> mp = new TreeMap<>();

        try {
            MyScanner in = new MyScanner(inputFile, "utf8");
            while (in.hasNextWord()) {
                String word = in.nextWord();
                word = word.toLowerCase();
                if (in.checkEnd()) {
                    stringnum++;
                    pos = 1;
                }
                MyIntList temp = mp.getOrDefault(word, new MyIntList());
                temp.addPair(stringnum, pos++);
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