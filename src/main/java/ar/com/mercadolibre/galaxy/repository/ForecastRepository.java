package ar.com.mercadolibre.galaxy.repository;

import java.util.List;

import ar.com.mercadolibre.galaxy.exception.RepositoryException;
import ar.com.mercadolibre.galaxy.model.Forecast;

public interface ForecastRepository {

	/**
	 * @param id
	 * @return
	 * @throws RepositoryException
	 */
	public Forecast findById(Integer id) throws RepositoryException;

	/**
	 * @param forecast
	 * @throws RepositoryException
	 */
	public void save(Forecast forecast) throws RepositoryException;

	/**
	 * @param type
	 * @throws RepositoryException
	 */
	public Integer getCountByWeather(String type) throws RepositoryException;

	/**
	 * @param weather
	 * @return
	 * @throws RepositoryException
	 */
	public List<Forecast> getAllByWeather(String weather) throws RepositoryException;

}
