package ulearn.practice4.task2.ready;

import org.springframework.stereotype.Component;
import ulearn.practice4.task2.IFormula;

import java.util.Hashtable;

//JustForTests
@Component()
public class ThirdNewtonLaw implements IFormula
{
    @Override
    public double Calculate(Hashtable<String, Double> params, String unknownParameter)
    {
        return switch (unknownParameter)
        {
            case "F1" ->
            {
                Utils.checkParams(params, "F1", new String[]{"F2"}, new String[0]);
                yield -params.get("F2");
            }
            case "F2" ->
            {
                Utils.checkParams(params, "F2", new String[]{"F1"}, new String[0]);
                yield -params.get("F1");
            }
            default -> throw new IllegalArgumentException(
                    String.format("Possible params are F1,F2; but was %s", unknownParameter));
        };
    }
}
