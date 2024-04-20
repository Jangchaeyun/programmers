import java.util.stream.IntStream;

public class SecondQ {
    public int solution(int a, int d, boolean[] included) {
        return IntStream.range(0, included.length).map(i -> included[i] == true ? (a + (i * d)) : 0).sum();
    }
}
