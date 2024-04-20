public class SixthQ {
    public int[] solution(String s) {
        int[] answer = new int[2];

        while (s.length() > 1) {

            int cntDne = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') answer[1]++;
                else cntDne++;
            }

            s = Integer.toBinaryString(cntDne);
            answer[0]++;
        }

        return answer;
    }
}
