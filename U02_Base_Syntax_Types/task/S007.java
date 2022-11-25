package U02_Base_Syntax_Types.task;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import com.google.gson.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class JUlearn {
    public static void main(String[] args) {
        var result = RunTest(args);
        Gson gson = new Gson();
        gson.toJson(result);
        System.out.print(gson.toJson(result));
    }

    private static RunResult RunTest(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(UnitTestS018.class); // имя класса, который хотим тестировать
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(outputStream, true, StandardCharsets.UTF_8);
        if (!result.wasSuccessful()) { // если ошибка печатать в консоль информацию
            TextListener listener = new TextListener(newOut);
            listener.testRunFinished(result);
            for (var fail : result.getFailures()) {
                if (!(fail.getException() instanceof AssertionError)) {
                    return new RunResult(Verdict.RuntimeError, outputStream.toString());
                }
            }
            return new RunResult(Verdict.WrongAnswer, outputStream.toString());
        }
        return new RunResult(Verdict.Ok, outputStream.toString());
    }
}

enum Verdict {
    NA,
    Ok, // Означает, что всё штатно протестировалось. Возвращается в том числе если тесты не прошли
    CompilationError,
    RuntimeError,
    SecurityException,
    SandboxError,
    OutputLimit,
    TimeLimit,
    MemoryLimit,
    WrongAnswer;
}

class RunResult {
    public RunResult(Verdict verdict, String output) {
        Verdict = verdict;
        Output = output == null ? "" : output;
    }

    private Verdict Verdict;
    private String Output;
}

public class UnitTestS018 {
    @Test
    public void testEven() throws Exception {
        Assert.assertTrue(Main.check(84226));
        Assert.assertTrue(Main.check(462));
        Assert.assertTrue(Main.check(2));
        Assert.assertTrue(Main.check(88888));
        Assert.assertTrue(Main.check(0));
    }

    @Test
    public void testOdd() throws Exception {
        Assert.assertFalse(Main.check(17395));
        Assert.assertFalse(Main.check(777));
        Assert.assertFalse(Main.check(1));
        Assert.assertFalse(Main.check(99999));
        Assert.assertFalse(Main.check(15));
    }

    @Test
    public void testMixed() throws Exception {
        Assert.assertFalse(Main.check(76234));
        Assert.assertFalse(Main.check(10));
        Assert.assertFalse(Main.check(12345));
        Assert.assertFalse(Main.check(99998));
        Assert.assertFalse(Main.check(88889));
    }
}

public class Main {
    //#region Task
    public static boolean check(int number) {
        while (number > 0) {
            if ((number % 10) % 2 == 0) number /= 10;
            else return false;
        }
        return true;
    }
    //#endregion Task
}