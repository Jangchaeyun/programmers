import java.util.ArrayList;
import java.util.List;

public class First_in_first_out_scheduling {
    public int N;
    public int[] C;
    public int INF = 987654321;

    public int check(int t) {
        List<Integer> zerolist = new ArrayList<>();

        int insertCnt = 0;

        for (int i = 0; i < C.length; i++) {
            insertCnt += (t / C[i] + 1);
            if (t % C[i] == 0) {
                zerolist.add(i + 1);
            }
        }
        if (insertCnt < N) return INF;
        else if (N <= insertCnt - zerolist.size()) return -INF;
        else {
            return zerolist.get(N - (insertCnt - zerolist.size()) - 1);
        }
    }

    public int solution(int n, int[] cores) {
        N = n;
        C = cores;
        int maxT = 0;
        for (int i = 0; i < C.length; i++)
            maxT = Math.max(maxT, C[i]);

        int hi = N * maxT / C.length + 1;
        int lo = 0;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int res = check(mid);

            if (res == INF) lo = mid;
            else if (res == -INF) hi = mid;
            else return res;
        }
        return 0;
    }
}
