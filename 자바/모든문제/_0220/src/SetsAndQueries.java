import java.util.ArrayList;
import java.util.List;

public class SetsAndQueries {
    class DisjoinSet {
        int[] parent;
        int[] rank;
        int[] queryOrder;

        public DisjoinSet(int n) {
            parent = new int[n];
            rank = new int[n];
            queryOrder = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
                queryOrder[i] = Integer.MAX_VALUE;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            x = find(x);
            y = find(y);

            if (x != y) {
                if (rank[x] < rank[y]) {
                    int temp = x;
                    x = y;
                    y = temp;
                }

                parent[y] = x;

                if (rank[x] == rank[y]) {
                    rank[x]++;
                }
            }
        }

        public void createNewSet(int x, int y) {
            int setX = find(x);
            int setY = find(y);

            int orderX = queryOrder[setX];
            int orderY = queryOrder[setY];

            if (orderX <= orderY) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }

    public List<String> solution(int n, int[][] queries) {
        List<String> result = new ArrayList<>();
        DisjoinSet dsu = new DisjoinSet(n);

        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];
            int y = query[2];

            if (type == 1) {
                dsu.union(x, y);
            } else if (type == 2) {
                dsu.createNewSet(x, y);
            } else if (type == 3) {
                if (dsu.find(x) == dsu.find(y)) {
                    result.add("Yes");
                } else {
                    result.add("No");
                }
            }
        }

        return result;
    }
}
