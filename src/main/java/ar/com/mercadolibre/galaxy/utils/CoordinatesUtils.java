package ar.com.mercadolibre.galaxy.utils;

public final class CoordinatesUtils {

	private CoordinatesUtils() {

	}

	/**
	 * Conversión de coordenadas polares a rectangulares
	 * 
	 * @param distance
	 * @param gradees
	 * @return
	 */
	public static double getX(double distance, Double gradees) {
		return distance * Math.cos(Math.toRadians(gradees));
	}

	/**
	 * Conversión de coordenadas polares a rectangulares
	 * 
	 * @param distance
	 * @param gradees
	 * @return
	 */
	public static double getY(double distance, Double gradees) {
		return distance * Math.sin(Math.toRadians(gradees));
	}
}
