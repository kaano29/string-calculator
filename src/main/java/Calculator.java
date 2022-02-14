import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public int addition(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.contains(",\n") || s.contains("\n,")) {
            throw new NumberFormatException("wrong format");
        }
        List<Integer> resultList = extractNumbers(s);
        List<Integer> negativeNumbers = resultList.stream().filter(num -> num < 0).collect(Collectors.toList());
        if (negativeNumbers.size() > 0) {
            throw new NumberFormatException("negatives not allowed:" + negativeNumbers);

        }
        return resultList.stream().reduce(0, (total, element) -> total + element);
    }


    private List<Integer> extractNumbers(String s) {
        String delimiter = "";
        if (s.contains("//")) {
            int index = s.indexOf("\n");
            delimiter += "(" +  s.substring(2, index) + ")+|";
            s = s.substring(index + 1);
        }

        List<Integer> resultList = Arrays.stream(s.split( delimiter + ",|\n")).map(Integer::parseInt).filter(num -> num < 1000).collect(Collectors.toList());
        return resultList;
    }

}
