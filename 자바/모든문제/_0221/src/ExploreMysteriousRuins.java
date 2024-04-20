import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ExploreMysteriousRuins {
    static int maxCommonPart(int n1, int[][] g1, int n2, int[][] g2) {
        List<Integer>[] tree1 = buildTree(n1, g1);
        List<Integer>[] tree2 = buildTree(n2, g2);

        int maxCommon = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                int common = findCommon(tree1, i, tree2, j);
                maxCommon = Math.max(maxCommon, common);
            }
        }

        return maxCommon;
    }

    static List<Integer>[] buildTree(int n, int[][] edges) {
        List<Integer>[] tree = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            tree[parent].add(child);
        }

        return tree;
    }

    static int findCommon(List<Integer>[] tree1, int root1, List<Integer>[] tree2, int root2) {
        boolean[] visited1 = new boolean[tree1.length];
        boolean[] visited2 = new boolean[tree2.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root1);
        visited1[root1] = true;

        int common = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited2[node]) {
                common++;
            }

            for (int child : tree1[node]) {
                if (!visited1[child]) {
                    queue.offer(child);
                    visited1[child] = true;
                }
            }
        }

        queue.clear();
        queue.offer(root2);
        visited2[root2] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited1[node]) {
                common++;
            }

            for (int child : tree2[node]) {
                if (!visited2[child]) {
                    queue.offer(child);
                    visited2[child] = true;
                }
            }
        }

        return common;
    }
}
