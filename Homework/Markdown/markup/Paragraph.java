package markup;
import java.util.*;

public class Paragraph implements Tag {
    StringBuilder sb;

    Paragraph(List<Tag> ls) {
        sb = new StringBuilder();
        for (var i : ls) {
            sb.append(i);
        }
    }

    public void toMarkdown(StringBuilder q) {
        q.append(sb);
    }
}