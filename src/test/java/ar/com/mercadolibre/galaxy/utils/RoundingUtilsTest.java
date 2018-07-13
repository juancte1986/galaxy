package ar.com.mercadolibre.galaxy.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RoundingUtilsTest {
	
	@Test
	public void roundOk () {
		double result = RoundingUtils.round(4.99);
		double resultB = RoundingUtils.round(4.92);
		double resultC = RoundingUtils.round(4.95);
		double resultD = RoundingUtils.round(4.93);
		
		assertThat(result).isEqualTo(5.0);		
		assertThat(resultB).isEqualTo(4.9);
		assertThat(resultC).isEqualTo(5.0);
		assertThat(resultD).isEqualTo(4.9);
	}
}
