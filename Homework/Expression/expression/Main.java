package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            int x = Integer.parseInt(args[0]);
            System.out.println(x * x - 2 * x + 1);
        }

        System.out.println(new Subtract(
            new Multiply(
                new Const(2),
                new Variable("x")
            ),
            new Const(3)
        ).toString());

        ExpressionParser ep = new ExpressionParser();

        System.out.println(ep.parse("x * (x - 2)*x + 1").evaluate(1, 0, 0));
    }
}