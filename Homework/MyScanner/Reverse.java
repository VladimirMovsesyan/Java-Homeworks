import java.io.IOException;

public class Reverse {

    public static int[] parseFromString(String t) throws IOException {
        int countOfDigits = 0, currdigit = 0;
        t = t + ' ';
        for (int i = 1; i < t.length(); i++) {
            if (!Character.isWhitespace(t.charAt(i - 1)) && Character.isWhitespace(t.charAt(i))) {
                countOfDigits++;
            }
        }
        int[] a = new int[countOfDigits];
        MyScanner q = new MyScanner(t);

        while (q.hasNextInt()) {
            int test = q.nextInt();
            a[currdigit++] = test;
        }
        q.close();

        return a;
    }

    public static void main(String[] args) throws IOException {
        int[][] reversedAnswer = new int[(int)1e6][];
        int len = 0;
        MyScanner s = new MyScanner(System.in);
        
        while (s.hasNextLine()) {
            reversedAnswer[len++] = parseFromString(s.nextLine());
        }
        s.close();

        for (int i = len - 1; i >= 0; i--) {
            for (int j = reversedAnswer[i].length - 1; j >= 0; j--) {
                System.out.print(reversedAnswer[i][j] + " ");
            }
            System.out.println();
        }
    }
}