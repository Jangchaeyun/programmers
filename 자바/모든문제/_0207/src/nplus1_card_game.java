import java.util.HashSet;
import java.util.Set;

public class nplus1_card_game {
    Set<Integer> original, additional;
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int len = cards.length;
        original = new HashSet();
        additional = new HashSet();

        int idx = len / 3;
        for (int i = 0; i < idx; ++i)
            original.add(cards[i]);

        int target = len + 1;
        while (true) {
            answer++;
            if (idx >= len) {
                break;
            }
            additional.add(cards[idx]);
            additional.add(cards[idx + 1]);
            idx += 2;
            boolean flag = false;
            for (int i : original) {
                if (original.contains(target - i)) {
                    original.remove(i);
                    original.remove(target - i);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                if (coin > 0) {
                    for (int i : original) {
                        if (additional.contains(target - i)) {
                            original.remove(i);
                            additional.remove(target - i);
                            --coin;
                            flag = true;
                            break;
                        }
                    }
                }
            }

            if (!flag) {
                if (coin > 1) {
                    for (int i : additional) {
                        if (additional.contains(target - i)) {
                            additional.remove(i);
                            additional.remove(target - i);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    }
                }
            }

            if (!flag)
                break;
        }

        return answer;
    }
}
