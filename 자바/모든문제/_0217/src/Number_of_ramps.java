import java.util.Arrays;

public class Number_of_ramps {
    static final int MOD = 1_000_000_007;

    public int solution(int[][] grid, int[] d, long k) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][d.length];

        for (int[][] layer : dp)
            for (int[] row : layer)
                Arrays.fill(row, -1);

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = (result + dfs(grid, i, j, d, 0, k, dp)) % MOD;
            }
        }
        return result;
    }

    public int dfs(int[][] grid, int i, int j, int[] d, int idx, long k, int[][][] dp) {
        int n = grid.length;
        int m = grid[0].length;

        if (i < 0 || i >= n || j < 0 || j >= m)
            return 0;
        if (idx == d.length - 1)
            return 1;
        if (dp[i][j][idx] != -1)
            return dp[i][j][idx];

        int result = 0;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (di == 0 && dj == 0)
                    continue;
                int ni = i + di;
                int nj = j + dj;
                int diff = grid[ni][nj] - grid[i][j];
                if (idx < d.length - 1 && diff == d[idx]) {
                    result = (result + dfs(grid, ni, nj, d, idx + 1, k, dp)) % MOD;
                }
            }
        }

        return dp[i][j][idx] = result;
    }
}
