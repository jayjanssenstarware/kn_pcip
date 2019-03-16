package kn.swift.pcip.service;


import kn.swift.ms.pcip.dto.CloseShipmentsRequest;
import kn.swift.ms.pcip.dto.PrintParcelRequest;
import kn.swift.pcip.service.closeshipments.CloseShipmentsService;
import kn.swift.pcip.service.printparcel.PrintParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.xml.transform.StringSource;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PcipHandler {

    @Autowired
    private Jaxb2Marshaller pcipMarshaller;

    @Autowired
    private PrintParcelService printParcelService;

    @Autowired
    private CloseShipmentsService closeShipmentsService;


    public void handle(String message){
        Object response;
        Map<String, Object> headers = new HashMap<>();
        try {
            Object request = pcipMarshaller.unmarshal(new StringSource(message));
            if (request instanceof PrintParcelRequest) {
                response = printParcelService.printParcel((PrintParcelRequest) request, headers);
            } else if (request instanceof CloseShipmentsRequest) {
                response = closeShipmentsService.closeShipments((CloseShipmentsRequest) request, headers);
            } else {
                log.error("Unknown Message type: {}", request.getClass());
            }
        } catch (XmlMappingException e) {
            log.error("XML to JAVA parsing exception {}", e);
        }
    }

}
