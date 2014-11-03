package com.ailife.uip.doc.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

/**
 * Created by chenmm on 10/13/2014.
 */
@Configuration
@EnableConfigurationProperties(DocProperties.class)
public class DocAutoConfiguration {

	public static final String CONFIGURATION_PREFIX = "doc.inter";

	@Configuration
	@ConditionalOnMissingBean(DocInitializer.class)
	protected static class UIPDataSourceInitializerConfiguration {

		@Bean
		public DocInitializer uipDataSourceInitializer() {
			return new DocInitializer();
		}

	}

	@Configuration
	@ConditionalOnMissingBean(IdGenerator.class)
	protected static class IdGeneratorConfiguration {

		@Bean
		public IdGenerator idGeneratorInitializer() {
			return new AlternativeJdkIdGenerator();
		}

	}

}
