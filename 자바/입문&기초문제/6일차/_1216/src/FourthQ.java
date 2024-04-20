import java.util.Arrays;

public class FourthQ {
    public String[] solution(String my_string) {
        int length = my_string.length();
        String[] suffixes = new String[length];

        for (int i = 0; i < length; i++) {
            suffixes[i] = my_string.substring(i);
        }

        Arrays.sort(suffixes);

        return suffixes;
    }
}
