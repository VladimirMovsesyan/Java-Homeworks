package markup;

import java.util.List;

public class Strong extends ModifyElement {
    Strong(List<Tag> ls) {
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
        return "__";
    }

    @Override
    public String getHtmlLeft() {
        return "<strong>";
    }

    @Override
    public String getHtmlRight() {
        return "</strong>";
    }
}
