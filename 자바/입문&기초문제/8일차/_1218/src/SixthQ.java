public class SixthQ {
    public int solution(int[] num_list) {
        int sum_odd = 0;
        int sum_even = 0;
        for (int i = 0; i < num_list.length; i++) {
            if (i % 2 == 0) {
                sum_odd += num_list[i];
            }
            else {
                sum_even += num_list[i];
            }
        }
        if (sum_odd > sum_even) {
            return sum_odd;
        }
        else return sum_even;
    }
}
