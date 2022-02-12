package expression.parser;

import expression.Add;
import expression.Const;
import expression.Divide;
import expression.Multiply;
import expression.Subtract;
import expression.TripleExpresion;
import expression.TripleExpression;
import expression.Variable;

public class ExpressionParser implements Parser {

    @Override
    public TripleExpression parse(String expression) {
        return parser(expression);
    }

    public TripleExpresion parser(String expression) {
        StackExpr stackExp = new StackExpr();
        StackOperation signs = new StackOperation();


        for (int i = 0; i < expression.length();) {
            boolean isSignAdded = true;
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

                while (balance != 0 && expression.charAt(i) != ')') {
                    if (expression.charAt(i) == '(') {
                        balance++;
                    } else if (expression.charAt(i) == ')') {
                        balance--;
                    }
                    sb.append(expression.charAt(i++));
                }
                System.out.println(sb);
                if (sb.length() > 0) {
                    stackExp.push(parser(sb.toString()));
                }
            } else {
                // if (isSignAdded && expression.charAt(i) == '-') {
                //     signs.push('n');
                //     continue;
                // }
                signs.push(expression.charAt(i++));
                isSignAdded = true;
            }

            if (!isSignAdded && !signs.isEmpty() && getOperationPriority(signs.top()) == 1) {
                // while (signs.top() == 'n') {
                //     signs.pop();
                //     stackExp.push(new Const(-stackExp.pop().evaluate(0, 0, 0)));
                // }
            TripleExpresion secondExpresion = stackExp.pop();
            TripleExpresion firstExpresion = stackExp.pop();
            char sign = signs.pop();
            stackExp.push(combainExpressions(firstExpresion, secondExpresion, sign));
            }
        }

        while (!signs.isEmpty()) {
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
