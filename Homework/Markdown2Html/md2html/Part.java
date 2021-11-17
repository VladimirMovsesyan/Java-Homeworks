package md2html;

import java.util.Map;

public class Part {
    private StringBuilder sb;
    private int index;
    private StringStack stack;
    final private Map<String, String> mp = Map.of(
        "`", "</code>",
        "*", "</em>",
        "_", "</em>",
        "**", "</strong>",
        "__", "</strong>",
        "'", "'",
        "--", "</s>",
        "''", "</q>"
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
        return (sb.charAt(index) == ' ' ? index : 0);
    }

    private void getStartIndex() {
        while (index < sb.length() && (sb.charAt(index) == '#' || Character.isWhitespace(sb.charAt(index)))) {
            index++;
        }
    }

    private void replacePairs() {
        for (int len = 2; len > 0; len--) {
            for (int i = 0; i + len < sb.length(); i++) {
                if (mp.containsKey(sb.substring(i, i + len))) {
                    if (i > 0 && sb.charAt(i - 1) == '\\') {
                        sb = new StringBuilder(sb.substring(0, i - 1) + sb.substring(i));
                        continue;
                    }
                    if (!stack.empty() && stack.topString().equals(sb.substring(i, i + len))) {
                        int oldSize = sb.length();
                        String htmlSym = mp.get(stack.topString());
                        sb = new StringBuilder(sb.substring(0, i) + htmlSym + sb.substring(i + len));

                        if (htmlSym.length() > 1) {
                            htmlSym = htmlSym.substring(0, 1) + htmlSym.substring(2);
                        }

                        sb = new StringBuilder(sb.substring(0, stack.topPos()) + 
                                                        htmlSym + sb.substring(stack.topPos() + len));

                        stack.pop();
                        i += htmlSym.length() - len - 1;
                    } else {
                        stack.push(sb.substring(i, i + len), i);
                    }
                }
            }
        }
    }

    private void replaceSpecs() {
        for (int i = 0; i < sb.length(); i++) {
            if (specs.containsKey(sb.substring(i, i + 1))) {
                String newSym = specs.get(sb.substring(i, i + 1));
                sb = new StringBuilder(sb.substring(0, i) + newSym + sb.substring(i + 1));
                i += newSym.length() - 2;
            }
        }
    }

    public String modify() {
        replaceSpecs();
        int temp = moveForward();
        if (temp != 0) {
            getStartIndex();
            sb = new StringBuilder("<h" + temp + ">" + sb.substring(index, sb.length() - 1) + "</h" + temp + ">" + "\n");
        } else {
            index = 0;
            sb = new StringBuilder("<p" + ">" + sb.substring(index, sb.length() - 1) + "</p" + ">" + "\n");
        }
        replacePairs();

        return sb.toString();
    }
}
