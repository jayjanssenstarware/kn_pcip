package kn.swift.pcip.configuration.properties;

import java.io.File;
import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "service", ignoreUnknownFields = true)
@ConditionalOnProperty("service.useInternalStores") 
public class ServiceConfig {

	private String trustStore;
	private String keyStore;
	private String trustStorePassword;
	private String keyStorePassword;

	@PostConstruct
	private void configStores() {
		try {
			File file = ResourceUtils.getFile("classpath:" + trustStore);
			System.setProperty("javax.net.ssl.trustStore", file.getAbsolutePath());
			file = ResourceUtils.getFile("classpath:" + keyStore);
			System.setProperty("javax.net.ssl.keyStore", file.getAbsolutePath());
			System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
			System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
