package ulearn.practice1;

import org.apache.commons.lang3.NotImplementedException;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//#region Task
@Configuration // Студент может не знать из лекций о этих аннотациях
@ComponentScan
public class CoordinateCalculatorConfiguration
{

    public ICoordinateCalculator EquidistantMovementCalculator()
    {
        throw new NotImplementedException();
    }

    public ICoordinateCalculator LinearMovementCalculator()
    {
        throw new NotImplementedException();
    }

    public ICoordinateCalculator CircularMovementCalculator()
    {
        throw new NotImplementedException();
    }

    public ICoordinateCalculator GeneralMovementCalculator()
    {
        throw new NotImplementedException();
    }

    public class EquidistantMovementCalculator
    {

    }

    public class LinearMovementCalculator
    {

    }

    public class CircularMovementCalculator
    {

    }

    public class GeneralMovementCalculator
    {

    }
}

//#endregion Task