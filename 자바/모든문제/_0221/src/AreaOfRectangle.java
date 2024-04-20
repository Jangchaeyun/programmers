import java.util.ArrayList;
import java.util.TreeSet;

public class AreaOfRectangle {
    public long solution(int[][] rectangles) {
        long answer = 0;
        TreeSet<Integer> xSet = new TreeSet<>();
        ArrayList<int[]> reacts = new ArrayList<>();

        for (int[] rect : rectangles) {
            xSet.add(rect[0]);
            xSet.add(rect[2]);
            reacts.add(rect);
        }

        ArrayList<Integer> xList = new ArrayList<>();

        for (int i = 0; i < xList.size() - 1; i++) {
            int x1 = xList.get(i);
            int x2 = xList.get(i + 1);
            ArrayList<int[]> active = new ArrayList<>();
            for (int[]  rect : reacts) {
                if (rect[0] <= x1 && rect[2] >= x2) {
                    active.add(rect);
                }
            }
            active.sort((a, b) -> a[1] - b[1]);
            long height = 0;
            int prevY = -1;
            for (int[] rect : active) {
                int y1 = Math.max(rect[1], prevY);
                int y2 = rect[3];
                height += Math.max(0, y2 - y1);
                prevY = Math.max(prevY, y2);
            }
            answer += height * (x2 - x1);
        }
        return answer;
    }
}
