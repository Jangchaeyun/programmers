import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThirdQ {
    private static List<File> list = new ArrayList<>();

    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        int idx = 0;
        for (String file : files) {
            boolean numFlag = false;
            StringBuilder num = new StringBuilder();
            StringBuilder head = new StringBuilder();
            StringBuilder tail = new StringBuilder();

            for (int i = 0; i < file.length(); i++) {
                char c = file.charAt(i);
                if (c >= '0' && c <= '9') {
                    num.append(c);
                    numFlag = true;
                } else {
                    if (!numFlag) head.append(c);
                    else {
                        tail.append(file.substring(i));
                        break;
                    }
                }
            }
            System.out.println(head + " " + num + " " + tail);
            list.add(new File(idx, head.toString(), num.toString(), tail.toString()));
            idx++;
        }

        Collections.sort(list);

        for (int i = 0; i < answer.length; i++) {
            File file = list.get(i);
            answer[i] = file.head + file.num + file.tail;
        }

        return answer;
    }

    private static class File implements Comparable<File> {
        int idx;
        String head;
        String num;
        String tail;

        public File(int idx, String head, String num, String tail) {
            this.idx = idx;
            this.head = head;
            this.num = num;
            this.tail = tail;
        }

        @Override
        public int compareTo(File f) {
            if (this.head.equalsIgnoreCase(f.head)) {
                if (Integer.parseInt(this.num) == Integer.parseInt(f.num)) {
                    return this.idx - f.idx;
                }
                return Integer.parseInt(this.num) - Integer.parseInt(f.num);
            }
            return this.head.toLowerCase().compareTo(f.head.toLowerCase());
        }
    }
}
