package ar.com.mercadolibre.galaxy.domain;

import ar.com.mercadolibre.galaxy.utils.CoordinatesUtils;
import ar.com.mercadolibre.galaxy.utils.RoundingUtils;

public class Planet {

	private String name;
	private double distanceToSun;
	private double speed;
	private double positionInDagrees;
	private Position position;
	private boolean clock;

	public Planet(String name, double distanceToSun, double speed, double positionInDagrees, boolean clock) {
		this.name = name;
		this.distanceToSun = distanceToSun;
		this.speed = speed;
		this.positionInDagrees = positionInDagrees;
		this.clock = clock;
		this.position = new Position();
	}

	public Position getPosition() {
		return position;
	}

	public double getDistanceToSun() {
		return distanceToSun;
	}

	public void setDistanceToSun(double distanceToSun) {
		this.distanceToSun = distanceToSun;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getPositionInDagrees() {
		return positionInDagrees;
	}

	public void setPositionInDagrees(double positionInDagrees) {
		this.positionInDagrees = positionInDagrees;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isClock() {
		return Clock;
	}

	public void setClock(boolean clock) {
		this.clock = clock;
	}

	/**
	 * Este metodo incrementa la posicion en grados y las coordenadas del planeta
	 * segun el sentido horario
	 */
	public void incrementOneDay() {
		double dagrees = 0.0;
		if (clock) {
			dagrees = this.positionInDagrees - this.speed;
			if (dagrees < 0) {
				dagrees += 360;
			}

		} else {
			dagrees = this.positionInDagrees + this.speed;
			if (dagrees > 360) {
				dagrees -= 360;
			}

		}

		this.positionInDagrees = dagrees;
		this.updatePosition();
	}

	private void updatePosition() {
		this.position.setX(RoundingUtils.round(CoordinatesUtils.getX(this.distanceToSun, this.positionInDagrees)));
		this.position.setY(RoundingUtils.round(CoordinatesUtils.getY(this.distanceToSun, this.positionInDagrees)));
	}
}
