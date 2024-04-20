public class SixthQ {
    public int[] solution(int money) {
        int[] answwer = new int[2];

        answwer[0] = money / 5500;
        answwer[1] = money % 5500;

        return answwer;
    }
}
