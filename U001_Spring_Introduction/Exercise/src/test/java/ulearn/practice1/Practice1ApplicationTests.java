package ulearn.practice1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

@SpringBootTest
class Practice1ApplicationTests
{

	CoordinateCalculatorConfiguration configuration = new CoordinateCalculatorConfiguration();

	ICoordinateCalculator equidistantMovementCalculator = configuration.EquidistantMovementCalculator();
	ICoordinateCalculator linearMovementCalculator = configuration.LinearMovementCalculator();
	ICoordinateCalculator circularMovementCalculator = configuration.CircularMovementCalculator();
	ICoordinateCalculator generalMovementCalculator = configuration.GeneralMovementCalculator();

	double delta = 0.002;

	@Test
	public void BeansShouldHaveAnnotations()
	{
		if (!configuration.getClass().isAnnotationPresent(Configuration.class))
			Assertions.fail();
		if (!configuration.getClass().isAnnotationPresent(ComponentScan.class))
			Assertions.fail();
		var names = new String[]{"EquidistantMovement", "LinearMovement", "CircularMovement", "GeneralMovement"};
		for (String name : names)
			TestFailsWhenAnnotationMissed(name, name == names[3]);
	}

	@Test
	public void ShouldFailWhenIncorrectInput()
	{
		var velocities = new double[][]{{}, null};
		var ts = new int[]{-1, 0, 5};
		for (double[] velocity : velocities)
		{
			TestFailsWhenIncorrectInput(() -> equidistantMovementCalculator.Calculate(velocity, 0, 0, 1));
			TestFailsWhenIncorrectInput(() -> linearMovementCalculator.Calculate(velocity, 0, 0, 1));
			TestFailsWhenIncorrectInput(() -> circularMovementCalculator.Calculate(velocity, 0, 0, 1));
			TestFailsWhenIncorrectInput(() -> generalMovementCalculator.Calculate(velocity, 0, 0, 1));
		}
		var velocity = new double[]{1, 1, 1};
		for (int t : ts)
		{
			TestFailsWhenIncorrectInput(() -> equidistantMovementCalculator.Calculate(velocity, 0, 0, t));
			TestFailsWhenIncorrectInput(() -> linearMovementCalculator.Calculate(velocity, 0, 0, t));
			TestFailsWhenIncorrectInput(() -> circularMovementCalculator.Calculate(velocity, 0, 0, t));
			TestFailsWhenIncorrectInput(() -> generalMovementCalculator.Calculate(velocity, 0, 0, t));
		}

	}

	@Test
	public void BeansShouldCalculateEquidistantMovement()
	{
		var a = 1;
		var v1 = DoubleStream.iterate(1, i -> i + a).limit(10).toArray();
		var v2 = DoubleStream.iterate(9, i -> i - a).limit(10).toArray();
		var v3 = DoubleStream.iterate(-1, i -> i - a).limit(10).toArray();

		var actual = equidistantMovementCalculator.Calculate(v1, 0, 0, 8);
		Assertions.assertEquals(32, actual, delta);
		actual = equidistantMovementCalculator.Calculate(v2, 30, 10, 5);
		Assertions.assertEquals(67.5, actual, delta);
		actual = equidistantMovementCalculator.Calculate(v3, -5, 0, 10);
		Assertions.assertEquals(-55, actual, delta);
	}

	@Test
	public void BeansShouldCalculateLinearMovement()
	{
		var v1 = DoubleStream.iterate(0, i -> 0).limit(5).toArray();
		var v2 = DoubleStream.iterate(5, i -> 5).limit(5).toArray();
		var v3 = DoubleStream.iterate(-5, i -> -5).limit(5).toArray();

		var actual = linearMovementCalculator.Calculate(v1, 7, 0, 3);
		Assertions.assertEquals(7, actual, delta);
		actual = linearMovementCalculator.Calculate(v2, -10, 5, 5);
		Assertions.assertEquals(15, actual, delta);
		actual = linearMovementCalculator.Calculate(v3, 5, -5, 2);
		Assertions.assertEquals(-5, actual, delta);
	}

	@Test
	public void BeansShouldCalculateCircularMovement()
	{
		var v1 = new double[5];
		var v2 = new double[5];
		var v3 = new double[5];
		Arrays.fill(v1, 5);
		Arrays.fill(v2, -5);
		Arrays.fill(v3, Math.PI);

		var actual = circularMovementCalculator.Calculate(v1, 2, 5, 2);
		Assertions.assertEquals(0.567, actual, delta);
		actual = circularMovementCalculator.Calculate(v2, 2, -5, 2);
		Assertions.assertEquals(0.567, actual, delta);
		actual = circularMovementCalculator.Calculate(v1, -2, 5, 2);
		Assertions.assertEquals(-0.567, actual, delta);

		actual = circularMovementCalculator.Calculate(v3, 2, Math.PI, 1);
		Assertions.assertEquals(0, actual, delta);
		actual = circularMovementCalculator.Calculate(v3, 2, Math.PI, 5);
		Assertions.assertEquals(0, actual, delta);
	}

	@Test
	public void BeansShouldCalculateGeneralMovement()
	{
		var a = 1;
		var v1 = DoubleStream.iterate(1, i -> i + a).limit(10).toArray();
		var v2 = DoubleStream.iterate(5, i -> 5).limit(5).toArray();
		var v3 = new double[]{-2, 3, 5, 4, 6, 8, 5, 3.5, 0.5};

		var actual = generalMovementCalculator.Calculate(v1, 0, 0, 4);
		Assertions.assertEquals(10, actual, delta); // e = 8 : доп проверка для точного расчета
		actual = generalMovementCalculator.Calculate(v2, -20, 5, 5);
		Assertions.assertEquals(10, actual, delta); // e = 5
		actual = generalMovementCalculator.Calculate(v3, -5, 0, 9);
		Assertions.assertEquals(-5 + Arrays.stream(v3).sum(), actual, delta);
	}

	@Test
	public void BeansShouldUseOptimalSolutionInSpecialCases()
	{
		//TODO
	}

	private void TestFailsWhenAnnotationMissed(String name, boolean primary)
	{
		try
		{
			var method = configuration.getClass().getMethod(name + "Calculator");
			var annotation = method.getAnnotation(Bean.class);
			Assertions.assertEquals(name, annotation.name()[0]);
			if (primary)
				Assertions.assertNotEquals(null, method.getAnnotation(Primary.class));
		}
		catch (Exception e)
		{
			Assertions.fail();
		}
	}

	private void TestFailsWhenIncorrectInput(Supplier supplier)
	{
		try
		{
			supplier.get();
		}
		catch (IllegalArgumentException e)
		{
			return;
		}
		Assertions.fail();
	}
}
