package ulearn.practice4.task2.ready;

import org.springframework.stereotype.Component;
import ulearn.practice4.task2.IFormula;

import java.util.Hashtable;

//# region Task2-1
@Component()
public class Momentum implements IFormula
{
    private String[] notNegative = new String[] {"m"};
    private String[] needed = new String[] {"p", "m", "v"};

    @Override
    public double Calculate(Hashtable<String, Double> params, String unknownParameter)
    {
        return switch (unknownParameter)
                {
                    case "p" -> getMomentum(params);
                    case "m" -> getMass(params);
                    case "v" -> getVelocity(params);
                    default -> throw new IllegalArgumentException(
                            String.format("Possible params are p,m,v; but was %s", unknownParameter));
                };
    }

    private double getMomentum(Hashtable<String, Double> params)
    {
        Utils.checkParams(params, "p", needed, notNegative);
        return params.get("m") * params.get("v");
    }

    private double getMass(Hashtable<String, Double> params)
    {
        Utils.checkParams(params, "m", needed, notNegative);
        return params.get("p") / params.get("v");
    }

    private double getVelocity(Hashtable<String, Double> params)
    {
        Utils.checkParams(params, "v", needed, notNegative);
        return params.get("p") / params.get("m");
    }
}
//# endregion Task2-1