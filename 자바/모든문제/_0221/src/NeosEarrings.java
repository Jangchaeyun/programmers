import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class NeosEarrings {
    static final int MOD = 1_000_000_007;

    public int[] solution(int n, int[][] point, int[][] query) {
        int[] answer = new int[n + 1];

        long initialCount = calculateInitialCount(point);
        answer[0] = (int) (initialCount % MOD);

        TreeMap<Integer, Integer> xCounts = new TreeMap<>();
        TreeMap<Integer, Integer> yCounts = new TreeMap<>();

        for (int[] p : point) {
            increment(xCounts, p[0]);
            increment(yCounts, p[1]);
        }

        for (int i = 0; i < n; i++) {
            int[] q = query[i];
            if (q[0] == 0) {
                int[] newPoint = {q[1], q[2], q[3]};
                addPoint(point, newPoint);
                increment(xCounts, q[1]);
                increment(yCounts, q[2]);
            } else {
                int[] removedPoint = {q[1], q[2], 0};
                removePoint(point, removedPoint);
                decrement(xCounts, q[1]);
                decrement(yCounts, q[2]);
            }

            long count = calculateCount(xCounts) * calculateCount(yCounts) % MOD;
            answer[i + 1] = (int) (count % MOD);
        }

        return answer;
    }

    private long calculateInitialCount(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int n = points.length;
        long count = 1;
        for (int i = 1; i < n; i++) {
            int k = points[i][2] - points[i - 1][2];
            count = (count * k) % MOD;
        }
        return count;
    }

    private void addPoint(int[][] points, int[] newPoint) {
        int n = points.length;
        points = Arrays.copyOf(points, n + 1);
        points[n] = newPoint;
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
    }

    private void removePoint(int[][] points, int[] removedPoint) {
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (Arrays.equals(points[i], removedPoint)) {
                points[i] = points[n - 1];
                points = Arrays.copyOf(points, n - 1);
                break;
            }
        }
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
    }

    private void increment(TreeMap<Integer, Integer> counts, int key) {
        counts.put(key, counts.getOrDefault(key, 0) + 1);
    }

    private void decrement(TreeMap<Integer, Integer> counts, int key) {
        counts.put(key, counts.get(key) - 1);
        if (counts.get(key) == 0) {
            counts.remove(key);
        }
    }

    private long calculateCount(TreeMap<Integer, Integer> counts) {
        long count = 1;
        for (int c : counts.values()) {
            count = (count * c) % MOD;
        }
        return count;
    }
}
