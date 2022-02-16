package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            int x = Integer.parseInt(args[0]);
            System.out.println(x * x - 2 * x + 1);
        }

        // System.out.println(new Subtract(
        //     new Multiply(
        //         new Const(2),
        //         new Variable("x")
        //     ),
        //     new Const(3)
        // ).toString());

        ExpressionParser ep = new ExpressionParser();

        String temp = "(y + (x - (-2147483648 + (x + x))))";
        int x = -1534516706;
        int y = -816864994;

        System.out.println((y + (x - (-2147483648 + (x + x)))));

        System.out.println(ep.parse(temp).evaluate(-1534516706, -816864994, 1077556082));
        System.out.println(ep.parse(temp).toString());
    }
}