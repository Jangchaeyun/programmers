import java.util.Arrays;

public class DesignAFunRacingStadium {
    public int solution(int[] heights) {
        Arrays.sort(heights);

        int maxDifference = Integer.MAX_VALUE;
        for (int i = 1; i < heights.length; i++) {
            int difference = Math.abs(heights[i] - heights[i - 1]);
            maxDifference = Math.max(maxDifference, difference);
        }
        return maxDifference;
    }
}
