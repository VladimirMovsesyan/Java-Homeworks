package markup;

public class Text implements Tag {
    StringBuilder sb;

    Text(String s) {
        sb = new StringBuilder(s);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}