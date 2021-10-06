import java.io.*;
import java.util.*;

public class WordStatInput {
    public static void main(String[] args) {
        String fileIn = args[0];
        String fileOut = args[1];
        // String fileIn = "input.txt";
        // String fileOut = "output.txt";
        BufferedReader in = null;
        StringBuilder wholeInput = new StringBuilder();
        Vector<String> v = new Vector<>();
        Map<String, Integer> map =  new TreeMap<String, Integer>();
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fileIn), "utf8"));
            while (true) {
                String s = in.readLine();
                if (s == null) {
                    break;
                }
                wholeInput.append(s);
                wholeInput.append(" ");
            }
            // System.err.println(wholeInput);
        } catch (IOException e) {
            System.out.println("full dolbaeb IOException: " + e.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("full dolbaeb IOException: " + e.getMessage());
            }
        }
        String ans = wholeInput.toString().toLowerCase();
        for (int i = 0; i < ans.length(); i++) {
            if (!(Character.isLetter(ans.charAt(i)) || Character.getType(ans.charAt(i)) == Character.DASH_PUNCTUATION || ans.charAt(i) == '\''))
                continue;
            for (int j = i + 1; j < ans.length(); j++) {
                if (!(Character.getType(ans.charAt(j)) == Character.DASH_PUNCTUATION || ans.charAt(j) == '\'' || Character.isLetter(ans.charAt(j)))) {
                    if (map.get(ans.substring(i, j)) == null) {
                        map.put(ans.substring(i, j), 1);
                        v.add(ans.substring(i, j));
                    } else {
                        map.put(ans.substring(i, j), map.get(ans.substring(i, j)) + 1);
                    }
                    i = j;
                    // System.err.println(j);
                    break;
                }
            }
        }//abcdefgh 
        // [del * 4] + 8 < del * 8
        // System.out.println(111);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut), "utf8"));

            for (String i : v) {
                // System.out.println(i);
                if (map.get(i) != null && i.length() > 0) {
                    out.write(i + " " + map.get(i));
                    out.newLine();
                    map.put(i, null);
                    // System.out.println("down");
                }
            }
        } catch (IOException e) {
            System.out.println("dolbaeb" + e.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println("dolban" + e.getMessage());
            }
        }
    }
}
