package ar.com.mercadolibre.galaxy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ar.com.mercadolibre.galaxy.contants.ReportsConstants;
import ar.com.mercadolibre.galaxy.contants.WeatherConstant;
import ar.com.mercadolibre.galaxy.dto.ForecastDTO;
import ar.com.mercadolibre.galaxy.service.impl.GalaxyServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(GalaxyController.class)
public class GalaxyControllerTest {

	private final Integer DAY_15 = 15;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GalaxyServiceImpl galaxyService;

	@Test
	public void testGetForecastByDayOk() throws Exception {
		ForecastDTO weather = new ForecastDTO();
		weather.setDia(DAY_15);
		weather.setClima(WeatherConstant.RAIN);

		Mockito.when(galaxyService.getForecastByDay(DAY_15)).thenReturn(weather);

		this.mockMvc.perform(get("/clima?dia=" + DAY_15)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(WeatherConstant.RAIN)));

	}

	@Test
	public void testGetReportByTypeOk() throws Exception {
		Mockito.when(galaxyService.getMaxRaindDay()).thenReturn(DAY_15);

		this.mockMvc.perform(get("/reporte?tipo=" + ReportsConstants.REPORT_MAX_RAIN_DAY)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString(DAY_15.toString())));

	}

}
