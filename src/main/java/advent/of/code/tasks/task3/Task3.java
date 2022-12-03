package advent.of.code.tasks.task3;

import java.io.IOException;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task3 {

    private static final int TASK_NUMBER = 3;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> rucksacks = Helper.convertTxtToStringList(TASK_NUMBER, runType, Helper.LINE_SEPARATOR);

        int sumOfRucksackPriorities = getSumOfRucksackPriorities(rucksacks);
        System.out.println("Sum of rucksack priorities: " + sumOfRucksackPriorities);
        Helper.assertResults(sumOfRucksackPriorities, TASK_NUMBER, 1, runType);

        int sumOfElfPriorities = getSumOfElfPriorities(rucksacks);
        System.out.println("Sum of rucksack priorities: " + sumOfElfPriorities);
        Helper.assertResults(sumOfElfPriorities, TASK_NUMBER, 2, runType);
    }

    private static int getSumOfRucksackPriorities(List<String> rucksacks) {
        int sumOfPriorities = 0;

        for (String rucksack : rucksacks) {
            int length = rucksack.length();
            String firstHalf = rucksack.substring(0, length / 2);
            String secondHalf = rucksack.substring(length / 2, length);

            for (char item : firstHalf.toCharArray()) {
                if (secondHalf.contains(Character.toString(item))) {
                    sumOfPriorities = addItemToSumOfPriorities(sumOfPriorities, item);
                    break;
                }
            }
        }

        return sumOfPriorities;
    }

    private static int getSumOfElfPriorities(List<String> rucksacks) {
        int sumOfPriorities = 0;

        for (int i = 0; i < rucksacks.size(); i += 3) {
            for (char item : rucksacks.get(i).toCharArray()) {
                if (rucksacks.get(i + 1).contains(Character.toString(item)) &&
                    rucksacks.get(i + 2).contains(Character.toString(item))
                ) {
                    sumOfPriorities = addItemToSumOfPriorities(sumOfPriorities, item);
                    break;
                }
            }
        }

        return sumOfPriorities;
    }

    private static int addItemToSumOfPriorities(int sumOfPriorities, char item) {
        if (Character.isUpperCase(item)) {
            sumOfPriorities += item - ((int) 'A') + 27;
        } else {
            sumOfPriorities += item - ((int) 'a') + 1;
        }
        return sumOfPriorities;
    }


}
