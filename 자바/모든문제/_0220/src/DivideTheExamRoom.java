import java.util.Arrays;

public class DivideTheExamRoom {
    private int root, size;
    private Node[] tree;
    private int[] parent;

    class Node {
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }


    private int checkGroup(int[] num, int max) {
        size = 0;
        dfs(num, root, max);
        return size + 1;
    }

    private int dfs(int[] num, int cur, int max) {
        int lv = 0, rv = 0;
        if (tree[cur].left != -1)
            lv = dfs(num, tree[cur].left, max);
        if (tree[cur].right != -1)
            rv = dfs(num, tree[cur].right, max);

        if (num[cur] + lv + rv <= max)
            return num[cur] + lv + rv;

        if (num[cur] + Math.min(lv, rv) <= max) {
            size += 1;
            return num[cur] + Math.min(lv, rv);
        }

        size += 2;
        return num[cur];
    }

    private void setTree(int[] num, int[][] links) {
        tree = new Node[num.length];
        parent = new int[num.length];

        Arrays.fill(parent, -1);

        for (int i = 0; i < links.length; i++) {
            tree[i] = new Node(links[i][0], links[i][1]);

            if (tree[i].left != -1) parent[tree[i].left] = i;
            if (tree[i].right != -1) parent[tree[i].right] = i;
        }

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1) {
                root = i;
                break;
            }
        }
    }

    public int solution(int k, int[] num, int[][] links)  {
        int answer = 0;
        setTree(num, links);

        int low = 0, high = (int) 1e9;
        for (int n : num) low = Math.max(low, n);

        while (low <= high) {
            int mid = (low + high) / 2;
            if (checkGroup(num, mid) <= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high + 1;
    }
}
