package ulearn.practice1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


//#region Task
@Configuration
@ComponentScan
public class CoordinateCalculatorConfiguration {

    @Bean(name = "EquidistantMovement")
    public ICoordinateCalculator EquidistantMovementCalculator()
    {
        return new EquidistantMovementCalculator();
    }

    @Bean(name = "LinearMovement")
    public ICoordinateCalculator LinearMovementCalculator()
    {
        return new LinearMovementCalculator();
    }

    @Bean(name = "CircularMovement")
    public ICoordinateCalculator CircularMovementCalculator()
    {
        return new CircularMovementCalculator();
    }

    @Bean(name = "GeneralMovement")
    @Primary()
    public ICoordinateCalculator GeneralMovementCalculator()
    {
        return new GeneralMovementCalculator();
    }

    public abstract class CoordinateCalculator implements  ICoordinateCalculator{
        protected void ThrowWhenWrongInput(double[] velocity, int t)
        {
            if(velocity == null  || t < 1 || velocity.length < 1 || t > velocity.length)
                throw new IllegalArgumentException();
        }
    }

    public class EquidistantMovementCalculator extends CoordinateCalculator{

        @Override
        public double Calculate(double[] velocity, double x0, double v0, int t)
        {
            ThrowWhenWrongInput(velocity, t);
            var a = velocity[0] - v0;
            return x0 + v0 * t + a * t * t / 2;
        }
    }

    public class LinearMovementCalculator extends CoordinateCalculator{

        @Override
        public double Calculate(double[] velocity, double x0, double v0, int t)
        {
            ThrowWhenWrongInput(velocity, t);
            return x0 + v0 * t;
        }
    }

    public class CircularMovementCalculator extends CoordinateCalculator{

        @Override
        public double Calculate(double[] velocity, double x0, double v0, int t)
        {
            ThrowWhenWrongInput(velocity, t);
            var r_vector = x0;
            var w =  v0 / r_vector;
            return r_vector * Math.cos(w * t);
        }
    }

    //TODO Проверить работоспособность - убрать / оставить / дополнить условия
    public class GeneralMovementCalculator extends CoordinateCalculator{

        @Override
        public double Calculate(double[] velocity, double x0, double v0, int t)
        {
            ThrowWhenWrongInput(velocity, t);
            var x = x0 + v0;
            for (var i = 0; i < t; i++)
                x += velocity[i];
            return x;
        }
    }
}
//#endregion Task