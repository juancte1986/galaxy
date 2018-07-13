package ar.com.mercadolibre.galaxy.repository.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBService {

	/**
	 * Este metodo instancia un cliente para DynamoDB
	 * 
	 * @return
	 */
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		//AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.fromName("us-east-2"))
//		.build();
		
    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
		.withEndpointConfiguration(new EndpointConfiguration("http://localhost:8000", "us-east-2"))
		.build();

		return new DynamoDBMapper(client);
	}

}
