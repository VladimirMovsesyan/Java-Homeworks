import java.util.Scanner;

public class Reverse {

    public static void reverseStringInts(String q) {
        Scanner ss = new Scanner(q);
        int[] reversedStringInts = new int[1000];
        int cntOfIntsInString = 0;
        for (; ss.hasNextInt(); cntOfIntsInString++) {
            reversedStringInts[cntOfIntsInString] = ss.nextInt();
        }
        ss.close();
        for (int i = cntOfIntsInString - 1; i > -1; i--) {
            System.out.print(reversedStringInts[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int cnt = 0;
        String[] temp = new String[1000];
        Scanner s = new Scanner(System.in);
        for (; s.hasNextLine(); cnt++) {
            temp[cnt] = s.nextLine();
        }
        s.close();
        for (int i = cnt - 1; i > -1; i--) {
            reverseStringInts(temp[i]);
        }
    }
}
