public class CountEvenRows {
    static final int MOD = 10_000_019;

    public static int solution(int[][] a) {
        int row = a.length;
        int col = a[0].length;

        int[][] combis = new int[row + 1][row + 1];
        combis[0][0] = 1;

        for (int i = 1; i <= row; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) combis[i][j] = 1;
                else if (i == j) combis[i][j] = 1;
                else combis[i][j] = (combis[i - 1][j - 1] + combis[i - 1][j]) % MOD;
            }
        }

        int[] numOfOne = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (a[i][j] == 1) numOfOne[j]++;
            }
        }

        int[][] DP = new int[col + 1][row + 1];
        DP[1][row - numOfOne[0]] = combis[row][row - numOfOne[0]];

        for (int curCol = 1; curCol <= col; curCol++) {
            for (int curRow = 0; curRow <= row; curRow++) {
                if (DP[curCol][curRow] == 0) continue;

                for (int one = 0; one <= numOfOne[curCol - 1]; one++) {
                    int next = (curRow - one) + (numOfOne[curCol - 1] - one);

                    if (next > row || curRow < one) continue;

                    long cases = (long) combis[curRow][one] * combis[row - curRow][numOfOne[curCol - 1] - one] % MOD;

                    DP[curCol + 1][next] = (DP[curCol + 1][next] + (int) ((long) DP[curCol][curRow] * cases % MOD)) % MOD;
                }
            }
        }

        return DP[col][row];
    }
}
