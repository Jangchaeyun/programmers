public class Base_station_installation {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int si = 0;
        int position = 1;
        while (position <= n) {
            if (si < stations.length && position >= stations[si] - w) {
                position = stations[si] + w + 1;
                si++;
            }
            else {
                answer += 1;
                position += (w * 2) + 1;
            }
        }
        return answer;
    }
}
