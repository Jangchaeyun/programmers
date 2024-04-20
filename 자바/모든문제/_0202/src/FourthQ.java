public class FourthQ {
    public String solution(String w) {
        if (w.isEmpty()) {
            return w;
        }

        int left = 0;
        int right = 0;
        int index = 0;
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                index = i;
                break;
            }
        }
        String u = w.substring(0, index + 1);
        String v = w.substring(index + 1);

        if (isCorrect(u)) {
            return u + solution(v);
        }

        return "(" + solution(v) + ")" + reverse(u);
    }

    private boolean isCorrect(String u) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                return false;
            }
        }
        return true;
    }

    private String reverse(String u) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i) == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }
}
