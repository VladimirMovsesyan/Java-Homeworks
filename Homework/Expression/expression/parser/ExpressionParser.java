package expression.parser;

import expression.Add;
import expression.Const;
import expression.Divide;
import expression.Multiply;
import expression.Negate;
import expression.Subtract;
import expression.TripleExpresion;
import expression.TripleExpression;
import expression.Variable;

public class ExpressionParser implements Parser {

    @Override
    public TripleExpression parse(String expression) {
        System.err.println(expression);
        return parser(expression);
    }

    public TripleExpresion parser(String expression) {
        StackExpr stackExp = new StackExpr();
        StackOperation signs = new StackOperation();
        boolean isSignAdded = true;

        for (int i = 0; i < expression.length();) {
            while (i < expression.length() && Character.isWhitespace(expression.charAt(i))) {
                i++;
            }

            if (Character.isDigit(expression.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i++));
                }
                stackExp.push(new Const(Integer.parseInt(sb.toString())));
                isSignAdded = false;
            } else if (Character.isAlphabetic(expression.charAt(i))) {
                stackExp.push(new Variable(Character.toString(expression.charAt(i++))));
                isSignAdded = false;
            } else if (expression.charAt(i) == '(') {
                StringBuilder sb = new StringBuilder();
                int balance = 1;
                i++;

                while (balance != 0) {
                    if (expression.charAt(i) == '(') {
                        balance++;
                    } else if (expression.charAt(i) == ')') {
                        balance--;
                    }
                    if (balance != 0) {
                        sb.append(expression.charAt(i++));
                    }
                }
                i++;
                if (sb.length() > 0) {
                    stackExp.push(parser(sb.toString()));
                }
                isSignAdded = false;
            } else {
                if (isSignAdded && expression.charAt(i) == '-') {
                    signs.push('n');
                    i++;
                } else {
                    signs.push(expression.charAt(i++));
                }
                isSignAdded = true;
            }

            while (!isSignAdded && !signs.isEmpty() && signs.top() == 'n') {
                signs.pop();
                stackExp.push(new Negate(stackExp.pop()));
            }

            while (!isSignAdded && !signs.isEmpty() && getOperationPriority(signs.top()) == 1) {
                TripleExpresion secondExpresion = stackExp.pop();
                TripleExpresion firstExpresion = stackExp.pop();
                char sign = signs.pop();
                stackExp.push(combainExpressions(firstExpresion, secondExpresion, sign));
            }
        }

        while (!signs.isEmpty()) {
            if (signs.top() == 'n') {
                signs.pop();
                stackExp.push(new Negate(stackExp.pop()));
                continue;
            }
            TripleExpresion secondExpresion = stackExp.pop();
            TripleExpresion firstExpresion = stackExp.pop();
            char sign = signs.pop();
            stackExp.push(combainExpressions(firstExpresion, secondExpresion, sign));
        }

        return stackExp.pop();
    }

    public int getOperationPriority(char x) {
        switch (x) {
            case '*', '/':
                return 1;
            default:
                return 0;
        }
    }

    public TripleExpresion combainExpressions(TripleExpresion firstExpresion, TripleExpresion secondExpresion, char sign) {
        switch (sign) {
            case '+':
                return new Add(firstExpresion, secondExpresion);
            case '*':
                return new Multiply(firstExpresion, secondExpresion);
            case '-':
                return new Subtract(firstExpresion, secondExpresion);
            default:
                return new Divide(firstExpresion, secondExpresion);
        }
    }
    
}
