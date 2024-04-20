import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class IUAndConsBoardGame {
    public int solution(int n, int[][] triangle, int[][] v) {
        Set<Point> points = new HashSet<>();

        for (int[] t : triangle) {
            points.add(new Point(t[0], t[1]));
        }

        for (int[] pin : v) {
            points.add(new Point(pin[0], pin[1]));
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    Point a = new Point(triangle[i][0], triangle[i][1]);
                    Point b = new Point(triangle[j][0], triangle[j][1]);
                    Point c = new Point(triangle[k][0], triangle[k][1]);

                    if (isInsideTriangle(a, b, c, points)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean isInsideTriangle(Point a, Point b, Point c, Set<Point> points) {
        for (Point p : points) {
            if (p.equals(a) || p.equals(b) || p.equals(c)) {
                continue;
            }

            if (isInside(a, b, c, p)) {
                return false;
            }
        }

        return true;
    }

    private boolean isInside(Point a, Point b, Point c, Point p) {
        double s1 = Math.abs((a.x * (b.y - p.y) + b.x * (p.y - a.y) + p.x * (a.y - b.y)) / 2.0);
        double s2 = Math.abs((a.x * (c.y - p.y) + c.x * (p.y - a.y) + p.x * (a.y - c.y)) / 2.0);
        double s3 = Math.abs((b.x * (c.y - p.y) + c.x * (p.y - b.y) + p.x * (b.y - c.y)) / 2.0);

        double s = Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2.0);

        return Math.abs(s - (s1 + s2 + s3)) < 1e-9;
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
