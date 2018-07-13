package ar.com.mercadolibre.galaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercadolibre.galaxy.contants.MessagesConstants;
import ar.com.mercadolibre.galaxy.contants.ReportsConstants;
import ar.com.mercadolibre.galaxy.contants.WeatherConstant;
import ar.com.mercadolibre.galaxy.dto.ForecastDTO;
import ar.com.mercadolibre.galaxy.dto.ResponseDTO;
import ar.com.mercadolibre.galaxy.exception.ServiceException;
import ar.com.mercadolibre.galaxy.service.GalaxyService;

@RestController
public class GalaxyController {

	@Autowired
	GalaxyService galaxyService;

	@RequestMapping(value = "/clima", method = RequestMethod.GET, produces = "application/json")
	public ResponseDTO getForescastByDay(@RequestParam(value = "dia", required = true) Integer day) {
		ResponseDTO response = new ResponseDTO();
		try {
			ForecastDTO forecastDTO = galaxyService.getForecastByDay(day);
			if (forecastDTO != null) {
				response.setResponse(forecastDTO);
			} else {
				response.setHasError(true);
				response.setMessage(MessagesConstants.ERROR_2 + day);
			}
		} catch (ServiceException e) {
			response.setHasError(true);
			response.setMessage(MessagesConstants.ERROR_1 + e.getMessage());
		}

		return response;

	}

	@RequestMapping(value = "/reporte", method = RequestMethod.GET, produces = "application/json")
	public ResponseDTO getForescastByDay(@RequestParam(value = "tipo", required = true) String type) {
		ResponseDTO response = new ResponseDTO();
		try {
			if (ReportsConstants.REPORT_DROUGHT.toUpperCase().equals(type.toUpperCase())) {
				response.setResponse(galaxyService.getCountByWeather(WeatherConstant.DROUGHT));
			} else if (ReportsConstants.REPORT_NATURAL.toUpperCase().equals(type.toUpperCase())) {
				response.setResponse(galaxyService.getCountByWeather(WeatherConstant.NATURAL));
			} else if (ReportsConstants.REPORT_OPTIMUM.toUpperCase().equals(type.toUpperCase())) {
				response.setResponse(galaxyService.getCountByWeather(WeatherConstant.OPTIMUM));
			} else if (ReportsConstants.REPORT_RAIN.toUpperCase().equals(type.toUpperCase())) {
				response.setResponse(galaxyService.getCountByWeather(WeatherConstant.RAIN));
			} else if (ReportsConstants.REPORT_MAX_RAIN_DAY.toUpperCase().equals(type.toUpperCase())) {
				response.setResponse(galaxyService.getMaxRaindDay());
			} else {
				response.setHasError(true);
				response.setMessage(MessagesConstants.ERROR_3);
			}
		} catch (ServiceException e) {
			response.setHasError(true);
			response.setMessage(MessagesConstants.ERROR_1 + e.getMessage());
		}

		return response;

	}

}
