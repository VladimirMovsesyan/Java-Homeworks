package expression.parser;

import java.util.Arrays;

import expression.TripleExpresion;

public class StackExpr {
    private TripleExpresion[] arr = new TripleExpresion[10];
    private int head = 0;

    public void push(TripleExpresion x) {
        arr[head++] = x;
        if (head >= arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }

    public TripleExpresion pop() {
        return arr[--head];
    }

    public boolean isEmpty() {
        return head == 0;
    }

    public TripleExpresion top() {
        return arr[head - 1];
    }

    public int length() {
        return head;
    }
}
