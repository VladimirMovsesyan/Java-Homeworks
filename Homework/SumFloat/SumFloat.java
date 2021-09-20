public class SumFloat {

    public static float sumOfString(String s) {
        s = ' ' + s + ' ';
        //s = s.toLowerCase();
        float result = 0;
        for (int i = 1; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                int j = i;
                while (!Character.isWhitespace(s.charAt(j))) {
                    j++;
                }
                result += Float.parseFloat(s.substring(i, j));

                i = j;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        float answer = 0;
        for (String s : args)
            answer += sumOfString(s);
        System.out.println(answer);
    }
}