package com.market.superMarket;

import com.market.superMarket.services.ExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperMarketApplication implements CommandLineRunner{
	@Autowired
	private ExternalApi externalApi;

	public static void main(String[] args) {
		SpringApplication.run(SuperMarketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}


}
