package kn.swift.pcip.service.closeshipments;

import kn.swift.ms.pcip.dto.CloseShipmentsRequest;
import kn.swift.ms.pcip.dto.CloseShipmentsResponse;
import kn.swift.pcip.service.common.PcipServiceClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
@Setter
public class PcipCloseShipmentsServiceClient extends PcipServiceClient {

    public CloseShipmentsResponse sendCloseShipments(CloseShipmentsRequest request, Map<String, Object> headers) {
        return (CloseShipmentsResponse) getPcipWebServiceTemplate().marshalSendAndReceive(
                request,
                new PcipCloseShipmentWebServiceMessageCallback(pcipProperties, pcipMarshaller, headers));
    }

}