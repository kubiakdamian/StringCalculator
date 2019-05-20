package pl.qbsapps;

import org.apache.commons.lang3.StringUtils;

public class Calculator {
    public int add(String numbers) {
        int sum = 0;
        String[] result;

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
            sum += Integer.parseInt(value);
        }

        return sum;
    }
}
