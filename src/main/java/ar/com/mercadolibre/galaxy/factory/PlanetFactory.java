package ar.com.mercadolibre.galaxy.factory;

import ar.com.mercadolibre.galaxy.contants.PlanetConstants;
import ar.com.mercadolibre.galaxy.domain.Planet;
import ar.com.mercadolibre.galaxy.exception.FactoryException;

public class PlanetFactory {

	/**
	 * Factory de planetas
	 * 
	 * @param type
	 * @return
	 * @throws FactoryException
	 */
	public Planet getPlanet(String type) throws FactoryException {
		if (PlanetConstants.FERENGIS.equals(type)) {
			return new Planet(PlanetConstants.FERENGIS, PlanetConstants.DISTANCE_TO_SUN_500KM,
					PlanetConstants.SPEED_FERENGIS, 0.0, true);
		} else if (PlanetConstants.BETASOIDES.equals(type)) {
			return new Planet(PlanetConstants.BETASOIDES, PlanetConstants.DISTANCE_TO_SUN_2000KM,
					PlanetConstants.SPEED_BETASOIDES, 0.0, true);
		} else if (PlanetConstants.VULCANOS.equals(type)) {
			return new Planet(PlanetConstants.VULCANOS, PlanetConstants.DISTANCE_TO_SUN_1000KM,
					PlanetConstants.SPEED_VULCANOS, 0.0, false);
		} else {
			throw new FactoryException("El planeta " + type
					+ " es invalido. Los unicos planetas validos son Farengi, Betasoide y Vulcano.");
		}
	}
}
