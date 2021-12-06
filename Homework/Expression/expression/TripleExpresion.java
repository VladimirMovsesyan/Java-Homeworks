package expression;

public interface TripleExpresion extends TripleExpression, Expresion {
    @Override
    public int evaluate(int x, int y, int z);
}