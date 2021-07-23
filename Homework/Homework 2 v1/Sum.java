public class Sum {

    public static int sumOfString(String s) {
        s = " " + s + " ";
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int j = i;
                while (s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    j++;
                }
                result += ((s.charAt(i - 1) != '-') ? Integer.parseInt(s.substring(i, j)) : -Integer.parseInt(s.substring(i, j)));
                i = j;
            }
        }

        return result;
    }
    public static void main(String[] args) {
        args = new String[] {
            "1 2",
            " 3"
        };
        int answer = 0;
        for (String s : args) {
            answer += sumOfString(s);
        }        
        System.out.println(answer);
    }
}
