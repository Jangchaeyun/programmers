public class NumberOfCollectionsInTheQuery {
    public int solution(int q, int[] a) {
        final int MOD = 998_244_353;
        int n = a.length;
        int[] dp = new int[q + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            int[] next = new int[q + 1];

            for (int j = 0; j <= q; j++) {
                for (int k = 0; k <= j; k++) {
                    next[Math.max(j, a[i] - k)] = (next[Math.max(j, a[i] - k)] + dp[j]) % MOD;
                }
            }

            dp = next;
        }

        int result = 0;
        for (int count : dp) {
            result = (result + count) % MOD;
        }

        return result;
    }
}
