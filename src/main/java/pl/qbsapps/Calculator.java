package pl.qbsapps;

import org.apache.commons.lang3.StringUtils;
import pl.qbsapps.exception.NegativesNotAllowedException;

import java.util.ArrayList;

public class Calculator {
    public int add(String numbers) {
        String[] result;

        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        if (numbers.startsWith("//")) {
            String delimiter = retrieveDelimiter(numbers);

            numbers = numbers.substring(numbers.lastIndexOf("\n") + 1);

            result = numbers.split(delimiter);
        } else {
            result = numbers.split("[,\n]");
        }


        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        checkIfNegativesOccur(result);

        if (result.length == 1) {
            return Integer.parseInt(result[0]);
        }

        return makeCalculations(result);
    }

    private String retrieveDelimiter(String numbers) {
        String delimiters[];
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = StringUtils.substringBetween(numbers, "//", "\n");

        if (delimiter.contains("[")) {
            delimiters = StringUtils.substringsBetween(numbers, "[", "]");

            for (String s : delimiters) {
                stringBuilder.append(s).append("|");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            delimiter = stringBuilder.toString();
        }

        return delimiter;
    }

    private int makeCalculations(String[] result) {
        int sum = 0;

        for (String value : result) {
            int parsedValue = Integer.parseInt(value);

            if (parsedValue <= 1000) {
                sum += parsedValue;
            }
        }

        return sum;
    }

    private void checkIfNegativesOccur(String[] result) {
        ArrayList<Integer> negativeNumbers = new ArrayList<>();

        for (String value : result) {
            int parsedValue = Integer.parseInt(value);

            if (parsedValue < 0) {
                negativeNumbers.add(parsedValue);
            }
        }

        for (Integer number : negativeNumbers) {
            if (number < 0) {
                throw new NegativesNotAllowedException("Negatives not allowed " + negativeNumbers);
            }
        }
    }
}
