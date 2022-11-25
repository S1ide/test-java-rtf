package U02_Base_Syntax_Types.task;

import org.junit.Assert;
import org.junit.internal.RealSystem;
import org.junit.internal.TextListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
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
        Result result = junit.run(UnitTestS019.class); // имя класса, который хотим тестировать
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

public class UnitTestS019{
    @Test
    @DisplayName("Проверка calculate «Сложение чисел»")
    public void firstTest(){
        Assertions.assertEquals(28, Double.parseDouble(Calculator.calculate("23 + 5")));
    }

    @Test
    @DisplayName("Проверка calculate «Вычитание чисел»")
    public void secondTest(){
        Assertions.assertEquals(18, Double.parseDouble(Calculator.calculate("23 - 5")));
    }

    @Test
    @DisplayName("Проверка calculate «Деление чисел»")
    public void thirdTest(){
        Assertions.assertEquals(15.5, Double.parseDouble(Calculator.calculate("31 / 2")));
    }

    @Test
    @DisplayName("Проверка calculate «Сложение строк»")
    public void fourthTest(){
        Assertions.assertEquals("abCaBc", Calculator.calculate("abC + aBc"));
    }

    @Test
    @DisplayName("Проверка calculate «Вычитание строк»")
    public void fifthTest(){
        Assertions.assertEquals("a", Calculator.calculate("abC - bC"));
    }

    @Test
    @DisplayName("Проверка calculate «Вычитание строк, неверный формат»")
    public void sixthTest(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> Calculator.calculate("abC - abc"));
    }

    @Test
    @DisplayName("Проверка calculate «Вычитание строк, неверный формат»")
    public void seventhTest(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> Calculator.calculate("abC / abc"));
    }

    @Test
    @DisplayName("Проверка getNumbers")
    public void getNumberTest() {
        Assertions.assertEquals(2, Calculator.getNumbers(114, 335));
    }

    @Test
    @DisplayName("Проверка getNumbers — большое число")
    public void BigNumberTest() {
        Assertions.assertEquals(3, Calculator.getNumbers(345345390, 12341209));
    }

    @Test
    @DisplayName("Проверка getNumbers — отрицательное число")
    public void NegativeNumberTest() {
        Assertions.assertEquals(1, Calculator.getNumbers(-114, -937));
    }

    @Test
    @DisplayName("Проверка getMinimalType — Byte")
    public void getMinimalByteTest() {
        Assertions.assertEquals("Byte", Calculator.getMinimalType(String.valueOf(Byte.MAX_VALUE)));
    }

    @Test
    @DisplayName("Проверка getMinimalType — Short")
    public void getMinimalShortTest() {
        Assertions.assertEquals("Short", Calculator.getMinimalType(String.valueOf(Short.MAX_VALUE)));
    }

    @Test
    @DisplayName("Проверка getMinimalType — Int")
    public void getMinimalIntTest() {
        Assertions.assertEquals("Int", Calculator.getMinimalType(String.valueOf(Integer.MAX_VALUE)));
    }

    @Test
    @DisplayName("Проверка getMinimalType — Long")
    public void getMinimalLongTest() {
        Assertions.assertEquals("Long", Calculator.getMinimalType(String.valueOf(Long.MAX_VALUE)));
    }

}
//#region Task
public class Calculator {
    public static String calculate(String input) {
        //TODO напишите метод Calculate, который будет получать строку в формате <первый аргумент> <операция> <второй аргумент>
        //разделенными пробелом и отправлять в нужные методы
    }

    private static String calculate(String a, String b, String operation) {
        //TODO напишите метод для складывания, либо вычитания строк
        //Важно: для вычитания строки, они должны совпадать по регистру
    }

    private static double calculate(double a, double b, String operation) {
        //TODO напишите метод для работы с числами со следующими операциями: +, -, /, *, %
    }

    public static int getNumbers(int a, int b) {
        //TODO напишите метод принимает два аргумента, складывает их и возращает кол-во четных цифр в сумме
    }

    public static String getMinimalType(String input) {
        //TODO напишите метод получается число в формате строки и возращает минимальный целочисленный тип, к которому его можно привести, Long, Int, Short или Byte
    }
}
//#endregion Task
