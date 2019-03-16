package kn.swift.pcip.service.printparcel.wms;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swift.wms.w001.client.api.DefaultApi;
import swift.wms.w001.client.model.UpdateTrackNumberForPackage;

@Component
@Getter
@Setter
public class UpdateTrackNumberForPackageClient {

	private static final Logger log = LoggerFactory.getLogger(UpdateTrackNumberForPackageClient.class);

	@Autowired
	private DefaultApi wmsApi;

	public void updateTrackNumberForPackage(String pkgId, String trackNumber) {
		log.info("Calling endpoint on WMS to Update Tracking Number {} for package Id {} ", trackNumber, pkgId);
		UpdateTrackNumberForPackage requestUpdateTrackNumberForPackage = createRequestUpdateTrackNumberForPackage(pkgId,
				trackNumber);
		wmsApi.updateTrackNumberForPackage(requestUpdateTrackNumberForPackage);
		log.info("Call to WMS endpoint to update Tracking Number {} for package Id {} is successful", trackNumber,
				pkgId);
	}

	private UpdateTrackNumberForPackage createRequestUpdateTrackNumberForPackage(String pkgId, String trackNumber) {
		UpdateTrackNumberForPackage request = new UpdateTrackNumberForPackage();
		request.setPkgId(pkgId);
		request.setTraknm(trackNumber);
		return request;
	}

}
