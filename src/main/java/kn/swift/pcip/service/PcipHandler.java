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
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PcipHandler {

    private static final String CTRL_SEG_UUID = "//*[name()='CTRL_SEG']/*[name()='UUID']/text()";
    private final Jaxb2Marshaller pcipMarshaller;
    private final PrintParcelService printParcelService;
    private final CloseShipmentsService closeShipmentsService;
    private final XPathFactory xPathFactory = XPathFactory.newInstance();

    @Autowired
    public PcipHandler(Jaxb2Marshaller pcipMarshaller, PrintParcelService printParcelService, CloseShipmentsService closeShipmentsService){
        this.pcipMarshaller = pcipMarshaller;
        this.printParcelService = printParcelService;
        this.closeShipmentsService = closeShipmentsService;
    }

    public void handle(String wmsMessage){
        String message = Transformer.transform(wmsMessage);
        Map<String, Object> headers = new HashMap<>();
        XPath xpath = xPathFactory.newXPath();
        InputSource xmlMessage = new InputSource(new StringReader(wmsMessage));
        String uuid = null;
        try {
            uuid = xpath.evaluate(CTRL_SEG_UUID, xmlMessage);
        } catch (XPathExpressionException e) {
            log.error("Can't extract UUID {}", e);
        }
        headers.put("X_KN_SWIFT_WMS_UUID", uuid);
        try {
            Object request = pcipMarshaller.unmarshal(new StringSource(message));
            if (request instanceof PrintParcelRequest) {
                printParcelService.printParcel((PrintParcelRequest) request, headers);
            } else if (request instanceof CloseShipmentsRequest) {
                closeShipmentsService.closeShipments((CloseShipmentsRequest) request, headers);
            } else {
                log.error("Unknown Message type: {}", request.getClass());
            }
        } catch (XmlMappingException e) {
            log.error("XML to JAVA parsing exception {}", e);
        }
    }

}
