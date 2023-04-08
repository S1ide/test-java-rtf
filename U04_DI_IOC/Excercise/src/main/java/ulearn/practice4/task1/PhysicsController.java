package ulearn.practice4.task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ulearn.practice4.task2.IFormula;

import java.util.*;

//# region Task1
@Controller
@ResponseBody
@RequestMapping("/physics")
public class PhysicsController
{
    private Set<String> allowedFormulas;

    //private IFormula [] formulas;
    Map<String, IFormula> formulas;

    @Autowired
    public PhysicsController(ApplicationContext context)
    {
        formulas = context.getBeansOfType(IFormula.class);
        allowedFormulas = formulas.keySet();
    }

    @PostMapping("/calculate/{formulaName}/{unknownParameter}")
    public double calculateByFormula(@PathVariable String formulaName, @PathVariable String unknownParameter,
            @RequestBody Hashtable<String, Double> params)
    {
        if(!allowedFormulas.contains(formulaName))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "You cant choose not allowed Formula");
        try
        {
            return formulas.get(formulaName).Calculate(params, unknownParameter);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
//# endregion Task1