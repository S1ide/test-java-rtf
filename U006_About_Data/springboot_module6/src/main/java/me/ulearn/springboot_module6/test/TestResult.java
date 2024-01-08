package me.ulearn.springboot_module6.test;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class TestResult {
    private final TestVerdict Verdict;
    private final String Output;

    public TestResult(TestVerdict verdict, String output) {
        Verdict = verdict;
        Output = output == null ? "" : output;
    }
}
