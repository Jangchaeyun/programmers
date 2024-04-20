public class _3xn_tiling {
    long[] title = new long[5001];

    public long solution(int n) {
        long answer = 0;
        int mod = 1000000007;
        title[0] = 1;
        title[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            title[i] = (title[i - 2] * 4 % mod - title[i - 4] % mod + mod) % mod;
        }
        return answer = title[n];
    }
}
