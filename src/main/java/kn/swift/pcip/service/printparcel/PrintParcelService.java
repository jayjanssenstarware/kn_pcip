package kn.swift.pcip.service.printparcel;

import kn.swift.ms.pcip.dto.*;
import kn.swift.pcip.configuration.properties.PcipProperties;
import kn.swift.pcip.exceptions.SoapServiceException;
import kn.swift.pcip.exceptions.TrackingNumberNotPresentException;
import kn.swift.pcip.service.printparcel.wms.StoreExternalRefsForPackageClient;
import kn.swift.pcip.service.printparcel.wms.UpdateTrackNumberForPackageClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class PrintParcelService {

    private static final String SHIPMENT_IDENTCODE = "Shipment.Identcode";

    private static final String PARCEL_IDENTCODE = "Parcel.Identcode";

    private static final String SHIPMENT_STATUS = "Shipment.Status";

    @Autowired
    private PcipPrintParcelServiceClient pcipPrintParcelServiceClient;

    @Autowired
    private UpdateTrackNumberForPackageClient updateTrackNumberForPackageClient;

    @Autowired
    private StoreExternalRefsForPackageClient storeExternalRefsForPackageClient;

    @Autowired
    private PcipProperties pcipProperties;

    public PrintParcelResponse printParcel(PrintParcelRequest printParcelRequest, Map<String, Object> headers) {

        PrintParcelResponse printParcelResponse = pcipPrintParcelServiceClient.sendPrintParcel(printParcelRequest, headers);

        Status status = printParcelResponse.getExitStatus().getValue().getStatus();
        if (status.equals(Status.SUCCESS)) {
            updatePCIPdataOnWMS(printParcelRequest, printParcelResponse);
        } else if (status.equals(Status.ERROR)) {
            reportError(printParcelResponse);
        }

        return printParcelResponse;
    }

    private void reportError(PrintParcelResponse printParcelResponse) {
        List<StatusDetail> statusDetail = printParcelResponse.getExitStatus().getValue().getStatusDetails().getValue()
                .getStatusDetail().stream().filter(sd -> sd.getStatus().equals(Status.ERROR))
                .collect(Collectors.toList());
        throw new SoapServiceException(statusDetail.get(0).getMessage().getValue(), statusDetail.get(0).getCode());
    }

    private void updatePCIPdataOnWMS(PrintParcelRequest printParcelRequest, PrintParcelResponse printParcelResponse) {

        String packageId = getPackageId(printParcelRequest).orElse(null);

        String trackNumber = getTrackNumber(printParcelResponse).orElseThrow(() -> new TrackingNumberNotPresentException("No tracking number is present for the given package ID"));

        updateTrackNumberForPackageClient.updateTrackNumberForPackage(packageId, trackNumber);

        storeExternalRefsForPackage(packageId, printParcelResponse);
    }

    private Optional<String> getPackageId(PrintParcelRequest printParcelRequest) {
        if (printParcelRequest.getInputParameters() == null || printParcelRequest.getInputParameters().getValue() == null) {
            return Optional.empty();
        }
        return printParcelRequest.getInputParameters().getValue().getShippingParameter().stream()
                .filter(p -> p.getName().equals(pcipProperties.getParcel().getRefno()))
                .findFirst()
                .map(p -> p.getValue().getValue());
    }


    private Optional<String> getTrackNumber(PrintParcelResponse printParcelResponse) {
        if (printParcelResponse.getOutputParameters() == null || printParcelResponse.getOutputParameters().getValue() == null) {
            return Optional.empty();
        }

        return printParcelResponse.getOutputParameters().getValue().getShippingParameter().stream()
                .filter(sp -> sp.getName().equals(pcipProperties.getParcel().getIdentifier()))
                .findFirst()
                .map(p -> p.getValue().getValue());
    }

    private void storeExternalRefsForPackage(String pkgId, PrintParcelResponse printParcelResponse) {
        ShippingParameterCollection shippingParamCollection = printParcelResponse.getOutputParameters().getValue();
        shippingParamCollection.getShippingParameter().stream().filter(this::isShippingParameterValueNotEmpty)
                .filter(this::isShippingParameterNameValid)
                .forEach(shippingParameter -> storeExternalRefsForPackageClient.storeExternalRefsForPackage(pkgId, shippingParameter));
    }

    private boolean isShippingParameterValueNotEmpty(ShippingParameter sp) {
        return !sp.getValue().getValue().isEmpty();
    }

    private boolean isShippingParameterNameValid(ShippingParameter sp) {
        return sp.getName().equalsIgnoreCase(SHIPMENT_IDENTCODE) || sp.getName().equalsIgnoreCase(PARCEL_IDENTCODE)
                || sp.getName().equalsIgnoreCase(SHIPMENT_STATUS);
    }


}