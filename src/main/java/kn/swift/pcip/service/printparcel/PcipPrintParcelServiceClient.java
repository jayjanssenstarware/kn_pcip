package kn.swift.pcip.service.printparcel;

import kn.swift.ms.pcip.dto.PrintParcelRequest;
import kn.swift.ms.pcip.dto.PrintParcelResponse;
import kn.swift.pcip.service.common.PcipServiceClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PcipPrintParcelServiceClient extends PcipServiceClient {

    public PrintParcelResponse sendPrintParcel(PrintParcelRequest request, Map<String, Object> headers) {
        return (PrintParcelResponse) pcipWebServiceTemplate.marshalSendAndReceive(
                request,
                new PcipPrintParcelWebServiceMessageCallback(pcipProperties, pcipMarshaller, headers));
    }

}
