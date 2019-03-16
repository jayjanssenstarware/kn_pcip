package kn.swift.pcip.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "pcip", ignoreUnknownFields = false)
public class PcipProperties {

	private final AuthHeader authHeader = new AuthHeader();
	private final Parcel parcel = new Parcel();
	private final Xpath xpath = new Xpath();
	private final Jaxb jaxb	= new Jaxb();
	private String uri;
	private String basePath;
	private String authBasePath;

	@Getter
	@Setter
	public static class Jaxb {
		private String contextPath;
	}

	@Getter
	@Setter
	public static class Xpath {
		private String parcelId;
		private String clientId;
		private String warehouseId;
		private String shipmentRefno;
		private String knSscc;
		private String knLoadId;
		private String knCarrierId;
	}

	@Getter
	@Setter
	public static class Parcel {
		private String identifier;
		private String externalRefs;
		private String refno;
	}

	@Getter
	@Setter
	public static class AuthHeader {
		private String culture;
		private String user;
		private String password;
	}
}
