import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int value;
    int parent;
    long subtreeSum;

    public TreeNode(int value) {
        this.value = value;
        this.parent = -1;
        this.subtreeSum = value;
    }
}
class ActionOfGravity {
    private static long caculateSubtreeSum(TreeNode[] tree, int node, boolean[] visited) {
        visited[node] = true;
        for (int i = 1; i < tree.length; i++) {
            if (!visited[i] && tree[i].parent == node) {
                tree[node].subtreeSum += caculateSubtreeSum(tree, i, visited);
            }
        }
        return tree[node].subtreeSum;
    }

    private static void updateTree(TreeNode[] tree, int node, int value) {
        while (node != -1) {
            tree[node].subtreeSum += value - tree[node].value;
            tree[node].value = value;
            node = tree[node].parent;
        }
    }

    public static List<Long> solution(int[] values, int[][] edges, int[][] queries) {
        int n = values.length;
        List<Long> result = new ArrayList<>();
        TreeNode[] tree = new TreeNode[n + 1];
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new TreeNode(values[i - 1]);
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (u < v) {
                tree[v].parent = u;
            } else {
                tree[u].parent = v;
            }
        }
        caculateSubtreeSum(tree, 1, visited);

        for (int[] query : queries) {
            int u = query[0];
            int w = query[1];

            if (w == -1) {
                result.add(tree[u].subtreeSum);
            } else {
                updateTree(tree, u, w);
            }
        }
        return result;
    }
}
