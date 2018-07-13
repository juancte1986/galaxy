package ar.com.mercadolibre.galaxy.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import ar.com.mercadolibre.galaxy.exception.RepositoryException;
import ar.com.mercadolibre.galaxy.model.Forecast;
import ar.com.mercadolibre.galaxy.repository.ForecastRepository;

@Repository
public class ForecastRepositoryImpl implements ForecastRepository {

	@Autowired
	DynamoDBMapper dynamoDBMapper;

	@Override
	public Forecast findById(Integer id) throws RepositoryException {
		try {
			return dynamoDBMapper.load(Forecast.class, id);
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

	}

	@Override
	public void save(Forecast forecast) throws RepositoryException {
		try {
			dynamoDBMapper.save(forecast);
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

	}

	@Override
	public Integer getCountByWeather(String type) throws RepositoryException {
		try {
			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":val", new AttributeValue().withS(type));
			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("weather = :val")
					.withExpressionAttributeValues(eav);

			return dynamoDBMapper.count(Forecast.class, scanExpression);

		} catch (Exception e) {
			throw new RepositoryException(e);
		}

	}

	@Override
	public List<Forecast> getAllByWeather(String weather) throws RepositoryException {
		try {
			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":val", new AttributeValue().withS(weather));
			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("weather = :val")
					.withExpressionAttributeValues(eav);

			return new ArrayList<Forecast>(dynamoDBMapper.scan(Forecast.class, scanExpression));

		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

}
