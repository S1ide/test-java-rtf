package ulearn.practice4.task2;

import java.util.Hashtable;

public interface IFormula
{
    double Calculate(Hashtable<String, Double> parameters, String unknownParameter);
}
