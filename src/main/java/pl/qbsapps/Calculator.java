package pl.qbsapps;

import org.apache.commons.lang3.StringUtils;
import pl.qbsapps.exception.NegativesNotAllowedException;

import java.util.ArrayList;

public class Calculator {
    public int add(String numbers) {
        int sum = 0;
        String[] result;
        ArrayList<Integer> negativeNumbers = new ArrayList<>();

        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        if (numbers.startsWith("//")) {
            String delimiter = StringUtils.substringBetween(numbers, "//", "\n");
            numbers = numbers.substring(numbers.lastIndexOf("\n") + 1);
            result = numbers.split(delimiter);
        } else {
            result = numbers.split("[,\n]");
        }


        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        if (result.length == 1) {
            return Integer.parseInt(result[0]);
        }

        for (String value : result) {
            int parsedValue = Integer.parseInt(value);
            sum += parsedValue;

            if (parsedValue < 0) {
                negativeNumbers.add(parsedValue);
            }
        }

        for (Integer number : negativeNumbers) {
            if (number < 0) {
                throw new NegativesNotAllowedException("Negatives not allowed " + negativeNumbers);
            }
        }

        return sum;
    }
}
