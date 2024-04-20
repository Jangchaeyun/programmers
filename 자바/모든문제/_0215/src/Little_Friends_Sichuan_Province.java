import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Little_Friends_Sichuan_Province {
    static class tile implements Comparable<tile> {
        int c;
        int x;
        int y;

        tile(int c, int x, int y) {
            this.c = c;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(tile o) {
            int result = this.c - o.c;

            if (result == 0) {
                result = this.y - o.y;
                if (result == 0) {
                    result = this.x - o.x;
                }
            }

            return result;
        }
    }

    static List<tile> tileList;
    static char[][] boardChar;
    static String answer;

    public static String solution(int m, int n, String[] board) {
        answer = "";
        tileList = new ArrayList<>();
        boardChar = new char[m][n];
        for (int i = 0; i < board.length; i++) {
            boardChar[i] = board[i].toCharArray();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = boardChar[i][j];
                if ('A' <= c && c <= 'Z') {
                    tileList.add(new tile(c, i, j));
                }
            }
        }

        Collections.sort(tileList);

        for (int i = 0; i < tileList.size(); i += 2) {
            tile t1 = tileList.get(i);
            tile t2 = tileList.get(i + 1);

            if (canRemove(t1, t2)) {
                answer += (char) t1.c;
                tileList.remove(t1);
                tileList.remove(t2);
                boardChar[t1.x][t1.y] = '.';
                boardChar[t2.x][t2.y] = '.';
                i = -2;
            }

        }

        if (tileList.size() == 0)
            return answer;

        return "IMPOSSIBLE";
    }

    private static boolean canRemove(tile t1, tile t2) {
        if (t1.x == t2.x) {
            for (int i = t1.y + 1; i < t2.y; i++) {
                if (boardChar[t1.x][i] != '.')
                    return false;
            }
            return true;
        } else if (t1.y == t2.y) {
            for (int i = t1.x + 1; i < t2.x; i++) {
                if (boardChar[i][t1.y] != '.')
                    return false;
            }
            return true;
        } else {
            boolean flag1 = true, flag2 = true;

            if (t1.x < t2.x) {
                for (int i = t1.y + 1; i < t2.y; i++) {
                    if (boardChar[t1.x][i] != '.') {
                        flag1 = false;
                        break;
                    }
                }

                for (int i = t1.x; i < t2.x; i++) {
                    if (!flag1)
                        break;

                    if (boardChar[i][t2.y] != '.') {
                        flag1 = false;
                        break;
                    }
                }

                for (int i = t1.x + 1; i <= t2.x; i++) {
                    if (boardChar[i][t1.y] != '.') {
                        flag2 = false;
                        break;
                    }
                }

                for (int i = t1.y; i < t2.y; i++) {
                    if (!flag2)
                        break;

                    if (boardChar[t2.x][i] != '.') {
                        flag2 = false;
                        break;
                    }
                }
            }

            else {
                for (int i = t1.x - 1; i >= t2.x; i--) {
                    if (boardChar[i][t1.y] != '.') {
                        flag1 = false;
                        break;
                    }
                }

                for (int i = t1.y; i < t2.y; i++) {
                    if (!flag1)
                        break;
                    if (boardChar[t2.x][i] != '.') {
                        flag1 = false;
                        break;
                    }
                }

                for (int i = t1.y + 1; i <= t2.y; i++) {
                    if (boardChar[t1.x][i] != '.') {
                        flag2 = false;
                        break;
                    }
                }

                for (int i = t1.x; i > t2.x; i--) {
                    if (!flag2)
                        break;
                    if (boardChar[i][t2.y] != '.') {
                        flag2 = false;
                        break;
                    }
                }
            }

            if (flag1 || flag2)
                return true;

            return false;
        }
    }
}
