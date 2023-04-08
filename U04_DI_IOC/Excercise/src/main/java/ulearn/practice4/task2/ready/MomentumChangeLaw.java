package ulearn.practice4.task2.ready;

import org.springframework.stereotype.Component;
import ulearn.practice4.task2.IFormula;

import java.util.Hashtable;

//# region Task2-2
@Component()
public class MomentumChangeLaw implements IFormula
{
    private String[] notNegative = new String[] {"m", "t"};
    private String[] needed = new String[] {"F","m","v","v0","t"};

    @Override
    public double Calculate(Hashtable<String, Double> params, String unknownParameter)
    {
        return switch (unknownParameter)
                {
                    case "F" -> getForce(params);
                    case "t" -> getTime(params);
                    case "m" -> getMass(params);
                    case "v" -> getVelocity(params, "v","v0");
                    case "v0" -> getVelocity(params, "v0","v");
                    default -> throw new IllegalArgumentException(
                            String.format("Possible params are F,m,v,v0,t; but was %s", unknownParameter));
                };
    }

    private double getForce(Hashtable<String, Double> params)
    {
        Utils.checkParams(params, "F", needed, notNegative);
        var m = params.get("m");
        var v = params.get("v");
        var v0 = params.get("v0");
        var t = params.get("t");
        return (m * v - m * v0) / t;
    }

    private double getMass(Hashtable<String, Double> params)
    {
        Utils.checkParams(params, "m", needed, notNegative);
        var F = params.get("F");
        var v = params.get("v");
        var v0 = params.get("v0");
        var t = params.get("t");
        return F * t / (v - v0);
    }

    private double getVelocity(Hashtable<String, Double> params, String unknown, String knownV)
    {
        Utils.checkParams(params, unknown, needed, notNegative);
        var F = params.get("F");
        var v = params.get(knownV);
        var m = params.get("m");
        var t = params.get("t");
        return F * t / m + v;
    }

    private double getTime(Hashtable<String, Double> params)
    {
        Utils.checkParams(params, "t", needed, notNegative);
        var F = params.get("F");
        var v = params.get("v");
        var v0 = params.get("v0");
        var m = params.get("m");
        return m * (v - v0) / F;
    }
}

//# endregion Task2-2
