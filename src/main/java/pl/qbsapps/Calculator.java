package pl.qbsapps;

public class Calculator {
    public int add(String numbers) {
        int sum = 0;

        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String[] result = numbers.split(",");
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        if(result.length == 1){
            return Integer.parseInt(result[0]);
        }

        for (String value : result) {
            sum += Integer.parseInt(value);
        }

        return sum;
    }
}
