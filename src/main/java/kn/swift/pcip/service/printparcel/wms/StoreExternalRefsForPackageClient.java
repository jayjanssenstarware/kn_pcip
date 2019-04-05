package kn.swift.pcip.service.printparcel.wms;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redprairie.moca.MocaArgument;
import com.redprairie.moca.MocaException;

import kn.swift.ms.pcip.dto.ShippingParameter;
import kn.swift.pcip.util.PCIPConnectionWrapper;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class StoreExternalRefsForPackageClient {

	private static final Logger log = LoggerFactory.getLogger(StoreExternalRefsForPackageClient.class);

	private static final String SYSTEM_NAME = "PCIP";

	private static final String COMMAND = "store usr external refs for package";
	
	@Autowired
	@Setter
	private PCIPConnectionWrapper util;

	public void storeExternalRefsForPackage(String pkgId, ShippingParameter shippingParameter) {
		log.info("Calling MOCA command {} on WMS to Update Shipping Parameter {} for package Id {} ", COMMAND,
				shippingParameter.getName(), pkgId);
		try {
			List<MocaArgument> params = createParamsForMocaCall(pkgId, shippingParameter);
			util.executeCommand(COMMAND, params.toArray(new MocaArgument[params.size()]));
			log.info("Call to WMS endpoint to update Shipping Parameter {} for package Id {} is successful ",
					shippingParameter.getName(), pkgId);
		} catch (MocaException e) {
			log.error("Call to {} to update Shipping Parameter {} for package Id {} failed with message {} ", COMMAND,
					shippingParameter.getName(), pkgId, e.getMessage());
		}
	}

	private List<MocaArgument> createParamsForMocaCall(String pkgId, ShippingParameter shippingParameter) {
		List<MocaArgument> params = new ArrayList<>();
		params.add(new MocaArgument("system", SYSTEM_NAME));
		params.add(new MocaArgument("pkg_id", pkgId));
		params.add(new MocaArgument("name", shippingParameter.getName()));
		params.add(new MocaArgument("value", shippingParameter.getValue().getValue()));
		return params;
	}
}
