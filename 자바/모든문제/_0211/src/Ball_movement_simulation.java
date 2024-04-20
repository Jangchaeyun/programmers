public class Ball_movement_simulation {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        int[] point = { y, y + 1, x, x + 1 };
        int[] dir = { -1, 1, -1, 1 };
        int[] boundary = { 0, m, 0, n };
        int[] limit = { m, m, n, n };

        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            int dx = queries[i][1];

            int reverse = command ^ 1;
            point[reverse] += dir[reverse] * dx;
            point[reverse] = Math.max(Math.min(point[reverse], limit[reverse]), 0);

            if (point[command] != boundary[command]) {
                point[command] += dir[reverse] * dx;
                point[command] = Math.max(Math.min(point[command], limit[command]), 0);
            }

            if (point[0] == m || point[1] == 0 || point[2] == n || point[3] == 0) {
                return 0L;
            }
        }
        return (1L * point[1] - point[0]) * (1L * point[3] - point[2]);
    }
}
