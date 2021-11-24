package md2html;

import java.util.Map;

public class Part {
    private StringBuilder sb;
    private int index;
    private StringStack stack;
    final private Map<String, String> closemp = Map.of(
        "`", "</code>",
        "*", "</em>",
        "_", "</em>",
        "**", "</strong>",
        "__", "</strong>",
        "'", "'",
        "--", "</s>",
        "''", "</q>"
    );

    final private Map<String, String> openmp = Map.of(
        "</code>", "<code>",
        "</em>", "<em>",
        "</strong>", "<strong>",
        "'", "'",
        "</s>", "<s>",
        "</q>", "<q>"
    );

    final private Map<String, String> specs = Map.of(
        "<", "&lt;",
        ">", "&gt;",
        "&", "&amp;"
    );

    public Part(StringBuilder sb) {
        this.sb = sb;
        index = 0;
        stack = new StringStack();
    }

    private int moveForward() {
        while (index < sb.length() && sb.charAt(index) == '#') {
            index++;
        }
        return (sb.charAt(index) == ' ' ? index : (index = 0));
    }

    private void getStartIndex() {
        while (index < sb.length() && (sb.charAt(index) == '#' || Character.isWhitespace(sb.charAt(index)))) {
            index++;
        }
    }

    private void updatePairs(StringBuilder temp, int ind, String tag, int len) {
        if (!stack.empty() && stack.topString().equals(tag)) {
            String htmlSym = closemp.get(stack.topString());
            temp.append(htmlSym);
            htmlSym = openmp.get(htmlSym);
            temp.replace(stack.topPos(), stack.topPos() + len, htmlSym);
            stack.pop();
        } else {
            stack.push(tag, temp.length());
            temp.append(tag);
        }
    }

    private StringBuilder replacePairs() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            String oneTag = Character.toString(sb.charAt(i));
            String twoTag = new String();
            if (i + 1 < sb.length()) {
                twoTag = Character.toString(sb.charAt(i)) + Character.toString(sb.charAt(i + 1));
            }
            if (closemp.containsKey(oneTag) || (i + 1 < sb.length() && closemp.containsKey(twoTag))) {
                if (i > 0 && sb.charAt(i - 1) == '\\') {
                    temp.setLength(temp.length() - 1);
                    temp.append(sb.charAt(i));
                    continue;
                }
                if (i + 1 < sb.length() && closemp.containsKey(twoTag)) {
                    updatePairs(temp, i, twoTag, 2);
                    i++;
                } else if (closemp.containsKey(oneTag)) {
                    updatePairs(temp, i, oneTag, 1);
                }
            } else {
                temp.append(sb.charAt(i));
            }
        }
        return temp;
    }

    private StringBuilder replaceSpecs() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (specs.containsKey(Character.toString(sb.charAt(i)))) {
                temp.append(specs.get(Character.toString(sb.charAt(i))));
            } else {
                temp.append(sb.charAt(i));
            }
        }
        return temp;
    }

    private StringBuilder getTag(int ind, int level) {
        StringBuilder temp = new StringBuilder();
        if (ind == 0) {
            temp.append("<p>");
        } else {
            temp.append("<h").append(level).append(">");
        }
        for (int i = ind; i < sb.length() - 1; i++) {
            temp.append(sb.charAt(i));
        }
        if (ind == 0) {
            temp.append("</p>\n");
        } else {
            temp.append("</h").append(level).append(">\n");
        }
        return temp;
    }

    public String modify() {
        sb = replaceSpecs();
        int lvl = moveForward();
        if (lvl != 0) {
            getStartIndex();
        }
        sb = getTag(index, lvl);
        sb = replacePairs();

        return sb.toString();
    }
}
