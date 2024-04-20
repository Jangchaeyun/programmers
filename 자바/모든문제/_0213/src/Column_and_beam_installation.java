public class Column_and_beam_installation {
    boolean[][] pillar;
    boolean[][] bar;

    public boolean canDelete(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (pillar[i][j] && checkPillar(i, j) == false) return false;
                else if (bar[i][j] && checkBar(i, j) == false) return false;
            }
        }
        return true;
    }

    public boolean checkPillar(int x, int y) {
        if (y == 0) return true;
        else if (y > 0 && pillar[x][y - 1]) return true;
        else if (x > 0 && bar[x - 1][y] || bar[x][y]) return true;
        return false;
    }

    public boolean checkBar(int x, int y) {
        if (y > 0 && pillar[x][y - 1] || pillar[x + 1][y - 1]) return true;
        else if (x > 0 && bar[x - 1][y] && bar[x + 1][y]) return true;
        return false;
    }

    public int[][] solution(int n, int[][] build_frame) {
        pillar = new boolean[n + 1][n + 1];
        bar = new boolean[n + 1][n + 1];

        int count = 0;
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int type = build_frame[i][2];
            int action = build_frame[i][3];

            if (type == 0) {
                if (action == 1) {
                    if (checkPillar(x, y)) {
                        pillar[x][y] = true;
                        count++;
                    }
                } else {
                    pillar[x][y] = false;
                    if (canDelete(n) == false) pillar[x][y] = true;
                    else count--;
                }
            } else {
                if (action == 1) {
                    if (checkBar(x, y)) {
                        bar[x][y] = true;
                        count++;
                    }
                } else {
                    bar[x][y] = false;
                    if (canDelete(n) == false) bar[x][y] = true;
                    else count--;
                }
            }
        }

        int[][] result = new int[count][3];
        int idx = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (pillar[i][j]) {
                    result[idx][0] = i;
                    result[idx][1] = j;
                    result[idx++][2] = 0;
                }
                if (bar[i][j]) {
                    result[idx][0] = i;
                    result[idx][1] = j;
                    result[idx++][2] = 1;
                }
            }
        }
        return result;
    }
}
