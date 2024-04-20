import java.util.ArrayList;
import java.util.List;

public class FirstQ {
    public List solution(int[] num_list, int n) {
        List<Integer> answer = new ArrayList<>();
        for (int i = n - 1; i < num_list.length; i++) {
            answer.add(num_list[i]);
        }
        return answer;
    }
}