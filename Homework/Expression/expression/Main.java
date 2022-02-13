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

        String temp = "((4))";

        System.out.println(ep.parse(temp).evaluate(2, 1, 0));
        System.out.println(ep.parse(temp).toString());
    }
}