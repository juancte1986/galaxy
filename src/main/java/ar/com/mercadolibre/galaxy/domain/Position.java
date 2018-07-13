package ar.com.mercadolibre.galaxy.domain;

public class Position {

	private double x;
	private double y;

	public Position() {

	}

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
}