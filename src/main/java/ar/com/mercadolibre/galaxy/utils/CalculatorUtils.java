package ar.com.mercadolibre.galaxy.utils;

import ar.com.mercadolibre.galaxy.domain.Position;

public final class CalculatorUtils {

	private CalculatorUtils() {

	}

	/**
	 * Retorna true si los tres puntos estan alineados en el eje de coordenadas
	 * 
	 * @param point1
	 * @param point2
	 * @param point3
	 * @return
	 */
	public static boolean isCollenear(Position point1, Position point2, Position point3) {
		double ba = point2.getX() - point1.getX();
		double ca = point3.getX() - point1.getX();

		if (ba == 0 || ca == 0) {
			return ba == ca ? true : false;
		}

		double m1 = (point2.getY() - point1.getY()) / ba;
		double m2 = (point3.getY() - point1.getY()) / ca;

		if (RoundingUtils.round(m1) == RoundingUtils.round(m2)) {
			return true;
		}

		return false;
	}

	/**
	 * Retorma el perimetro de un triangulo dado 3 puntos
	 * 
	 * @param point1
	 * @param point2
	 * @param point3
	 * @return
	 */
	public static double getPerimeterTriangle(Position point1, Position point2, Position point3) {
		double distanceP1P2 = Math
				.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
		double distanceP2P3 = Math
				.sqrt(Math.pow(point2.getX() - point3.getX(), 2) + Math.pow(point2.getY() - point3.getY(), 2));
		double distanceP1P3 = Math
				.sqrt(Math.pow(point1.getX() - point3.getX(), 2) + Math.pow(point1.getY() - point3.getY(), 2));

		return distanceP1P2 + distanceP2P3 + distanceP1P3;
	}

	/**
	 * Retorna true si el cuarto punto esta incluido en el triangulo que forman los
	 * point1, point2 y point3
	 * 
	 * @param point1
	 * @param point2
	 * @param point3
	 * @param point4
	 * @return
	 */
	public static boolean pointInTriangle(Position point1, Position point2, Position point3, Position point4) {
		boolean b1, b2, b3;

		b1 = sign(point4, point1, point2) < 0.0;
		b2 = sign(point4, point2, point3) < 0.0;
		b3 = sign(point4, point3, point1) < 0.0;

		return ((b1 == b2) && (b2 == b3));
	}

	private static double sign(Position p1, Position p2, Position p3) {
		return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
	}

}
