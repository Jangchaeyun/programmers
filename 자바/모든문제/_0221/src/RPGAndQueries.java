import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RPGAndQueries {
    public int[] solution(int n, int z, int[][] roads, long[] queries) {
        int[] result = new int[queries.length];
        int[][] graph = new int[n][n];
        long[][] dp = new long[n][z + 1];
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            adjList[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];
            graph[u][v] = w;
            adjList[u].add(v);
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }

        dp[0][0] = 0;

        for (int money = 0; money <= z; money++) {
            for (int curCity = 0; curCity < n; curCity++) {
                if (dp[curCity][money] == Long.MAX_VALUE) continue;
                for (int nextCity : adjList[curCity]) {
                    if (money + graph[curCity][nextCity] <= z && dp[nextCity][money + graph[curCity][nextCity]] > dp[curCity][money] + 1) {
                        dp[nextCity][money + graph[curCity][nextCity]] = dp[curCity][money] + 1;
                    }
                }
                if (money + z <= z && dp[curCity][money] + 1 < dp[curCity][money + z]) {
                    dp[curCity][money + z] = dp[curCity][money] + 1;
                }
            }
        }

        for (int i = 0; i < queries.length; i++) {
            long query = queries[i];
            result[i] = -1;
            for (int money = 0; money <= z; money++) {
                if (dp[n - 1][money] <= query) {
                    result[i] = (int) Math.min(result[i], dp[n - 1][money]);
                    break;
                }
            }
        }

        return result;
    }
}
