package advent.of.code.tasks.task8;

import java.io.IOException;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task8 {

    private static final int TASK_NUMBER = 8;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        int[][] forest = Helper.convertTxtToIntMatrix(TASK_NUMBER, runType, Helper.NONE);

        int visibleFromTheOutside = getVisibleFromTheOutside(forest);
        System.out.println("Visible from the outside: " + visibleFromTheOutside);
        Helper.assertResults(visibleFromTheOutside, TASK_NUMBER, 1, runType);

        int highestScenicScore = getHighestScenicScore(forest);
        System.out.println("Highest scenic score: " + highestScenicScore);
        Helper.assertResults(highestScenicScore, TASK_NUMBER, 2, runType);
    }

    private static int getVisibleFromTheOutside(int[][] forest) {
        int counter = 0;
        int currentTree;

        int columnLength = forest.length;
        int rowLength = forest[0].length;
        for (int i = 0; i < columnLength; i++) {
            for (int j = 0; j < rowLength; j++) {
                if (i == 0 || j == 0 || i == columnLength - 1 || j == rowLength - 1) {
                    counter++;
                    continue;
                }

                currentTree = forest[i][j];
                if (checkColumn(forest, currentTree, 0, i, j) ||
                    checkColumn(forest, currentTree, i + 1, columnLength, j) ||
                    checkRow(forest, currentTree, 0, j, i) ||
                    checkRow(forest, currentTree, j + 1, rowLength, i)
                ) {
                    counter++;
                }
            }
        }

        return counter;
    }

    private static boolean checkColumn(int[][] forest, int currentTree, int start, int until, int column) {
        boolean result = true;

        for (int k = start; k < until; k++) {
            if (currentTree <= forest[k][column]) {
                result = false;
                break;
            }
        }

        return result;
    }

    private static boolean checkRow(int[][] forest, int currentTree, int start, int until, int row) {
        boolean result = true;

        for (int k = start; k < until; k++) {
            if (currentTree <= forest[row][k]) {
                result = false;
                break;
            }
        }

        return result;
    }

    private static int getHighestScenicScore(int[][] forest) {
        int highestScenicScore = 0;
        int currentTree;
        int currentScenicScore;

        int columnLength = forest.length;
        int rowLength = forest[0].length;
        for (int i = 0; i < columnLength; i++) {
            for (int j = 0; j < rowLength; j++) {
                currentTree = forest[i][j];

                if (!(i == 0 || j == 0 || i == columnLength - 1 || j == rowLength - 1)) {
                    currentScenicScore = countViewingDistanceUp(forest, currentTree, i - 1, j) *
                        countViewingDistanceDown(forest, currentTree, i + 1, columnLength, j) *
                        countViewingDistanceLeft(forest, currentTree, j - 1, i) *
                        countViewingDistanceRight(forest, currentTree, j + 1, rowLength, i);

                    if (highestScenicScore < currentScenicScore) {
                        highestScenicScore = currentScenicScore;
                    }
                }
            }
        }

        return highestScenicScore;
    }

    private static int countViewingDistanceUp(int[][] forest, int currentTree, int start, int column) {
        int viewingDistance = 0;

        for (int k = start; k >= 0; k--) {
            if (forest[k][column] >= currentTree) {
                viewingDistance++;
                break;
            } else {
                viewingDistance++;
            }
        }

        return viewingDistance;
    }

    private static int countViewingDistanceDown(int[][] forest, int currentTree, int start, int until, int column) {
        int viewingDistance = 0;

        for (int k = start; k < until; k++) {
            if (forest[k][column] >= currentTree) {
                viewingDistance++;
                break;
            } else {
                viewingDistance++;
            }
        }

        return viewingDistance;
    }

    private static int countViewingDistanceLeft(int[][] forest, int currentTree, int start, int row) {
        int viewingDistance = 0;

        for (int k = start; k >= 0; k--) {
            if (forest[row][k] >= currentTree) {
                viewingDistance++;
                break;
            } else {
                viewingDistance++;
            }
        }

        return viewingDistance;
    }

    private static int countViewingDistanceRight(int[][] forest, int currentTree, int start, int until, int row) {
        int viewingDistance = 0;

        for (int k = start; k < until; k++) {
            if (forest[row][k] >= currentTree) {
                viewingDistance++;
                break;
            } else {
                viewingDistance++;
            }
        }

        return viewingDistance;
    }
}
