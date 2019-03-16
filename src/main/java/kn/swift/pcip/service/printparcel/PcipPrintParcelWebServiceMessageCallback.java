package kn.swift.pcip.service.printparcel;

import kn.swift.ms.pcip.dto.Audit;
import kn.swift.pcip.configuration.properties.PcipProperties;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessage;
import org.w3c.dom.Document;
import kn.swift.pcip.service.common.PcipWebServiceMessageCallback;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Map;

public class PcipPrintParcelWebServiceMessageCallback extends PcipWebServiceMessageCallback {

    public PcipPrintParcelWebServiceMessageCallback(PcipProperties pcipProperties, Jaxb2Marshaller pcipMarshaller, Map<String, Object> headers) {
        super(pcipProperties, pcipMarshaller, headers);
    }

    @Override
    public void doWithMessage(WebServiceMessage message)throws TransformerException {
        super.doWithMessage(message, "PrintParcel");
    }

    @Override
    protected Audit.BusinessKeys createBusinessKeys(Document payload) throws TransformerException {

        Audit.BusinessKeys auditBusinessKeys = pcipObjectFactory.createAuditBusinessKeys();
        auditBusinessKeys.setBusinessKey1(getDocumentValue(payload, pcipProperties.getXpath().getWarehouseId()));
        auditBusinessKeys.setBusinessKey2(getDocumentValue(payload, pcipProperties.getXpath().getClientId()));
        auditBusinessKeys.setBusinessKey3(getDocumentValue(payload, pcipProperties.getXpath().getShipmentRefno()));
        auditBusinessKeys.setBusinessKey4(getDocumentValue(payload, pcipProperties.getXpath().getKnSscc()));
        return auditBusinessKeys;
    }
}