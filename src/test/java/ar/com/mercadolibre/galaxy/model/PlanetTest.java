package ar.com.mercadolibre.galaxy.model;

import org.junit.Assert;
import org.junit.Test;

import ar.com.mercadolibre.galaxy.contants.PlanetConstants;
import ar.com.mercadolibre.galaxy.domain.Planet;
import ar.com.mercadolibre.galaxy.factory.PlanetFactory;

public class PlanetTest {

	private final static double POSITION_FERENGIS = 359.0;
	private final static double POSITION_BETASOIDES = 357.0;
	private final static double POSITION_VULCANOS = 5.0;

	@Test
	public void getIncrementOneDayOk() throws Exception {
		PlanetFactory factory = new PlanetFactory();
		Planet ferengis = factory.getPlanet(PlanetConstants.FERENGIS);
		Planet vulcanos = factory.getPlanet(PlanetConstants.VULCANOS);
		Planet betasoides = factory.getPlanet(PlanetConstants.BETASOIDES);

		ferengis.incrementOneDay();
		vulcanos.incrementOneDay();
		betasoides.incrementOneDay();

		Assert.assertTrue(ferengis.getPositionInDagrees() == POSITION_FERENGIS);
		Assert.assertTrue(betasoides.getPositionInDagrees() == POSITION_BETASOIDES);
		Assert.assertTrue(vulcanos.getPositionInDagrees() == POSITION_VULCANOS);

	}

}
