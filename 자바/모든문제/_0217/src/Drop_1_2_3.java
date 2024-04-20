import java.util.ArrayList;
import java.util.Collections;

public class Drop_1_2_3 {
    public int[] solution(int[][] edges, int[] target) {
        int n = edges.length + 1;
        int T = 0;

        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            tree[parent - 1].add(child - 1);
        }
        for (int i = 0; i < n; i++) Collections.sort(tree[i]);

        int[] pass = new int[n];
        int[] cnt = new int[n];
        boolean[] check = new boolean[n];
        ArrayList<Integer> Q = new ArrayList<>();

        for (int i = 0; i < n; i++) if (tree[i].isEmpty() && target[i] > 0) T++;

        while (T > 0) {
            int node = 0;

            while (tree[node].size() > 0) node = tree[node].get(pass[node]++ % tree[node].size());

            cnt[node]++;
            Q.add(node);

            if (cnt[node] > target[node]) {
                int[] answer = { -1 };
                return answer;
            }

            if (!check[node] && target[node] <= 3 * cnt[node]) {
                check[node] = true;
                T--;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i : Q) {
            cnt[i]--;
            for (int val = 1; val <= 3; val++) {
                if (cnt[i] <= target[i] - val && target[i] - val <= 3 * cnt[i]) {
                    res.add(val);
                    target[i] -= val;
                    break;
                }
            }
        }

        int[] answer = new int[res.size()];
        for (int i = 0; i < res.size(); i++) answer[i] = res.get(i);
        return answer;
    }
}
