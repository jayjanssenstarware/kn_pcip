package kn.swift.pcip.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Component
@ConfigurationProperties(prefix = "wms", ignoreUnknownFields = false)
public class WmsProperties {
	
	private final Api api = new Api();
	
	@Getter
	@Setter
	public static class Api {
		private String url;
		private String user;
		private String password;
	}
}
