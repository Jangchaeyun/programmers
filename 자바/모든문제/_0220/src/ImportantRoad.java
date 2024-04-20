import java.util.*;

public class ImportantRoad {
    static class Road {
        int node;
        int length;
        int traffic;
        int time;

        public Road(int node, int length, int traffic) {
            this.node = node;
            this.length = length;
            this.traffic = traffic;
            this.time = time;
        }
    }

    private int[] dijkstra(int n, List<List<Road>> graph) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
        pq.offer(new Road(1, 0, 0));

        while (!pq.isEmpty()) {
            Road current = pq.poll();
            int node = current.node;
            int time = current.time;
            if (distance[node] < time) continue;

            distance[node] = time;

            for (Road next : graph.get(node)) {
                int nextNode = next.node;
                int nextTime = time + next.length + next.traffic;
                if (distance[nextNode] > nextTime) {
                    pq.offer(new Road(nextNode, nextTime, 0));
                }
            }
        }
        return distance;
    }

    public int[] solution(int n, int[][] roads) {
        List<List<Road>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int length = road[2];
            int traffic = road[3];
            graph.get(u).add(new Road(v, length, traffic));
            graph.get(v).add(new Road(u, length, traffic));
        }

        int[] distances = dijkstra(n, graph);

        List<Integer> result = new ArrayList<>();
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int trafficChange = road[0];
            int newLength = distances[u] + road[2] + trafficChange;
            if (distances[v] > newLength) {
                result.add(Math.max(u, v));
            }
        }
        if (result.isEmpty()) {
            return new int[] { - 1 };
        } else {
            Collections.sort(result);
            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
