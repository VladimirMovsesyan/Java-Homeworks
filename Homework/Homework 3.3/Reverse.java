import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arrayOfIntegers = new int[(int)1e6];
        int[] intgersInLine = new int[(int)1e6];
        int curPos = -1, curLine = -1;

        while (scan.hasNextLine()) {
            String patternForScan = scan.nextLine();
            Scanner scan2 = new Scanner(patternForScan);
            int cntCurLine = 0;
            for (; scan2.hasNextInt(); cntCurLine++) {
                arrayOfIntegers[++curPos] = scan2.nextInt();
            }
            scan2.close();
            intgersInLine[++curLine] = cntCurLine;
        }
        scan.close();

        for (; curLine >= 0;) {
            if (intgersInLine[curLine] > 0) {
                System.out.print(arrayOfIntegers[curPos--]);
                intgersInLine[curLine]--;
            }
            if (intgersInLine[curLine] == 0) {
                System.out.println();
                curLine--;
            } else {
                System.out.print(" ");
            }
        }
    }    
}
