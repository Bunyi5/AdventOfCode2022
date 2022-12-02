package advent.of.code.tasks.task1;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task1 {

    private static final int TASK_NUMBER = 1;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> elfCaloriesStringList = Helper.convertTxtToStringList(TASK_NUMBER, runType, Helper.LINE_SEPARATOR + Helper.LINE_SEPARATOR);

        int mostCalories = getMostCalories(elfCaloriesStringList);
        System.out.println("Most calories: " + mostCalories);
        Helper.assertResults(mostCalories, TASK_NUMBER, 1, runType);

        int mostThreeCaloriesSum = getMostThreeCaloriesSum(elfCaloriesStringList);
        System.out.println("Most three calories sum: " + mostThreeCaloriesSum);
        Helper.assertResults(mostThreeCaloriesSum, TASK_NUMBER, 2, runType);
    }

    private static int getMostCalories(List<String> elfCaloriesStringList) {
        int max = 0;
        for (String elfCaloriesString : elfCaloriesStringList) {
            int elfCalorie = Arrays.stream(elfCaloriesString.split(Helper.LINE_SEPARATOR))
                .mapToInt(Integer::parseInt)
                .sum();

            if (elfCalorie > max) {
                max = elfCalorie;
            }
        }

        return max;
    }

    private static int getMostThreeCaloriesSum(List<String> elfCaloriesStringList) {
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        for (String elfCaloriesString : elfCaloriesStringList) {
            int elfCalorie = Arrays.stream(elfCaloriesString.split(Helper.LINE_SEPARATOR))
                .mapToInt(Integer::parseInt)
                .sum();

            if (elfCalorie > max1) {
                max3 = max2;
                max2 = max1;
                max1 = elfCalorie;
            } else if (elfCalorie > max2) {
                max3 = max2;
                max2 = elfCalorie;
            } else if (elfCalorie > max3) {
                max3 = elfCalorie;
            }
        }

        return max1 + max2 + max3;
    }
}
