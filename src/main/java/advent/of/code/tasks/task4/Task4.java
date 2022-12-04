package advent.of.code.tasks.task4;

import java.io.IOException;
import java.util.List;

import advent.of.code.helper.Helper;
import advent.of.code.helper.RunType;

public class Task4 {

    private static final int TASK_NUMBER = 4;

    public static void main(String[] args) throws IOException {
        runMain(RunType.TEST);
        runMain(RunType.REAL);
    }

    private static void runMain(RunType runType) throws IOException {
        System.out.println(runType);
        List<String> assigmentPairs = Helper.convertTxtToStringList(TASK_NUMBER, runType, Helper.LINE_SEPARATOR);

        int fullyOverlappingAssignments = getOverlappingAssignments(assigmentPairs, true);
        System.out.println("Fully overlapping assignments: " + fullyOverlappingAssignments);
        Helper.assertResults(fullyOverlappingAssignments, TASK_NUMBER, 1, runType);

        int overlappingAssignments = getOverlappingAssignments(assigmentPairs, false);
        System.out.println("Overlapping assignments: " + overlappingAssignments);
        Helper.assertResults(overlappingAssignments, TASK_NUMBER, 2, runType);
    }

    private static int getOverlappingAssignments(List<String> assigmentPairs, boolean fullyOverlap) {
        int overLappingAssignments = 0;

        for (String assigmentPairString : assigmentPairs) {
            String[] assignmentPair = assigmentPairString.split(Helper.COMMA);
            String[] firstAssigment = assignmentPair[0].split(Helper.DASH);
            String[] secondAssigment = assignmentPair[1].split(Helper.DASH);

            int firstAssigment1 = Integer.parseInt(firstAssigment[0]);
            int firstAssigment2 = Integer.parseInt(firstAssigment[1]);
            int secondAssigment1 = Integer.parseInt(secondAssigment[0]);
            int secondAssigment2 = Integer.parseInt(secondAssigment[1]);

            if (isAssignmentsOverlap(firstAssigment1, firstAssigment2, secondAssigment1, secondAssigment2, fullyOverlap) ||
                isAssignmentsOverlap(secondAssigment1, secondAssigment2, firstAssigment1, firstAssigment2, fullyOverlap)) {
                overLappingAssignments++;
            }
        }
        return overLappingAssignments;
    }

    private static boolean isAssignmentsOverlap(
        int assigment1Pair1,
        int assigment1Pair2,
        int assigment2Pair1,
        int assigment2Pair2,
        boolean fullyOverLap
    ) {
        boolean assigmentPair2ContainsAssigment1 = isAssigmentPairContainsAssigment(assigment1Pair1, assigment2Pair1, assigment2Pair2);
        boolean assigmentPair1ContainsAssigment2 = isAssigmentPairContainsAssigment(assigment1Pair2, assigment2Pair1, assigment2Pair2);

        return fullyOverLap ?
            assigmentPair2ContainsAssigment1 && assigmentPair1ContainsAssigment2 :
            assigmentPair2ContainsAssigment1 || assigmentPair1ContainsAssigment2;
    }

    private static boolean isAssigmentPairContainsAssigment(int assigment, int assigmentPair1, int assigmentPair2) {
        return assigment >= assigmentPair1 &&
            assigment <= assigmentPair2;
    }
}
