package ar.com.mercadolibre.galaxy.domain;

/**
 * 
 * La clase PeriodsManager se encarga de adminitrar los periodos durante el init
 * de la aplicacion
 *
 */
public class PeriodsManager {

	private int droughtPeriod = 0;
	private int rainPeriod = 0;
	private int optimumPeriod = 0;
	private int naturalPeriod = 0;
	private double perimeterMax = -10000.0;
	private int maxDayRain = 0;

	public int getDroughtPeriod() {
		return droughtPeriod;
	}

	public void setDroughtPeriod(int droughtPeriod) {
		this.droughtPeriod = droughtPeriod;
	}

	public int getRainPeriod() {
		return rainPeriod;
	}

	public void setRainPeriod(int rainPeriod) {
		this.rainPeriod = rainPeriod;
	}

	public int getOptimumPeriod() {
		return optimumPeriod;
	}

	public void setOptimumPeriod(int optimumPeriod) {
		this.optimumPeriod = optimumPeriod;
	}

	public int getNaturalPeriod() {
		return naturalPeriod;
	}

	public void setNaturalPeriod(int naturalPeriod) {
		this.naturalPeriod = naturalPeriod;
	}

	public double getPerimeterMax() {
		return perimeterMax;
	}

	public void setPerimeterMax(double perimeterMax) {
		this.perimeterMax = perimeterMax;
	}

	public void incrementOptimunPeriod() {
		this.optimumPeriod++;
	}

	public void incrementRainPeriod() {
		this.rainPeriod++;
	}

	public void incrementNaturalPeriod() {
		this.naturalPeriod++;
	}

	public void incrementDroughtPeriod() {
		this.droughtPeriod++;
	}

	public int getMaxDayRain() {
		return maxDayRain;
	}

	public void setMaxDayRain(int maxDayRain) {
		this.maxDayRain = maxDayRain;
	}

}
