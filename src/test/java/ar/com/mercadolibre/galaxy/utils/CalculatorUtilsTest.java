package ar.com.mercadolibre.galaxy.utils;

import org.junit.Assert;
import org.junit.Test;

import ar.com.mercadolibre.galaxy.domain.Position;

public class CalculatorUtilsTest {
	
	@Test
	public void checkAlignedPointsTrue () {
		Position point1 = new Position(0.0, 0.1);
		Position point2 = new Position(0.0, 0.2);
		Position point3 = new Position(0.0, 0.3);
		
		boolean result = CalculatorUtils.isCollenear(point1, point2, point3);
		
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void pointInTriangleTrue () {
		Position point1 = new Position(0.0, 3.0);
		Position point2 = new Position(4.0, -2.0);
		Position point3 = new Position(-20.0, -2.0);
		
		Position point4 = new Position(0.0, 0.0);
		
		boolean result = CalculatorUtils.pointInTriangle(point1, point2, point3, point4);
		
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void pointInTriangleFalse () {
		Position point1 = new Position(0.0, 3.0);
		Position point2 = new Position(4.0, -1.0);
		Position point3 = new Position(20.0, -1.);
		
		Position point4 = new Position(50.0, 50.0);
		
		boolean result = CalculatorUtils.pointInTriangle(point1, point2, point3, point4);
		
		Assert.assertTrue(!result);
		
	}
}
