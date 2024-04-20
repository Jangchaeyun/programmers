public class CorrectNumberOfParentheses {
    int count;

    public void dfs(int left, int right, int n) {
        if (left < right) return;
        if (left == n && right == n) {
            count++;
            return;
        }

        if (left > n || right > n) return;

        dfs(left + 1, right, n);
        dfs(left, right + 1, n);
    }

    public int solution(int n) {
        count = 0;

        dfs(0, 0, n);
        return count;
    }
}
