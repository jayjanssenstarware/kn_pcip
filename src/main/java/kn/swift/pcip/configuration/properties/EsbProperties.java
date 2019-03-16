package kn.swift.pcip.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "esb", ignoreUnknownFields = false)
public class EsbProperties {
	
	private String url;
	private String pcipBasePath;
	private String user;
	private String password;
	
}
