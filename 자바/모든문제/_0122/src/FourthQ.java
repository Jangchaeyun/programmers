public class FourthQ {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        double[] fail = new double[N];
        int noclear[] = new int[N];

        for (int s : stages) {
            if (s != N + 1) noclear[s - 1] += 1;
        }

        int player = stages.length;
        for (int i = 0; i < N; i++) {
            fail[i] = (double) noclear[i] / player;
            player -= noclear[i];
            answer[i] = i + 1;
        }

        double tmpf = 0;
        int tmpa = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                if (fail[j - 1] < fail[j]) {
                    tmpf = fail[j - 1];
                    fail[j - 1] = fail[j];
                    fail[j] = tmpf;

                    tmpa = answer[j - 1];
                    answer[j - 1] = answer[j];
                    answer[j] = tmpa;
                }
            }
        }

        return answer;
    }
}
