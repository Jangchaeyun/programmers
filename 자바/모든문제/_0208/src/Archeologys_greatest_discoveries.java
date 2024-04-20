public class Archeologys_greatest_discoveries {
    int N, result = Integer.MAX_VALUE;
    int[][] map;
    int[] rot;
    int[] dx = { 0, 0, 0, 1, -1 };
    int[] dy = { 0, 1, -1, 0, 0 };

    public void dfs(int ind) {
        if (ind == N) {
            int[][] tmp = new int[N][N];
            int[] cur = new int[N];
            for (int i = 0; i < N; i++)
                cur[i] = rot[i];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    tmp[i][j] = map[i][j];
            }

            int tmpRes = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tmpRes += cur[j];
                    for (int d = 0; d < 5; d++) {
                        int X = i + dx[d];
                        int Y = j + dy[d];
                        if (X < 0 || Y < 0 || X >= N || Y >= N) continue;
                        tmp[X][Y] = tmp[X][Y] - cur[j] >= 0 ? tmp[X][Y] - cur[j] : tmp[X][Y] - cur[j] + 4;
                    }
                }

                for (int j = 0; j < N; j++)
                    cur[j] = tmp[i][j];
            }

            boolean flag = true;
            for (int i = 0; i < N; i++) {
                if (cur[i] != 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) result = Math.min(result, tmpRes);
            return;
        }

        for (int i = 0; i < 4; i++) {
            rot[ind] = i;
            dfs(ind + 1);
        }
    }

    public int solution(int[][] clockHands) {
        N = clockHands.length;
        map = new int[N][N];
        rot = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = 4 - clockHands[i][j] == 4 ? 0 : 4 - clockHands[i][j];
            }
        }

        dfs(0);
        return result;
    }
}
