import java.util.ArrayList;
import java.util.Arrays;

public class SeventhQ {
    public String[] solution(String myString) {
        String[] splitArr = myString.split("x");
        ArrayList<String> resultList = new ArrayList<>();

        for (String str : splitArr) {
            if (!str.isEmpty()) {
                resultList.add(str);
            }
        }

        String[] resultArr = resultList.toArray(new String[resultList.size()]);
        Arrays.sort(resultArr);

        return resultArr;
    }
}