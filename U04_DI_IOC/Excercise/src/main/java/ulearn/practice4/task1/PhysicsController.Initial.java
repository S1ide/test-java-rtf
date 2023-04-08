package ulearn.practice4.task1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/physics")
public class PhysicsController
{
    @PostMapping("/calculate/{formulaName}/{unknownParameter}")
    public double CalculateByFormula(@PathVariable String formulaName, @PathVariable String unknownParameter,
            @RequestBody double[] params)
    {
        return 0;
    }
}