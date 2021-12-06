package expression;

public abstract class Operation implements TripleExpresion {
    String value;
    TripleExpresion a;
    TripleExpresion b;
    boolean isvar = false;

    public Operation(TripleExpresion a, TripleExpresion b) {
        this.a = a;
        this.b = b;
        value = "(" + a.getValue() + getSign() + b.getValue() + ")";
    }

    @Override
    public String toString() {
        return "(" + a.getValue() + getSign() + b.getValue() + ")";
    }

    @Override
    public int hashCode() {
        return ((7727 + a.hashCode()) * getSign().charAt(1)) * 7727 + b.hashCode() + getClass().hashCode();
    }

    @Override
    public int evaluate(int x) {
        switch (getSign()) {
            case " + ":
                return a.evaluate(x) + b.evaluate(x);
            case " - ":
                return a.evaluate(x) - b.evaluate(x);
            case " * ":
                return a.evaluate(x) * b.evaluate(x);
            case " / ":
                return a.evaluate(x) / b.evaluate(x);
            default:
                return 0;
        }
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (getSign()) {
            case " + ":
                return a.evaluate(x, y, z) + b.evaluate(x, y, z);
            case " - ":
                return a.evaluate(x, y, z) - b.evaluate(x, y, z);
            case " * ":
                return a.evaluate(x, y, z) * b.evaluate(x, y, z);
            case " / ":
                return a.evaluate(x, y, z) / b.evaluate(x, y, z);
            default:
                return 0;
        }
    }

    public abstract String getSign();

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }
}