package kn.swift.pcip.service.closeshipments;

import kn.swift.ms.pcip.dto.CloseShipmentsRequest;
import kn.swift.ms.pcip.dto.CloseShipmentsResponse;
import kn.swift.ms.pcip.dto.Status;
import kn.swift.ms.pcip.dto.StatusDetail;
import kn.swift.pcip.configuration.properties.PcipProperties;
import kn.swift.pcip.exceptions.SoapServiceException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class CloseShipmentsService {

    @Autowired
    private PcipCloseShipmentsServiceClient pcipServiceClient;

    @Autowired
    private PcipProperties pcipProperties;

    public CloseShipmentsResponse closeShipments(CloseShipmentsRequest closeShipmentsRequest, Map<String, Object> headers) {

        CloseShipmentsResponse closeShipmentsResponse = pcipServiceClient.sendCloseShipments(closeShipmentsRequest, headers);

        Status status = closeShipmentsResponse.getExitStatus().getValue().getStatus();
        if (status.equals(Status.ERROR)) {
            reportError(closeShipmentsResponse);
        }

        return closeShipmentsResponse;
    }

    private void reportError(CloseShipmentsResponse closeShipmentsResponse) {
        List<StatusDetail> statusDetail = closeShipmentsResponse.getExitStatus().getValue().getStatusDetails().getValue()
                .getStatusDetail().stream().filter(sd -> sd.getStatus().equals(Status.ERROR))
                .collect(Collectors.toList());
        throw new SoapServiceException(statusDetail.get(0).getMessage().getValue(), statusDetail.get(0).getCode());
    }

}
