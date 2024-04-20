import java.util.PriorityQueue;

public class FirstQ {
    public int solution(int[] scoville, int K) {
        Integer answer = 0;
        Integer one, two;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        for (int i = 0; i < scoville.length; i++) {
            queue.add(scoville[i]);
        }

        while (queue.peek() < K) {
            if (queue.size() < 2) {
                return -1;
            }
            else {
                one = queue.poll();
                two = queue.poll();

                one += (two * 2);
                queue.offer(one);
                answer += 1;
            }
        }
        return answer;
    }
}