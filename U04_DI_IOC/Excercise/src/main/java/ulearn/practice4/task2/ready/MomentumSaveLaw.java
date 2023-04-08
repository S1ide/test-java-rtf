package ulearn.practice4.task2.ready;

import org.springframework.stereotype.Component;
import ulearn.practice4.task2.IFormula;

import java.util.Hashtable;

//# region Task2-3
@Component()
public class MomentumSaveLaw implements IFormula
{
    private String[] notNegative = new String[] {"m1", "m2"};
    private String[] needed = new String[] {"m1", "m2","v1","v01","v2","v02"};

    @Override
    public double Calculate(Hashtable<String, Double> params, String unknownParameter)
    {
        return switch (unknownParameter)
                {
                    case "m1" -> getMass(params, "m1", "m2", true);
                    case "m2" -> getMass(params, "m2", "m1", false);
                    case "v1" -> getVelocity(params, "v1", "v01", "v2", "v02", true);
                    case "v2" -> getVelocity(params, "v2", "v02", "v1", "v01", true);
                    case "v01" -> getVelocity(params, "v01", "v1", "v02", "v2", false);
                    case "v02" -> getVelocity(params, "v02","v2", "v01", "v1", false);
                    default -> throw new IllegalArgumentException(
                            String.format("Possible params are m1, m2,v1,v01,v2,v02; but was %s", unknownParameter));
                };
    }

    private double getMass(Hashtable<String, Double> params, String unknown, String knownM, boolean t1Unknown)
    {
        Utils.checkParams(params, unknown, needed, notNegative);
        var v01 = params.get("v01");
        var v02 = params.get("v02");
        var v1 = params.get("v1");
        var v2 = params.get("v2");
        var m2 = params.get(knownM);
        if(t1Unknown)
            return m2 * (v02 - v2) / (v01 - v1);
        else
            return m2 * (v01 - v1) / (v02 - v2);
    }

    private double getVelocity(Hashtable<String, Double> params, String unknown, String _v01, String _v2, String _v02,
                               boolean t1Unknown)
    {
        Utils.checkParams(params, unknown, needed, notNegative);
        var v01 = params.get(_v01);
        var v02 = params.get(_v02);
        var v2 = params.get(_v2);
        var m1 = params.get("m1");
        var m2 = params.get("m2");
        if(t1Unknown)
            return v01 + m2 *(v02 - v2) / m1;
        else
            return v01 + m1 *(v02 - v2) / m2;
    }
}
//# endregion Task2-3