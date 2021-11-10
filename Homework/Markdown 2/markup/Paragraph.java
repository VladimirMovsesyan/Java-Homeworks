package markup;

import java.util.List;

public class Paragraph implements Tag {

    List<Tag> ls;

    Paragraph(List<Tag> ls) {
        this.ls = ls;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (var i : ls) {
            i.toMarkdown(sb);
        }
    }

    @Override
    public void toHtml(StringBuilder sb) {
        for (var i : ls) {
            i.toHtml(sb);
        }
    }
}
