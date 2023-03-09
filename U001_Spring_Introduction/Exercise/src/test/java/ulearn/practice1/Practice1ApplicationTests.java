package ulearn.practice1;

import org.apache.commons.lang3.StringUtils;
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

	ICoordinateCalculator equidistantMovementCalculator = configuration.equidistantMovementCalculator();
	ICoordinateCalculator linearMovementCalculator = configuration.linearMovementCalculator();
	ICoordinateCalculator circularMovementCalculator = configuration.circularMovementCalculator();
	ICoordinateCalculator generalMovementCalculator = configuration.generalMovementCalculator();

	double delta = 0.002;

	@Test
	public void beansShouldHaveAnnotations()
	{
		if (!configuration.getClass().isAnnotationPresent(Configuration.class))
			Assertions.fail();
		if (!configuration.getClass().isAnnotationPresent(ComponentScan.class))
			Assertions.fail();
		var names = new String[]{"equidistantMovement", "linearMovement", "circularMovement", "generalMovement"};
		for (String name : names)
			testFailsWhenAnnotationMissed(name, name == names[3]);
	}

	@Test
	public void shouldFailWhenIncorrectInput()
	{
		var velocities = new double[][]{{}, null};
		var ts = new int[]{-1, 0, 5};
		for (double[] velocity : velocities)
		{
			testFailsWhenIncorrectInput(() -> equidistantMovementCalculator.calculate(velocity, 0, 0, 1));
			testFailsWhenIncorrectInput(() -> linearMovementCalculator.calculate(velocity, 0, 0, 1));
			testFailsWhenIncorrectInput(() -> circularMovementCalculator.calculate(velocity, 0, 0, 1));
			testFailsWhenIncorrectInput(() -> generalMovementCalculator.calculate(velocity, 0, 0, 1));
		}
		var velocity = new double[]{1, 1, 1};
		for (int t : ts)
		{
			testFailsWhenIncorrectInput(() -> equidistantMovementCalculator.calculate(velocity, 0, 0, t));
			testFailsWhenIncorrectInput(() -> linearMovementCalculator.calculate(velocity, 0, 0, t));
			testFailsWhenIncorrectInput(() -> circularMovementCalculator.calculate(velocity, 0, 0, t));
			testFailsWhenIncorrectInput(() -> generalMovementCalculator.calculate(velocity, 0, 0, t));
		}

	}

	@Test
	public void beansShouldCalculateEquidistantMovement()
	{
		var a = 1;
		var v1 = DoubleStream.iterate(1, i -> i + a).limit(10).toArray();
		var v2 = DoubleStream.iterate(9, i -> i - a).limit(10).toArray();
		var v3 = DoubleStream.iterate(-1, i -> i - a).limit(10).toArray();

		var actual = equidistantMovementCalculator.calculate(v1, 0, 0, 8);
		Assertions.assertEquals(32, actual, delta);
		actual = equidistantMovementCalculator.calculate(v2, 30, 10, 5);
		Assertions.assertEquals(67.5, actual, delta);
		actual = equidistantMovementCalculator.calculate(v3, -5, 0, 10);
		Assertions.assertEquals(-55, actual, delta);
	}

	@Test
	public void beansShouldCalculateLinearMovement()
	{
		var v1 = DoubleStream.iterate(0, i -> 0).limit(5).toArray();
		var v2 = DoubleStream.iterate(5, i -> 5).limit(5).toArray();
		var v3 = DoubleStream.iterate(-5, i -> -5).limit(5).toArray();

		var actual = linearMovementCalculator.calculate(v1, 7, 0, 3);
		Assertions.assertEquals(7, actual, delta);
		actual = linearMovementCalculator.calculate(v2, -10, 5, 5);
		Assertions.assertEquals(15, actual, delta);
		actual = linearMovementCalculator.calculate(v3, 5, -5, 2);
		Assertions.assertEquals(-5, actual, delta);
	}

	@Test
	public void beansShouldCalculateCircularMovement()
	{
		var v1 = new double[5];
		var v2 = new double[5];
		var v3 = new double[5];
		Arrays.fill(v1, 5);
		Arrays.fill(v2, -5);
		Arrays.fill(v3, Math.PI);

		var actual = circularMovementCalculator.calculate(v1, 2, 5, 2);
		Assertions.assertEquals(0.567, actual, delta);
		actual = circularMovementCalculator.calculate(v2, 2, -5, 2);
		Assertions.assertEquals(0.567, actual, delta);
		actual = circularMovementCalculator.calculate(v1, -2, 5, 2);
		Assertions.assertEquals(-0.567, actual, delta);

		actual = circularMovementCalculator.calculate(v3, 2, Math.PI, 1);
		Assertions.assertEquals(0, actual, delta);
		actual = circularMovementCalculator.calculate(v3, 2, Math.PI, 5);
		Assertions.assertEquals(0, actual, delta);
	}

	@Test
	public void beansShouldCalculateGeneralMovement()
	{
		var a = 1;
		var v1 = DoubleStream.iterate(1, i -> i + a).limit(10).toArray();
		var v2 = DoubleStream.iterate(5, i -> 5).limit(5).toArray();
		var v3 = new double[]{-2, 3, 5, 4, 6, 8, 5, 3.5, 0.5};

		var actual = generalMovementCalculator.calculate(v1, 0, 0, 4);
		Assertions.assertEquals(10, actual, delta); // e = 8 : доп проверка для точного расчета
		actual = generalMovementCalculator.calculate(v2, -20, 5, 5);
		Assertions.assertEquals(10, actual, delta); // e = 5
		actual = generalMovementCalculator.calculate(v3, -5, 0, 9);
		Assertions.assertEquals(-5 + Arrays.stream(v3).sum(), actual, delta);
	}

	@Test
	public void beansShouldUseOptimalSolutionInSpecialCases()
	{
		//TODO
	}

	private void testFailsWhenAnnotationMissed(String name, boolean primary)
	{
		try
		{
			var method = configuration.getClass().getMethod(name + "Calculator");
			var annotation = method.getAnnotation(Bean.class);
			Assertions.assertEquals(StringUtils.capitalize(name), annotation.name()[0]);
			if (primary)
				Assertions.assertNotEquals(null, method.getAnnotation(Primary.class));
		}
		catch (Exception e)
		{
			Assertions.fail();
		}
	}

	private void testFailsWhenIncorrectInput(Supplier supplier)
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
