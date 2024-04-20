import java.util.*;

public class FastTravel {
    private int findNextTeleportTarget(List<List<Integer>> graph, boolean[] visited) {
        for (int i = 1; i < graph.size(); i++) {
            if (!visited[i]) {
                for (int neighbor : graph.get(i)) {
                    if (visited[neighbor]) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public int solution(int n, int[][] roads) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int start = road[0];
            int end = road[1];
            graph.get(start).add(end);
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        distances[1] = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (distances[neighbor] == Integer.MAX_VALUE) {
                    distances[neighbor] = distances[current] + 1;
                    queue.offer(neighbor);
                }
            }
        }

        int minTeleports = 0;
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (distances[i] == 1) {
                visited[i] = true;
                minTeleports++;
            }
        }
        while (true) {
            int teleportTarget = findNextTeleportTarget(graph, visited);
            if (teleportTarget == -1) break;
            minTeleports++;
            visited[teleportTarget] = true;
        }
        return minTeleports;
    }
}
