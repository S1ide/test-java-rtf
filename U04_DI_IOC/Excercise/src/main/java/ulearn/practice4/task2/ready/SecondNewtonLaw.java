package ulearn.practice4.task2.ready;

import org.springframework.stereotype.Component;
import ulearn.practice4.task2.IFormula;

import java.util.Hashtable;

//# region Task2-4
@Component()
public class SecondNewtonLaw implements IFormula
{
    private String[] notNegative = new String[] {"m"};
    private String[] needed = new String[] {"F", "m", "a"};


    @Override
    public double Calculate(Hashtable<String, Double> params, String unknownParameter)
    {
        return switch (unknownParameter)
                {
                    case "F" -> getForce(params);
                    case "m" -> getMass(params);
                    case "a" -> getAcceleration(params);
                    default -> throw new IllegalArgumentException(
                            String.format("Possible params are F,m,a; but was %s", unknownParameter));
                };
    }

    private double getForce(Hashtable<String, Double> params)
    {
        Utils.checkParams(params, "F", needed, notNegative);
        return params.get("m") * params.get("a");
    }

    private double getMass(Hashtable<String, Double> params)
    {
        Utils.checkParams(params, "m", needed, notNegative);
        return params.get("F") / params.get("a");
    }

    private double getAcceleration(Hashtable<String, Double> params)
    {
        Utils.checkParams(params, "a", needed, notNegative);
        return params.get("F") / params.get("m");
    }
}
//# endregion Task2-4