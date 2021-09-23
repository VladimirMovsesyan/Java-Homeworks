import java.util.Scanner;

public class ReverseTranspose {

    public static int[] parseFromString(String t) {
        int countOfDigits = 0, currdigit = 0;
        t = t + ' ';
        for (int i = 1; i < t.length(); i++) {
            if (!Character.isWhitespace(t.charAt(i - 1)) && Character.isWhitespace(t.charAt(i))) {
                countOfDigits++;
            }
        }
        int[] a = new int[countOfDigits];
        Scanner q = new Scanner(t);

        while (q.hasNextInt()) {
            a[currdigit++] = q.nextInt();
        }
        q.close();

        return a;
    }

    public static void main(String[] args) {
        int[][] reversedAnswer = new int[(int)1e6][];
        int len = 0, mx = Integer.MIN_VALUE;
        Scanner s = new Scanner(System.in);

        while (s.hasNextLine()) {
            reversedAnswer[len++] = parseFromString(s.nextLine());
            mx = Math.max(mx, reversedAnswer[len - 1].length);
        }
        s.close();
        
        for (int j = 0; j < mx; j++) {
            for (int i = 0; i < len; i++) {
                if (j < reversedAnswer[i].length)
                    System.out.print(reversedAnswer[i][j] + " ");
            }
            System.out.println();
        }
    }
}
/*
1 2 3
4 5 6
7 8 9

1 4 7
2 5 8
3 6 9

0 0
1 0
0 1
*/