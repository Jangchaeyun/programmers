import java.util.*;

public class Decide_on_a_hiking_course {
    private static List<List<Node>> graph;

    private static class Node {
        int e, w;

        Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }

    private static int[] dijkstra(int n, int[] gates, int[] submits) {
        Queue<Node> qu = new LinkedList<>();
        int[] intensity = new int[n + 1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            qu.add(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while (!qu.isEmpty()) {
            Node cn = qu.poll();

            if (cn.w > intensity[cn.e]) continue;

            for (int i = 0; i < graph.get(cn.e).size(); i++) {
                Node nn = graph.get(cn.e).get(i);

                int dis = Math.max(intensity[cn.e], nn.w);
                if (intensity[nn.e] > dis) {
                    intensity[nn.e] = dis;
                    qu.add(new Node(nn.e, dis));
                }
            }
        }

        int mn = Integer.MAX_VALUE;
        int mw = Integer.MAX_VALUE;

        Arrays.sort(submits);

        for (int submit : submits) {
            if (intensity[submit] < mw) {
                mn = submit;
                mw = intensity[submit];
            }
        }

        return new int[]{ mn, mw };
    }

    private static boolean isGate(int num, int[] gates) {
        for (int gate : gates) {
            if (num == gate) return true;
        }
        return false;
    }

    private static boolean isSubmit(int num, int[] submits) {
        for (int submit : submits) {
            if (num == submit) return true;
        }
        return false;
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] submits) {
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int s = path[0];
            int e = path[1];
            int w = path[2];

            if (isGate(s, gates) || isSubmit(e, submits)) {
                graph.get(s).add(new Node(e, w));
            } else if (isGate(e, gates) || isSubmit(s, submits)) {
                graph.get(e).add(new Node(s, w));
            } else {
                graph.get(s).add(new Node(e, w));
                graph.get(e).add(new Node(s, w));
            }
        }

        return dijkstra(n, gates, submits);
    }
}