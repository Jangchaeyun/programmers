import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SixthQ {
    public String solution(String my_string, int num1, int num2) {
        List<String> list = new ArrayList<>(List.of(my_string.split("")));

        Collections.swap(list, num1, num2);

        String answer = String.join("", list);

        return answer;
    }
}