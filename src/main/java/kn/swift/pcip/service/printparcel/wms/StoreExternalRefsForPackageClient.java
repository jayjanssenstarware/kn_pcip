package kn.swift.pcip.service.printparcel.wms;

import kn.swift.ms.pcip.dto.ShippingParameter;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swift.wms.w001.client.api.DefaultApi;
import swift.wms.w001.client.model.StoreExternalRefsForPackage;

@Component
@Getter
@Setter
public class StoreExternalRefsForPackageClient {

	private static final Logger log = LoggerFactory.getLogger(StoreExternalRefsForPackageClient.class);

	private static final String SYSTEM_NAME = "PCIP";

	@Autowired
	private DefaultApi wmsApi;

	public void storeExternalRefsForPackage(String pkgId, ShippingParameter shippingParameter) {
		log.info("Calling endpoint on WMS to Update Shipping Parameter {} for package Id {} ",
				shippingParameter.getName(), pkgId);
		StoreExternalRefsForPackage requestTostoreExternalRefsForPackage = createRequestTostoreExternalRefsForPackage2(
				pkgId, shippingParameter);
		wmsApi.storeExternalRefsForPackage(requestTostoreExternalRefsForPackage);
		log.info("Call to WMS endpoint to update Shipping Parameter {} for package Id {} is successful ",
				shippingParameter.getName(), pkgId);
	}

	private StoreExternalRefsForPackage createRequestTostoreExternalRefsForPackage2(String pkgId,
			ShippingParameter shippingParameter) {
		StoreExternalRefsForPackage request = new StoreExternalRefsForPackage();
		request.setSystem(SYSTEM_NAME);
		request.setPkgId(pkgId);
		request.setName(shippingParameter.getName());
		request.setValue(shippingParameter.getValue().getValue());
		return request;
	}

}
