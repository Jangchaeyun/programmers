public class SeventhQ {
    public int solution(int[] sides) {
        int bigValue = Math.max(sides[0], sides[1]);
        int smallValue = Math.min(sides[0], sides[1]);

        int lowLimit = bigValue - smallValue;
        int heighLimit = bigValue + smallValue;

        return heighLimit - lowLimit - 1;
    }
}
