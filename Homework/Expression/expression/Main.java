package expression;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            int x = Integer.parseInt(args[0]);
            System.out.println(x * x - 2 * x + 1);
        }
    }
}