package advent.of.code.tasks.task7;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task7 {

    private static final int TASK_NUMBER = 7;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        String[] fileSystem = Helper.convertTxtToStringArray(TASK_NUMBER, runType, Helper.LINE_SEPARATOR);
        Map<String, Integer> directorySizeMap = new HashMap<>();
        fillDirectorySizeMap(fileSystem, directorySizeMap);

        int sumOfTotalDirectories = getSumOfTotalDirectories(directorySizeMap);
        System.out.println("Sum of total directories: " + sumOfTotalDirectories);
        Helper.assertResults(sumOfTotalDirectories, TASK_NUMBER, 1, runType);

        int smallestDirectoryThatFreesUpEnoughSpace = smallestDirectoryThatFreesUpEnoughSpace(directorySizeMap);
        System.out.println("Sum of total directories: " + smallestDirectoryThatFreesUpEnoughSpace);
        Helper.assertResults(smallestDirectoryThatFreesUpEnoughSpace, TASK_NUMBER, 2, runType);
    }

    private static void fillDirectorySizeMap(String[] fileSystem, Map<String, Integer> directorySizeMap) {
        LinkedList<String> currentDirectoryDepth = new LinkedList<>();

        for (String line : fileSystem) {
            if (line.contains("$ cd ..")) {
                currentDirectoryDepth.removeFirst();
            } else if (line.contains("$ cd ")) {
                String directory = line.replaceAll("\\$ cd ", "");
                if (directorySizeMap.containsKey(directory)) {
                    directory = directory.concat(String.valueOf(Math.random()));
                }
                currentDirectoryDepth.push(directory);
                directorySizeMap.put(directory, 0);
            } else if (Character.isDigit(line.charAt(0))) {
                Integer fileSize = Integer.parseInt(line.split(Helper.WHITESPACE)[0]);
                currentDirectoryDepth.forEach(directory ->
                    directorySizeMap.put(directory, directorySizeMap.get(directory) + fileSize)
                );
            }
        }
    }

    private static int getSumOfTotalDirectories(Map<String, Integer> directorySizeMap) {
        return directorySizeMap.values().stream()
            .filter(directorySize -> directorySize <= 100000)
            .mapToInt(Integer::intValue)
            .sum();
    }

    private static int smallestDirectoryThatFreesUpEnoughSpace(Map<String, Integer> directorySizeMap) {
        int totalSize = 70000000;
        int requiredSpace = 30000000;
        int deleteSizeAtLeast = requiredSpace - (totalSize - directorySizeMap.get("/"));
        return directorySizeMap.values().stream()
            .filter(directorySize -> directorySize > deleteSizeAtLeast)
            .mapToInt(Integer::intValue)
            .min().orElseThrow();
    }
}
