package markup;

import java.util.List;

public abstract class ModifyElement implements Tag {
    List<Tag> ls;

    ModifyElement(List<Tag> ls) {
        this.ls = ls;
    }

    public void toHtml(StringBuilder sb) {
        sb.append(getHtmlLeft());
        for (var i : ls) {
            i.toHtml(sb);
        }
        sb.append(getHtmlRight());
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(getMarkdown());
        for (var i : ls) {
            i.toMarkdown(sb);
        }
        sb.append(getMarkdown());
    }

    public abstract String getMarkdown();
    public abstract String getHtmlLeft();
    public abstract String getHtmlRight();
}