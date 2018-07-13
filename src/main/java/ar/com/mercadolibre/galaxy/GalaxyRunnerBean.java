package ar.com.mercadolibre.galaxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ar.com.mercadolibre.galaxy.service.GalaxyService;

@Component
public class GalaxyRunnerBean implements ApplicationRunner {

	@Autowired
	private GalaxyService galaxyService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		galaxyService.init();
	}

}
