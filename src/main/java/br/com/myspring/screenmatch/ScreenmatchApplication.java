package br.com.myspring.screenmatch;

import br.com.myspring.screenmatch.model.SeriesData;
import br.com.myspring.screenmatch.service.ApiConsumption;
import br.com.myspring.screenmatch.service.DataConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception {
		var apiConsumption = new ApiConsumption ();
		var json = apiConsumption.getData ("https://www.omdbapi.com/?t=the+last+of+us&apikey=d1a73fd4");
		System.out.println (json);

		DataConverter dataConverter = new DataConverter ();
		SeriesData data = dataConverter.getData (json, SeriesData.class);
		System.out.println (data);
	}
}
