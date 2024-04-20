public class FifthQ {
    public int solution(int[] money) {
        int answer = 0;
        if (money.length == 3) {
            for (int i = 0; i < 3; i++) if (answer < money[i]) answer = money[i];
            return answer;
        }

        int[] dpForGetFirst = new int[money.length];
        int[] dpForIgnoreFirst = new int[money.length];

        dpForGetFirst[0] = money[0];
        dpForGetFirst[1] = Math.max(money[0], money[1]);

        dpForIgnoreFirst[0] = 0;
        dpForIgnoreFirst[1] = money[1];

        for (int i = 2; i < money.length; i++) {
            dpForIgnoreFirst[i] = Math.max(dpForIgnoreFirst[i - 1], dpForIgnoreFirst[i - 2] + money[i]);
            answer = Math.max(answer, dpForIgnoreFirst[i]);

            if (i == money.length - 1) break;
            dpForGetFirst[i] = Math.max(dpForGetFirst[i - 1], dpForGetFirst[i - 2] + money[i]);
            answer = Math.max(answer, dpForGetFirst[i]);
        }
        return answer;
    }
}
