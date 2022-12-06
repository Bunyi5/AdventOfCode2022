package advent.of.code.tasks.task6;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task6 {

    private static final int TASK_NUMBER = 6;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        String[] dataStream = Helper.convertTxtToStringArray(TASK_NUMBER, runType, Helper.NONE);

        int sizeOfPocket4 = 4;
        int startOfFirstPacket4 = getStartOfFirstPacket(dataStream, sizeOfPocket4);
        System.out.println("Start of first packet with " + sizeOfPocket4 + ": " + startOfFirstPacket4);
        Helper.assertResults(startOfFirstPacket4, TASK_NUMBER, 1, runType);

        int sizeOfPocket14 = 14;
        int startOfFirstPacket14 = getStartOfFirstPacket(dataStream, sizeOfPocket14);
        System.out.println("Start of first packet with " + sizeOfPocket14 + ": " + startOfFirstPacket14);
        Helper.assertResults(startOfFirstPacket14, TASK_NUMBER, 2, runType);
    }

    private static int getStartOfFirstPacket(String[] dataStream, int sizeOfPocket) {
        Set<String> testArray;

        for (int i = 0; i < dataStream.length - sizeOfPocket; i++) {
            testArray = Arrays.stream(Arrays.copyOfRange(dataStream, i, i + sizeOfPocket)).collect(Collectors.toSet());
            if (testArray.size() == sizeOfPocket) {
                return i + sizeOfPocket;
            }
        }

        return 0;
    }
}
