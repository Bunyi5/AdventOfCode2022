package advent.of.code.tasks.task5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task5 {

    private static final int TASK_NUMBER = 5;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        String[] drawing = Helper.convertTxtToStringArray(TASK_NUMBER, runType, Helper.LINE_SEPARATOR + Helper.LINE_SEPARATOR);

        String topCratesForCrateMover9000 = getTopCrates(drawing, false);
        System.out.println("Top crates for CrateMover9000: " + topCratesForCrateMover9000);
        Helper.assertResults(topCratesForCrateMover9000, TASK_NUMBER, 1, runType);

        String topCratesForCrateMover9001 = getTopCrates(drawing, true);
        System.out.println("Top crates for CrateMover9001: " + topCratesForCrateMover9001);
        Helper.assertResults(topCratesForCrateMover9001, TASK_NUMBER, 2, runType);
    }

    private static String getTopCrates(String[] split, boolean crateMover9001) {
        StringBuilder topCrates = new StringBuilder();

        List<Stack<Character>> stacks = createStacksFromCrates(split[0]);
        rearrangeCrates(stacks, split[1], crateMover9001);

        for (Stack<Character> stack : stacks) {
            topCrates.append(stack.pop());
        }

        return topCrates.toString();
    }

    private static List<Stack<Character>> createStacksFromCrates(String stackWithNumbers) {
        List<Stack<Character>> stackList = new ArrayList<>();

        String[] crateLines = stackWithNumbers
            .substring(0, stackWithNumbers.lastIndexOf("\n"))
            .split(Helper.LINE_SEPARATOR);

        for (int i = crateLines.length - 1; i > -1; i--) {

            String[] crateLine = crateLines[i].split("(?<=\\G.{4})");

            if (i == crateLines.length - 1) {
                for (String crate : crateLine) {
                    Stack<Character> stack = new Stack<>();
                    stack.push(crate.charAt(1));
                    stackList.add(stack);
                }
                continue;
            }

            for (int j = 0; j < crateLine.length; j++) {
                if (!crateLine[j].isBlank()) {
                    stackList.get(j).push(crateLine[j].charAt(1));
                }
            }
        }

        return stackList;
    }

    private static void rearrangeCrates(List<Stack<Character>> stacks, String movements, boolean crateMover9001) {
        for (String movement : movements.split(Helper.LINE_SEPARATOR)) {
            int[] movementNumbers = Arrays.stream(movement.replaceAll("move ", "")
                    .split("\\sfrom\\s|\\sto\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();

            if (crateMover9001) {
                Character[] tempCrates = new Character[movementNumbers[0]];
                for (int i = 0; i < movementNumbers[0]; i++) {
                    tempCrates[i] = (stacks.get(movementNumbers[1] - 1).pop());
                }
                for (int i = tempCrates.length - 1; i > -1; i--) {
                    stacks.get(movementNumbers[2] - 1).push(tempCrates[i]);
                }
            } else {
                for (int i = 0; i < movementNumbers[0]; i++) {
                    if (!stacks.get(movementNumbers[1] - 1).isEmpty()) {
                        Character pop = stacks.get(movementNumbers[1] - 1).pop();
                        stacks.get(movementNumbers[2] - 1).push(pop);
                    }
                }
            }
        }
    }
}
