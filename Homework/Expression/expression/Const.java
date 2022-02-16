package expression;

public class Const implements TripleExpresion {
    String value;
    boolean negateFlag = false;

    public Const(int val) {
        value = Integer.toString(val);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(value) + (isNegate() ? 1 : 0);
    }

    @Override
    public int evaluate(int x) {
        return Integer.parseInt((isNegate() ? "-" : "") + value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.parseInt((isNegate() ? "-" : "") + value);
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

    public boolean isNegate() {
        return negateFlag;
    }

    public void toNegate() {
        negateFlag = (isNegate() ? false : true);
    }
}