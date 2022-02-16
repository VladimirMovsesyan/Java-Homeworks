package expression;

public class Multiply extends Operation {
    
    public Multiply(TripleExpresion a, TripleExpresion b) {
        super(a, b);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public String getSign() {
        return " * ";
    }

    @Override
    public String getValue() {
        return super.value;
    }

    @Override
    public void toNegate() {
        return;
    }
}