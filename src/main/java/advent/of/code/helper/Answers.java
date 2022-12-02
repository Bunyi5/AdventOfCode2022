package advent.of.code.helper;

public enum Answers {
    TASK_1_1_TEST (24000),
    TASK_1_2_TEST (45000),
    TASK_1_1_REAL (70720),
    TASK_1_2_REAL (207148),

    TASK_2_1_TEST (15),
    TASK_2_2_TEST (12),
    TASK_2_1_REAL (15632),
    TASK_2_2_REAL (14416);

    private final Object answer;

    Answers(Object answer) {
        this.answer = answer;
    }

    public Object getAnswer() {
        return answer;
    }
}
