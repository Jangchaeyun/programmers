import java.util.HashMap;
import java.util.Map;

public class TheBeautyOfStrings {
    public long solution(String s) {
        Map<Character, Map<Integer, Integer>> countByLenByCh = new HashMap<>();

        char[] chars = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = chars[i];
            int len = 1;
            while (i + 1 < n && chars[i + 1] == ch) {
                len++;
                i++;
            }
            if (!countByLenByCh.containsKey(ch)) {
                countByLenByCh.put(ch, new HashMap<>());
            }
            Map<Integer, Integer> countByLen = countByLenByCh.get(ch);
            countByLen.put(len, countByLen.getOrDefault(len, 0) + 1);
        }

        long answer = (long) (n - 1) * n * (n + 1) / 6;
        for (Map<Integer, Integer> countByLen : countByLenByCh.values()) {
            int t = countByLen.values().stream().mapToInt(Integer :: intValue).sum();
            int eSum = 0;
            for (Map.Entry<Integer, Integer> entry : countByLen.entrySet()) {
                int l = entry.getKey();
                int count = entry.getValue();
                eSum += l * count;
                answer -= (long) eSum * (eSum - 1) / 2;
                eSum -= t;
                t -= count;
            }
        }

        return answer;
    }
}
