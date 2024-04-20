import java.util.HashMap;

public class FirstQ {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int friendsLength = friends.length;
        HashMap<String, Integer> dic = new HashMap<>();
        int[] giftDegree = new int[friendsLength];
        int[][] giftGraph = new int[friendsLength][friendsLength];

        for (int i = 0; i < friendsLength; i++) {
            dic.put(friends[i], i);
        }

        for (String gift : gifts) {
            String[] giftName = gift.split(" ");
            giftDegree[dic.get(giftName[0])]++;
            giftDegree[dic.get(giftName[1])]--;
            giftGraph[dic.get(giftName[0])][dic.get(giftName[1])]++;
        }

        for (int i = 0; i < friendsLength; i++) {
            int num = 0;
            for (int j = 0; j < friendsLength; j++) {
                if (i == j) {
                    continue;
                }

                if (giftGraph[i][j] > giftGraph[j][i] ||
                        (giftGraph[i][j] == giftGraph[j][i] && giftDegree[i] > giftDegree[j])) {
                    num++;
                }
            }

            if (answer < num) {
                answer = num;
            }
        }
        return answer;
    }
}
