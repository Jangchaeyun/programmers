import java.util.Arrays;
import java.util.Collections;

public class SeventhQ {
    public String solution(String s) {
        String answer = "";

        String[] str = s.split("");
        Arrays.sort(str, Collections.reverseOrder());

        for (String a : str)
            answer += a;

        return answer;
    }
}
