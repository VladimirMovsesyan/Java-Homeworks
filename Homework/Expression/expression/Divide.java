package expression;

public class Divide extends Operation {

    public Divide(TripleExpresion a, TripleExpresion b) {
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
    public String getValue() {
        return super.value;
    }
    @Override
    public String getSign() {
        return " / ";
    }
}