package ar.com.mercadolibre.galaxy.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import ar.com.mercadolibre.galaxy.contants.ReportsConstants;
import ar.com.mercadolibre.galaxy.contants.WeatherConstant;
import ar.com.mercadolibre.galaxy.dto.ForecastDTO;
import ar.com.mercadolibre.galaxy.model.Forecast;
import ar.com.mercadolibre.galaxy.repository.impl.ForecastRepositoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class GalaxyServiceImplTest {

	private static final Integer DAY_15 = 15;
	private static final Integer DAY_20 = 20;
	private static final Integer DAY_10 = 10;

	@Mock
	private ForecastRepositoryImpl forecastRepository;
	@InjectMocks
	private GalaxyServiceImpl galaxyService;

	@Test
	public void testGetForecastByDayOk() throws Exception {
		Mockito.when(forecastRepository.findById(DAY_15)).thenReturn(new Forecast(DAY_15, WeatherConstant.RAIN));

		ForecastDTO result = galaxyService.getForecastByDay(DAY_15);

		assertThat(result.getDia()).isEqualTo(DAY_15);
		assertThat(result.getClima()).isEqualTo(WeatherConstant.RAIN);

	}

	@Test
	public void testGetCountByWeatherOk() throws Exception {
		Mockito.when(forecastRepository.getCountByWeather(ReportsConstants.REPORT_RAIN)).thenReturn(DAY_15);

		Integer result = galaxyService.getCountByWeather(ReportsConstants.REPORT_RAIN);

		assertThat(result).isEqualTo(DAY_15);
	}

	@Test
	public void testGetForecastToMaxRainDayOk() throws Exception {
		List<Forecast> forecastList = new ArrayList<Forecast>();
		Forecast item1 = new Forecast();
		item1.setDay(DAY_15);
		item1.setPerimeter(80.0);
		item1.setWeather(WeatherConstant.RAIN);

		Forecast item2 = new Forecast();
		item2.setDay(DAY_20);
		item2.setPerimeter(40.0);
		item2.setWeather(WeatherConstant.RAIN);

		Forecast item3 = new Forecast();
		item2.setDay(DAY_10);
		item2.setPerimeter(2.0);
		item2.setWeather(WeatherConstant.RAIN);

		forecastList.add(item1);
		forecastList.add(item2);
		forecastList.add(item3);

		Mockito.when(forecastRepository.getAllByWeather(WeatherConstant.RAIN)).thenReturn(forecastList);

		Integer day = galaxyService.getMaxRaindDay();

		assertThat(day).isEqualTo(DAY_15);

	}

}
