package ar.com.mercadolibre.galaxy.service.impl;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.mercadolibre.galaxy.contants.PlanetConstants;
import ar.com.mercadolibre.galaxy.contants.WeatherConstant;
import ar.com.mercadolibre.galaxy.domain.PeriodsManager;
import ar.com.mercadolibre.galaxy.domain.Planet;
import ar.com.mercadolibre.galaxy.domain.Position;
import ar.com.mercadolibre.galaxy.dto.ForecastDTO;
import ar.com.mercadolibre.galaxy.exception.ServiceException;
import ar.com.mercadolibre.galaxy.exception.FactoryException;
import ar.com.mercadolibre.galaxy.exception.RepositoryException;
import ar.com.mercadolibre.galaxy.factory.PlanetFactory;
import ar.com.mercadolibre.galaxy.model.Forecast;
import ar.com.mercadolibre.galaxy.repository.ForecastRepository;
import ar.com.mercadolibre.galaxy.service.GalaxyService;
import ar.com.mercadolibre.galaxy.utils.CalculatorUtils;

@Component("galaxyService")
public class GalaxyServiceImpl implements GalaxyService {

	private static final Logger logger = LoggerFactory.getLogger(GalaxyServiceImpl.class);

	@Autowired
	ForecastRepository forecastRepository;

	@Override
	public void init() throws ServiceException {
		logger.info("Ingresando al metodo GalaxyServiceImpl.init");

		try {
			PeriodsManager periodsManager = new PeriodsManager();

			PlanetFactory planetFactory = new PlanetFactory();
			Planet ferengis = planetFactory.getPlanet(PlanetConstants.FERENGIS);
			Planet betasoides = planetFactory.getPlanet(PlanetConstants.BETASOIDES);
			Planet vulcanos = planetFactory.getPlanet(PlanetConstants.VULCANOS);

			Position positionOfSun = new Position(0.0, 0.0);

			for (int day = 1; day <= 3650; day++) {
				forecastRepository.save(calculateForecast(day, ferengis, betasoides, vulcanos, positionOfSun, periodsManager));

				logger.debug("El registro con id: " + day + " se cargo correctamente");
			}

			logger.info("¿Cuántos períodos de sequía habrá? " + periodsManager.getDroughtPeriod());
			logger.info("¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá? "
					+ periodsManager.getOptimumPeriod());
			logger.info("¿Cuántos períodos de lluvia habrá? " + periodsManager.getRainPeriod());
			logger.info("¿Qué día será el pico máximo de lluvia? " + periodsManager.getMaxDayRain());
			logger.info("¿Cuántos períodos de condiciones naturales habra? " + periodsManager.getNaturalPeriod());

			logger.info("Saliendo del metodo GalaxyServiceImpl.init");

		} catch (FactoryException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		} catch (RepositoryException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}

	@Override
	public ForecastDTO getForecastByDay(Integer day) throws ServiceException {
		logger.info("Ingresando al metodo GalaxyServiceImpl.getWeatherByDay");
		try {
			Forecast forecast = forecastRepository.findById(day);

			ForecastDTO forecastDTO = null;
			if (forecast != null) {
				forecastDTO = new ForecastDTO();
				forecastDTO.setDia(forecast.getDay());
				forecastDTO.setClima(forecast.getWeather());
			}

			logger.info("Saliendo del metodo GalaxyServiceImpl.getWeatherByDay");

			return forecastDTO;
		} catch (RepositoryException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}

	@Override
	public Integer getCountByWeather(String type) throws ServiceException {
		logger.info("Ingresando al metodo GalaxyServiceImpl.getCountByWeather");
		try {
			int count = forecastRepository.getCountByWeather(type);

			logger.info("Saliendo del metodo GalaxyServiceImpl.getCountByWeather");

			return count;
		} catch (RepositoryException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer getMaxRaindDay() throws ServiceException {
		logger.info("Ingresando al metodo GalaxyServiceImpl.getDayToMaxRainPeak");
		try {
			List<Forecast> forecastLiat = forecastRepository.getAllByWeather(WeatherConstant.RAIN);
			forecastLiat.sort(new Comparator<Forecast>() {
				public int compare(Forecast a, Forecast b) {
					Double perimA = a.getPerimeter();
					Double perimB = b.getPerimeter();
					int sComp = perimB.compareTo(perimA);
					if (sComp != 0) {
						return sComp;
					}

					Integer dayA = a.getDay();
					Integer dayB = b.getDay();

					return dayA.compareTo(dayB);
				}
			});

			logger.info("Ingresando al metodo GalaxyServiceImpl.getDayToMaxRainPeak");

			return forecastLiat.size() > 0 ? forecastLiat.get(0).getDay() : 0;
		} catch (RepositoryException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	private Forecast calculateForecast(int day, Planet ferengis, Planet betasoides, Planet vulcanos,
			Position positionOfSun, PeriodsManager periodsManager) throws RepositoryException {
		Forecast forecast = new Forecast();
		forecast.setDay(day);

		ferengis.incrementOneDay();
		betasoides.incrementOneDay();
		vulcanos.incrementOneDay();

		if (CalculatorUtils.isCollenear(ferengis.getPosition(), betasoides.getPosition(), vulcanos.getPosition())) {
			if (CalculatorUtils.isCollenear(ferengis.getPosition(), betasoides.getPosition(), positionOfSun)) {
				periodsManager.incrementDroughtPeriod();
				forecast.setWeather(WeatherConstant.DROUGHT);
			} else {
				periodsManager.incrementOptimunPeriod();
				forecast.setWeather(WeatherConstant.OPTIMUM);
			}
		} else {
			if (CalculatorUtils.pointInTriangle(ferengis.getPosition(), betasoides.getPosition(),
					vulcanos.getPosition(), positionOfSun)) {
				double perimAux = CalculatorUtils.getPerimeterTriangle(ferengis.getPosition(), betasoides.getPosition(),
						vulcanos.getPosition());
				if (perimAux > periodsManager.getPerimeterMax()) {
					periodsManager.setPerimeterMax(perimAux);
					periodsManager.setMaxDayRain(day);
				}

				periodsManager.incrementRainPeriod();
				forecast.setWeather(WeatherConstant.RAIN);
				forecast.setPerimeter(perimAux);

			} else {
				periodsManager.incrementNaturalPeriod();
				forecast.setWeather(WeatherConstant.NATURAL);
			}
		}

		return forecast;

	}
}
