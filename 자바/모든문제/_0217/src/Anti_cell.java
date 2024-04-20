import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anti_cell {
    static int MOD = 1000000007;

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};
        int[] s = {2, 4};
        int[] result = solution(a, s);
        for (int res : result) {
            System.out.println(res);
        }
    }

    static int binarySearch(long[] arr, int x) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if (low < arr.length && arr[low] == x) {
            return low;
        }
        return -1;
    }

    static int solve(int[] b) {
        int maxb = Arrays.stream(b).max().orElse(0);
        int n = b.length;

        long[] ps = new long[n + 1];
        ps[0] = 0;
        for (int i = 0; i < n; i++) {
            ps[i + 1] = ps[i] + b[i];
        }

        int maxe = (int) Math.ceil(Math.log(ps[n]) / Math.log(2));

        long[][] dp = new long[n][maxe + 1];
        dp[0][0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Arrays.stream(dp[i - 1]).sum() % MOD;

            for (int e = 1; e <= maxe; e++) {
                if (dp[i][e - 1] == 0) {
                    continue;
                }

                int target = (int) (ps[i + 1] - b[i] * Math.pow(2, e - 1));
                int jhalf = binarySearch(ps, target);
                if (jhalf == -1) {
                    continue;
                }

                double eprime = Math.log(b[i] / (double) b[jhalf - 1]) / Math.log(2) + e - 1;
                if (eprime < 0 || Math.floor(eprime) != eprime) {
                    continue;
                }

                if (dp[jhalf - 1][(int) eprime] == 0) {
                    continue;
                }

                int jfull = binarySearch(ps, (int) (ps[i + 1] - b[i] * Math.pow(2, e)));
                if (jfull == -1) {
                    continue;
                }

                if (jfull == 0) {
                    dp[i][e] = 1;
                } else {
                    dp[i][e] = Arrays.stream(dp[jfull - 1]).sum() % MOD;
                }
            }
        }

        return (int) Arrays.stream(dp[n - 1]).sum() % MOD;
    }

    static int[] solution(int[] a, int[] s) {
        List<Integer> resultList = new ArrayList<>();
        int start = 0;
        for (int l : s) {
            int[] b = Arrays.copyOfRange(a, start, start + l);
            resultList.add(solve(b));
            start += l;
        }
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }
}
