import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Caving {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] before;
    static int[] save;
    static boolean[] visited;
    public boolean solution(int n, int[][] path, int[][] order) {
        graph = new ArrayList<>();
        before = new int[n];
        save = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < path.length; i++) {
            graph.get(path[i][0]).add(path[i][1]);
            graph.get(path[i][1]).add(path[i][0]);
        }

        for (int i = 0; i < order.length; i++) {
            before[order[i][1]] = order[i][0];
        }

        if (before[0] != 0) {
            return false;
        }

        Queue<Integer> q = new LinkedList<>();
        visited[0] = true;
        for (int node : graph.get(0)) {
            q.offer(node);
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            if (visited[now]) {
                continue;
            }

            if (!visited[before[now]]) {
                save[before[now]] = now;
                continue;
            }

            visited[now] = true;
            for (int node : graph.get(now)) {
                q.offer(node);
            }

            q.offer(save[now]);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}