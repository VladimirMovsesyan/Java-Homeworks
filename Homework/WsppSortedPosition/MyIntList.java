import java.util.Arrays;

public class MyIntList {
    private int size;
    private int[] arr;

    MyIntList() {
        size = 1;
        arr = new int[1];
    }

    public void add(int x) {
        size++;
        if (size > arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[size - 1] = x;
        arr[0]++;
    }

    public void addPair(int x, int y) {
        add(x);
        add(y);
        arr[0]--;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(arr[0]);
        for (int i = 1; i < size; i++) {
            res.append((i % 2 == 0 ? ":" : " "));
            res.append(Integer.toString(arr[i]));
        }
        return res.toString();
    }
}
