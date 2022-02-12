package expression.parser;

import java.util.Arrays;

public class StackOperation {
    private char[] arr = new char[10];
    private int head = 0;

    public void push(char x) {
        arr[head++] = x;
        if (head >= arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }

    public char pop() {
        return arr[--head];
    }

    public char top() {
        return arr[head - 1];
    }

    public boolean isEmpty() {
        return head == 0;
    }

    public int length() {
        return head;
    }
}
