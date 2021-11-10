package markup;

import java.util.List;

public class Emphasis extends ModifyElement {
    Emphasis(List<Tag> ls) {
        super(ls);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        super.toMarkdown(sb);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        super.toHtml(sb);
    }

    @Override
    public String getMarkdown() {
        return "*";
    }

    @Override
    public String getHtmlLeft() {
        return "<em>";
    }

    @Override
    public String getHtmlRight() {
        return "</em>";
    }
}
