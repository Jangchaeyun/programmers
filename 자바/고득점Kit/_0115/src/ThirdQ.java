import java.util.*;

public class ThirdQ {
    public static int solution(int[] arrows) {
        int answer = 0;

        int[] dx = { 0,  1, 1, 1, 0, -1, -1, -1};
        int[] dy = {-1, -1, 0, 1, 1,  1,  0, -1};
        Node curNode = new Node(0, 0);

        Map<Node, List<Node>> visited = new HashMap<>();

        for (int arrow : arrows) {
            for (int i = 0; i <= 1; i++) {
                Node nextNode = new Node(curNode.x + dx[arrow], curNode.y + dy[arrow]);

                if (!visited.containsKey(nextNode)) {
                    visited.put(nextNode, makeEdgeList(curNode));

                    if (visited.get(curNode) == null) {
                        visited.put(curNode, makeEdgeList(nextNode));
                    } else {
                        visited.get(curNode).add(nextNode);
                    }

                } else if (!visited.get(nextNode).contains(curNode)) {
                    visited.get(nextNode).add(curNode);
                    visited.get(curNode).add(nextNode);
                    answer++;
                }

                curNode = nextNode;
            }
        }

        return answer;
    }

    private static List<Node> makeEdgeList(Node node) {
        List<Node> edge = new ArrayList<>();
        edge.add(node);
        return edge;
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
