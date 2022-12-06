package advent.of.code.helper;

@SuppressWarnings("unused")
public enum Answers {
    TASK_1_1_TEST (24000),
    TASK_1_2_TEST (45000),
    TASK_1_1_REAL (70720),
    TASK_1_2_REAL (207148),

    TASK_2_1_TEST (15),
    TASK_2_2_TEST (12),
    TASK_2_1_REAL (15632),
    TASK_2_2_REAL (14416),

    TASK_3_1_TEST (157),
    TASK_3_2_TEST (70),
    TASK_3_1_REAL (8243),
    TASK_3_2_REAL (2631),

    TASK_4_1_TEST (2),
    TASK_4_2_TEST (4),
    TASK_4_1_REAL (441),
    TASK_4_2_REAL (861),

    TASK_5_1_TEST ("CMZ"),
    TASK_5_2_TEST ("MCD"),
    TASK_5_1_REAL ("WCZTHTMPS"),
    TASK_5_2_REAL ("BLSGJSDTS"),

    TASK_6_1_TEST (7),
    TASK_6_2_TEST (19),
    TASK_6_1_REAL (1142),
    TASK_6_2_REAL (2803);

    private final Object answer;

    Answers(Object answer) {
        this.answer = answer;
    }

    public Object getAnswer() {
        return answer;
    }
}
