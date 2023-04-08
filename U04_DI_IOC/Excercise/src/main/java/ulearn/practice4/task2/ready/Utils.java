package ulearn.practice4.task2.ready;

import java.util.Hashtable;

//# region Task2-5
public class Utils
{
    public static void checkParams(Hashtable<String, Double> params, String unknown, String[] needed, String[] notNegatives)
    {
        for (var n: needed)
        {
            if(n.equals(unknown))
                continue;
            if (!params.containsKey(n))
                throw new IllegalArgumentException(String.format("You need to send all parameters, but %s not provided", n));
        }

        for (var n: notNegatives)
        {
            if (!params.containsKey(n))
                continue;
            if (params.get(n) <= 0)
                throw new IllegalArgumentException(String.format("This parameter (%s) cant be negative", n));
        }
    }
}
//# endregion Task2-5