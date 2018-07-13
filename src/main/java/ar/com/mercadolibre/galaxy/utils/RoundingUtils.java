package ar.com.mercadolibre.galaxy.utils;

public final class RoundingUtils {

	private RoundingUtils() {

	}

	/**
	 * Este metodo se utiliza para redondear el resultado de las ecuaciones
	 * 
	 * @param num
	 * @return
	 */
	public static double round(double num) {
		return (double) Math.round(num * 10) / 10;
	}

}
