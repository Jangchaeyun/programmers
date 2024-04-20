import java.util.*;

public class FakeHamiltonian {
    static int answer, N;
    static List<List<Integer>> graph;
    static int[] subTree, depth;

    static void setDepthSubtree(int node, int prev) {
        List<Integer> subTrees = new ArrayList<>();
        List<Integer> depths = new ArrayList<>();
        for (int child : graph.get(node)) {
            if (child == prev) continue;
            setDepthSubtree(child, node);
            subTrees.add(subTree[child]);
            depths.add(depth[child]);
            depth[node] = Math.max(depth[node], depth[child] + 1);
        }

        Collections.sort(subTrees, Collections.reverseOrder());
        Collections.sort(depths, Collections.reverseOrder());

        if (subTrees.size() >= 2) {
            if (!Objects.equals(subTrees.get(0), depths.get(0)))
                subTree[node] = subTrees.get(0) + depths.get(0) + 1;
            else
                subTree[node] = Math.max(subTrees.get(0) + depths.get(1) + 1, subTrees.get(1) + depths.get(0) + 1);
        } else if (subTrees.size() == 1) {
            subTree[node] = subTrees.get(0) + 1;
        }

    }
    static void dfs(int node, int prev, int height) {
        List<Integer> subTrees = new ArrayList<>();
        List<Integer> depths = new ArrayList<>();
        for (int child : graph.get(node)) {
            if (child == prev) continue;
            subTrees.add(subTree[child]);
            depths.add(depth[child]);
        }

        Collections.sort(subTrees, Collections.reverseOrder());
        Collections.sort(depths, Collections.reverseOrder());

        for (int child : graph.get(node)) {
            if (child == prev) continue;
            int nextHeight = height + 1;
            if (!Objects.equals(depths.get(0), child))
                nextHeight = Math.max(nextHeight, depths.get(0) + 2);
            else if (depths.size() >= 2 && Objects.equals(depths.get(0), child))
                nextHeight = Math.max(nextHeight, depths.get(1) + 2);
            dfs(child, node, nextHeight);
        }

        if (subTrees.size() >= 3) {
            answer = Math.max(answer, subTrees.get(0) + subTrees.get(1) + height);
            answer = Math.max(answer, subTrees.get(0) + subTrees.get(2) + depths.get(1) + 1);
            answer = Math.max(answer, subTrees.get(0) + subTrees.get(1) + depths.get(2) + 1);
        } else if (subTrees.size() == 2)
            answer = Math.max(answer, subTrees.get(0) + subTrees.get(1) + height);
    }

    public int solution(int[][] t) {
        N = t.length + 1;
        graph = new ArrayList<>();
        subTree = new int[N];
        depth = new int[N];
        Arrays.fill(subTree, 1);
        Arrays.fill(depth, 1);
        answer = 0;

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : t) {
            int v1 = edge[0];
            int v2 = edge[1];
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        setDepthSubtree(0, -1);
        dfs(0, -1, 1);
        return answer;
    }
}
