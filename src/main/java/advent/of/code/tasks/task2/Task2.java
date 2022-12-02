package advent.of.code.tasks.task2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task2 {

    private static final int TASK_NUMBER = 2;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        String matchesString = Helper.convertTxtToStringContent(TASK_NUMBER, runType);

        int totalScore = getTotalScore(matchesString);
        System.out.println("Total score: " + totalScore);
        Helper.assertResults(totalScore, TASK_NUMBER, 1, runType);

        int correctTotalScore = getCorrectTotalScore(matchesString);
        System.out.println("Correct total score: " + correctTotalScore);
        Helper.assertResults(correctTotalScore, TASK_NUMBER, 2, runType);
    }

    private static int getTotalScore(String matchesString) {
        String replacedString = matchesString
            .replaceAll("A", "1")
            .replaceAll("B", "2")
            .replaceAll("C", "3")
            .replaceAll("Y", "2")
            .replaceAll("X", "1")
            .replaceAll("Z", "3");

        List<String> matchesStringList = Arrays.stream(replacedString.split(Helper.LINE_SEPARATOR)).toList();

        return getTotalScore(matchesStringList);
    }

    private static int getCorrectTotalScore(String matchesString) {
        String replacedString = matchesString
            .replaceAll("A", "1")
            .replaceAll("B", "2")
            .replaceAll("C", "3");

        List<String> matchesStringList = Arrays.stream(replacedString.split(Helper.LINE_SEPARATOR))
            .map(matchString -> {
                String[] matchArray = matchString.split(Helper.WHITESPACE);
                switch (matchArray[1]) {
                    case "Y":
                        return matchString.replace("Y", matchArray[0]);
                    case "X":
                        int toLose;
                        if (Integer.parseInt(matchArray[0]) == 1) {
                            toLose = 2;
                        } else {
                            toLose = -1;
                        }
                        return matchString.replace("X", String.valueOf(Integer.parseInt(matchArray[0]) + toLose));
                    case "Z":
                        int toWin;
                        if (Integer.parseInt(matchArray[0]) == 3) {
                            toWin = -2;
                        } else {
                            toWin = 1;
                        }
                        return matchString.replace("Z", String.valueOf(Integer.parseInt(matchArray[0]) + toWin));
                }
                return matchString;
            }).toList();

        return getTotalScore(matchesStringList);
    }


    private static int getTotalScore(List<String> matchesStringList) {
        int totalScore = 0;

        for (String matchString : matchesStringList) {

            String[] match = matchString.split(Helper.WHITESPACE);

            int match1 = Integer.parseInt(match[0]);
            int match2 = Integer.parseInt(match[1]);
            if (match1 == match2) {
                totalScore += 3 + match2;
            } else if (match1 + 1 == match2 || match1 - 2 == match2) {
                totalScore += 6 + match2;
            } else {
                totalScore += match2;
            }
        }
        return totalScore;
    }
}
