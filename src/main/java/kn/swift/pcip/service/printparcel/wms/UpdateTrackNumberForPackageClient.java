package kn.swift.pcip.service.printparcel.wms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redprairie.moca.MocaArgument;
import com.redprairie.moca.MocaException;

import kn.swift.pcip.util.PCIPConnectionWrapper;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UpdateTrackNumberForPackageClient {

	private static final Logger log = LoggerFactory.getLogger(UpdateTrackNumberForPackageClient.class);

	private static final String COMMAND = "update usr track number for package";
	
	@Autowired
	@Setter
	private PCIPConnectionWrapper util;

	public void updateTrackNumberForPackage(String pkgId, String trackNumber) {
		log.info("Calling MOCA command {} on WMS to Update Tracking Number {} for package Id {} ", COMMAND, trackNumber,
				pkgId);
		try {
			List<MocaArgument> params = createParamsForMocaCall(pkgId, trackNumber);
			util.executeCommand(COMMAND, params.toArray(new MocaArgument[params.size()]));
			log.info("Call to WMS endpoint to update Tracking Number {} for package Id {} is successful", trackNumber,
					pkgId);
		} catch (MocaException e) {
			log.error("Call to {} to update Tracking Number {} for package Id {} failed with error message {}", COMMAND,
					trackNumber, pkgId, e.getMessage());
		}
	}

	private List<MocaArgument> createParamsForMocaCall(String pkgId, String trackNumber) {
		List<MocaArgument> params = new ArrayList<>();
		params.add(new MocaArgument("pkg_id", pkgId));
		params.add(new MocaArgument("traknm", trackNumber));
		return params;
	}
}
