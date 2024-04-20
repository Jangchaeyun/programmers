import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MazeDrivingTest {
    private Set<String> getCandidatePosition(int n, int m, int[][] tests) {
        Set<String> candidates = new HashSet<>();
        for (int[] test : tests) {
            int x = test[0];
            int y = test[1];
            int d = test[2];

            for (int i = Math.max(0, x - d); i <= Math.min(n, x + d); i++) {
                for (int j = Math.max(0, y - (d - Math.abs(x - i))); j <= Math.min(m, y + (d - Math.abs(x - i))); j++) {
                    candidates.add(i + "," + j);
                }
            }
        }
        return candidates;
    }

    public long solution(int n, int m, int[][] tests) {
        Set<String> candidatePositions = getCandidatePosition(n, m, tests);

        for (int[] test : tests) {
            int x = test[0];
            int y = test[1];
            int d = test[2];
            int flag = test[3];

            if (flag == 1) {
                Iterator<String> iterator = candidatePositions.iterator();
                while (iterator.hasNext()) {
                    String position = iterator.next();
                    String[] parts = position.split(",");
                    int candidateX = Integer.parseInt(parts[0]);
                    int candidateY = Integer.parseInt(parts[1]);
                    int distance = Math.abs(candidateX - x) + Math.abs(candidateY - y);
                    if (distance > d) {
                        iterator.remove();
                    }
                }
            }
        }
        return candidatePositions.size();
    }
}
