import java.util.*;

public class TerrainMovement {
    int[][] groups;
    int[][] land;
    int n, height;
    ArrayList<Edge> edges;

    class Edge implements Comparable<Edge> {
        int big_g, small_g, cost;
        public Edge(int g1, int g2, int cost) {
            if (g1 > g2) {
                this.big_g = g1;
                this.small_g = g2;
            } else {
                this.big_g = g2;
                this.small_g = g1;
            }
            this.cost = cost;
        }


        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }

        public void show() {
            System.out.println(big_g + ", " + small_g + ": " + cost);
        }
    }

    int MST(int group_cnt) {
        int[] roots = new int[group_cnt+1];
        for(int i = 0; i < group_cnt+1; i++)
            roots[i] = i;

        int idx = 0, edge_cnt = 0, cost = 0;
        while(edge_cnt < group_cnt-1){
            Edge e = edges.get(idx);
            if(roots[e.big_g] != roots[e.small_g]){
                int old_root = roots[e.small_g];
                for(int i = 0; i < roots.length; i++){
                    if(roots[i] == old_root)
                        roots[i] = roots[e.big_g];
                }
                cost += e.cost;
                edge_cnt++;
            }
            idx++;
        }
        return cost;
    }

    void checking() {
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i+1 < n && groups[i+1][j] != groups[i][j]){
                    edges.add(new Edge(groups[i][j], groups[i+1][j], Math.abs(land[i][j] - land[i+1][j])));
                }
                if(i-1 >= 0 && groups[i-1][j] != groups[i][j]){
                    edges.add(new Edge(groups[i][j], groups[i-1][j], Math.abs(land[i][j] - land[i-1][j])));
                }
                if(j+1 < n && groups[i][j+1] != groups[i][j]){
                    edges.add(new Edge(groups[i][j], groups[i][j+1], Math.abs(land[i][j] - land[i][j+1])));
                }
                if(j-1 >= 0 && groups[i][j-1] != groups[i][j]){
                    edges.add(new Edge(groups[i][j], groups[i][j-1], Math.abs(land[i][j] - land[i][j-1])));
                }
            }
        }
    }

    void coloring(int group_cnt, int[] pos) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(pos);
        this.groups[pos[0]][pos[1]] = group_cnt;
        while(!queue.isEmpty()){
            int[] popped = queue.poll();
            int i = popped[0], j = popped[1];
            if(i+1 < n && groups[i+1][j] == 0 && Math.abs(land[i+1][j] - land[i][j]) <= height){
                this.groups[i+1][j] = group_cnt;
                queue.add(new int[]{i+1,j});
            }
            if(i-1 >= 0 && groups[i-1][j] == 0 && Math.abs(land[i-1][j] - land[i][j]) <= height){
                this.groups[i-1][j] = group_cnt;
                queue.add(new int[]{i-1,j});
            }
            if(j+1 < n && groups[i][j+1] == 0 && Math.abs(land[i][j+1] - land[i][j]) <= height){
                this.groups[i][j+1] = group_cnt;
                queue.add(new int[]{i,j+1});
            }
            if(j-1 >= 0 && groups[i][j-1] == 0 && Math.abs(land[i][j-1] - land[i][j]) <= height){
                this.groups[i][j-1] = group_cnt;
                queue.add(new int[]{i,j-1});
            }
        }
    }

    public int solution(int[][] land, int height) {
        this.n = land.length;
        this.height = height;
        this.groups = new int[n][n];
        this.land = land;

        int group_cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (groups[i][j] == 0) {
                    group_cnt++;
                    coloring(group_cnt, new int[] { i, j });
                }
            }
        }

        this.edges = new ArrayList<>();

        checking();

        Collections.sort(edges);

        return MST(group_cnt);
    }
}
