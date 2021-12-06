package expression;

public class Const implements TripleExpresion {
    int value;

    public Const(int val) {
        value = val;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

	@Override
	public String getValue() {
		return toString();
	}

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }
}