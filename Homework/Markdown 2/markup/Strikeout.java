package markup;

import java.util.List;

public class Strikeout extends ModifyElement {
    Strikeout(List<Tag> ls) {
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
        return "~";
    }

    @Override
    public String getHtmlLeft() {
        return "<s>";
    }

    @Override
    public String getHtmlRight() {
        return "</s>";
    }
}
