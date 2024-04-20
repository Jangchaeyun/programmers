import java.util.ArrayList;

public class FourthQ {
    public int[] solution(int n) {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(n);
        while (n != 1) { // n이 1이 아닌 동안
            if (n % 2 == 0) { // n이 짝수면
                n /= 2;
            } else { // n이 홀수면
                n = 3 * n + 1;
            }
            answer.add(n);
        }
        return answer.stream().mapToInt(i->i).toArray();
    }
}