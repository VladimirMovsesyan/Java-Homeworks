import java.util.Scanner;

public class Sum {
    
    public static void main (String[] args) {
        int sum = 0;
        for (String i : args) {
            Scanner s = new Scanner(i);
            for (; s.hasNextInt(); ) {
                sum += s.nextInt();
            }
            s.close();
        }
        System.out.println(sum);
    }
}
