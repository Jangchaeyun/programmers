import java.util.ArrayDeque;

public class Matrices_and_Operations {
    public int[][] solution(int[][] rc, String[] operations) {
        int R = rc.length;
        int C = rc[0].length;
        ArrayDeque<Integer> leftCol = new ArrayDeque<>();
        ArrayDeque<Integer> rightCol = new ArrayDeque<>();
        ArrayDeque<ArrayDeque<Integer>> rows = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            leftCol.add(rc[i][0]);
            rightCol.add(rc[i][C - 1]);
            ArrayDeque<Integer> tmp = new ArrayDeque<>();
            for (int j = 1; j < C - 1; j++)
                tmp.add(rc[i][j]);
            rows.add(tmp);
        }

        for (String op : operations) {
            if (op.charAt(0) == 'S') {
                leftCol.addFirst(leftCol.removeLast());
                rightCol.addFirst(rightCol.removeLast());
                rows.addFirst(rows.removeLast());
            }
            else {
                rows.getFirst().addFirst(leftCol.removeFirst());
                rightCol.addFirst(rows.getFirst().removeLast());
                rows.getLast().addLast(rightCol.removeLast());
                leftCol.addLast(rows.getLast().removeFirst());
            }
        }

        int[][] result = new int[R][C];
        for (int i = 0; i < R; i++) {
            result[i][0] = leftCol.removeFirst();
            result[i][C - 1] = rightCol.removeFirst();
            ArrayDeque<Integer> tmp = rows.removeFirst();
            for (int j = 1; j < C - 1; j++)
                result[i][j] = tmp.removeFirst();
        }

        return result;
    }
}
