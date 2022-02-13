package expression;

public class Negate extends Operation {

    public Negate(TripleExpresion a) {
        super(a);
    }

    @Override
    public String toString() {
        return "-" + super.a.toString();
    }
    @Override
    public int hashCode() {
        return super.hashCode() + 31;
    }

    @Override
    public int evaluate(int x) {
        return -(super.a.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -(super.a.evaluate(x, y, z));
    }

    @Override
    public String getValue() {
        return super.value;
    }

    @Override
    public String getSign() {
        return " - ";
    }
}
