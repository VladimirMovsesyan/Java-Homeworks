package expression;

public interface Expresion extends Expression {
    public String getValue();
    @Override
    public int hashCode();
    public String toString();
    public int evaluate(int x);
    @Override
    boolean equals(Object obj);
}