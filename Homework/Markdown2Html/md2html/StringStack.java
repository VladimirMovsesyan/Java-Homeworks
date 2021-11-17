package md2html;

import java.util.Arrays;

public class StringStack {
    String[] s;
    int head;
    int[] pos;

    public StringStack() {
        s = new String[32];
        pos = new int[32];
        head = 0;
    }

    public void push(String str, int position) {
        s[head++] = str;
        pos[head - 1] = position;
        if (head == pos.length) {
            Arrays.copyOf(pos, head * 2);
            Arrays.copyOf(s, head * 2);
        }
    }

    public void pop() {
        --head;
        if (head * 2 == pos.length) {
            Arrays.copyOf(pos, head);
            Arrays.copyOf(s, head);
        }
    }

    public String topString() {
        return s[head - 1];
    }

    public int topPos() {
        return pos[head - 1];
    }

    public int getSize() {
        return head;
    }

    public boolean empty() {
        return head == 0;
    }
}