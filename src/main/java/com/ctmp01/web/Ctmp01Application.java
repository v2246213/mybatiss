package com.ctmp01.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
public class Ctmp01Application extends SpringBootServletInitializer {

	@Autowired
	private Environment env;

	private final Log logger = LogFactory.getLog(this.getClass());

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Ctmp01Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Ctmp01Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			logger.debug("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				logger.debug(beanName);
			}

			logger.info(String.format("\n----------------------------------------------------------\n\t" +
							"Application '%s' is running! Access URLs:\n\t" +
							"Local: \t\thttp://localhost:%s\n\t" +
							"The following profiles are active: %s\n" +
							"----------------------------------------------------------",
					env.getProperty("spring.application.name"),
					env.getProperty("server.port"),
					env.getProperty("spring.profiles.active")));

		};
	}
}
