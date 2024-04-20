import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class SmartFrodo {
    public int[][] solution(int n, int[][] a, int[][] b, int[] e1, int[] e2) {
        ArrayList<Dot> dots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dots.add(new Dot(i + 1));
        }

        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            Line l = new Line(i, dots.get(Math.min(a[i][0], a[i][1]) - 1), dots.get(Math.max(a[i][0], a[i][1]) - 1));
            lines.add(l);
        }

        HashSet<Line> selected = new HashSet<>();
        for (int i = 0; i < e1.length; i++) {
            Line l = lines.get(e1[i] - 1);
            selected.add(l);
            l.dot1.used = true;
            l.dot2.used = true;
        }

        LinkedList<Line> toDo = new LinkedList<>();
        HashSet<Line> must = new HashSet<>();
        for (int i = 0; i < e2.length; i++) {
            Line l = lines.get(e2[i] - 1);
            if (!selected.contains(l)) toDo.add(l);
            must.add(l);
        }

        LinkedList<int[]> answer = new LinkedList<>();
        while (true) {
            if (selected.equals(must)) break;
            Line forDel = null;
            if (selected.size() > n - 2) {
                for (Line el : selected) {
                    if (!must.contains(el)) {
                        forDel = el;
                        break;
                    }
                }
                if (forDel != null) {
                    answer.add(new int[]{0, forDel.index});
                    selected.remove(forDel);
                    forDel.dot1.used = false;
                    forDel.dot2.used = false;
                }
            }
            Line forAdd = null;
            for (Line nl : toDo) {
                if (nl.dot1.used) break;
                if (nl.dot2.used) break;
                forAdd = nl;
                break;
            }
            if (forAdd != null) {
                answer.add(new int[]{1, forAdd.index});
                selected.add(forAdd);
                toDo.remove(forAdd);
                forAdd.dot1.used = true;
                forAdd.dot2.used = true;
            }
            if (forDel == null && forAdd == null) break;
        }

        int[][] answerarr = new int[answer.size()][2];
        for (int i = 0; i < answerarr.length; i++) answerarr[i] = answer.get(i);
        return answerarr;
    }

    class Dot {
        int num;
        boolean used;

        Dot(int num) {
            this.num = num;
            this.used = false;
        }
    }

    class Line {
        int index;
        Dot dot1;
        Dot dot2;

        Line(int index, Dot dot1, Dot dot2) {
            this.index = index + 1;
            this.dot1 = dot1;
            this.dot2 = dot2;
        }

        @Override
        public int hashCode() {
            return index;
        }

        @Override
        public boolean equals(Object obj) {
            return (this.hashCode() == obj.hashCode());
        }
    }
}
