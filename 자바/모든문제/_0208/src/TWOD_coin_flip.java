public class TWOD_coin_flip {
    public int R, C;
    public int[][] M, T, tmp;

    public void flipCol(int c) {
        for (int i = 0; i < R; i++) {
            tmp[i][c] = tmp[i][c] == 0 ? 1 : 0;
        }
    }

    public void copyArr() {
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                tmp[i][j] = M[i][j];
    }

    public int solution(int[][] beginning, int[][] target) {
        R = beginning.length;
        C = beginning[0].length;
        M = beginning;
        T = target;
        tmp = new int[R][C];
        int result = Integer.MAX_VALUE;
        for (int mask = 0; mask < 2 << C; mask++) {
            int tmpResult = 0;
            copyArr();
            for (int col = 0; col < C; col++) {
                if ((mask & 2 << col) == 0) continue;
                tmpResult++;
                flipCol(col);
            }

            boolean flag = false;
            for (int i = 0; i < R; i++) {
                if (flag) break;
                if (tmp[i][0] == T[i][0]) {
                    for (int j = 1; j < C; j++) {
                        if (tmp[i][j] != T[i][j]) {
                            flag = true;
                            break;
                        }
                    }
                }
                else {
                    for (int j = 1; j < C; j++) {
                        if (tmp[i][j] == T[i][j]) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) tmpResult++;
                }
            }
            if (!flag) result = Math.min(result, tmpResult);
        }

        if (result == Integer.MAX_VALUE) return  -1;
        else return result;
    }
}
