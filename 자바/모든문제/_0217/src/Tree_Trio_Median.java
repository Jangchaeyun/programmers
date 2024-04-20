import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree_Trio_Median {

    static public int solution(int n, int[][] edges) {
        List<Integer>[] list = new ArrayList[n+1];
        for(int i=1; i<=n; i++) list[i] = new ArrayList<Integer>();
        for(int[] edge : edges) {
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }

        int start = 1, cnt = 0;
        int[] result = bfs(list, start, n);
        for(int i=2; i<=n; i++)
            if(result[i]>result[start]) start = i;

        cnt = 0;
        result = bfs(list, start, n);
        for(int i=1; i<=n; i++)
            if(result[i]>result[start]) start = i;
        for(int i=1; i<=n; i++) if(result[start]==result[i]) cnt++;
        if(cnt>=2) return result[start];

        cnt = 0;
        result = bfs(list, start, n);
        for(int i=1; i<=n; i++) if(result[start]==result[i]) cnt++;
        if(cnt>=2) return result[start];
        return result[start]-1;
    }

    static int[] bfs(List<Integer>[] list, int start, int n) {
        boolean[] visit = new boolean[n+1];
        int[] dist = new int[n+1];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        visit[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for(int i : list[now]) {
                if(visit[i]) continue;
                visit[i] = true;
                queue.add(i);
                dist[i] = dist[now] + 1;
            }
        }
        return dist;
    }
}
