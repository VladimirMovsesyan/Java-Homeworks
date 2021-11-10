package markup;

public class Text implements Tag {
    private String s;
    Text(String text) {
        s = text;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(s);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(s);
    }
}
