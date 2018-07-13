package ar.com.mercadolibre.galaxy.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "forecast")
public class Forecast {

	@DynamoDBHashKey
	private Integer day;
	@DynamoDBAttribute
	private Double perimeter = 0.0;
	@DynamoDBAttribute
	private String weather;

	public Forecast(int day, String weather) {
		this.day = day;
		this.weather = weather;
	}

	public Forecast() {

	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public Double getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(Double perimeter) {
		this.perimeter = perimeter;
	}

}
