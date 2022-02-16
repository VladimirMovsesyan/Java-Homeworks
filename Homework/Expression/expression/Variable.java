package expression;

public class Variable implements TripleExpresion {
    String var;

    public Variable(String v) {
        var = v;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public int hashCode() {
        return var.hashCode() * var.length() + 1;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (var) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new AssertionError();
        }
    }

	@Override
	public String getValue() {
		return var;
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return var.equals(obj.toString());
    }

    @Override
    public void toNegate() {
        return;
    }
}