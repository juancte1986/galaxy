package ar.com.mercadolibre.galaxy.service;

import ar.com.mercadolibre.galaxy.dto.ForecastDTO;
import ar.com.mercadolibre.galaxy.exception.ServiceException;

public interface GalaxyService {

	/**
	 * Este metodo se utiliza para inicializar la base
	 * 
	 * @throws ServiceException
	 */
	public void init() throws ServiceException;

	/**
	 * @param day
	 * @return
	 * @throws ServiceException
	 */
	public ForecastDTO getForecastByDay(Integer day) throws ServiceException;

	/**
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	public Integer getCountByWeather(String type) throws ServiceException;

	/**
	 * Este metodo retorna el dia con mayor intensidad de lluvia, si retorna 0
	 * significa que no existe dias con lluvia
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Integer getMaxRaindDay() throws ServiceException;

}
