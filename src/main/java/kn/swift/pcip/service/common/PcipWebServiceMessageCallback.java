package kn.swift.pcip.service.common;

import kn.swift.ms.pcip.dto.Audit;
import kn.swift.ms.pcip.dto.AuthenticationHeader;
import kn.swift.ms.pcip.dto.ObjectFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import kn.swift.pcip.configuration.properties.PcipProperties;

import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

public abstract class PcipWebServiceMessageCallback implements WebServiceMessageCallback {

    private static final String APPLICATION_ID = "SwiftLOG";

    protected ObjectFactory pcipObjectFactory = new ObjectFactory();

    private EsbAuditObjectFactory esbAuditObjectFactory = new EsbAuditObjectFactory();

    private XPathFactory xPathFactory = XPathFactory.newInstance();

    protected PcipProperties pcipProperties;

    private Jaxb2Marshaller pcipMarshaller;

    private TransformerFactory transformerFactory;

    private final Map<String, Object> headers;

    private DocumentBuilderFactory documentBuilderFactory;

    public PcipWebServiceMessageCallback(PcipProperties pcipProperties, Jaxb2Marshaller pcipMarshaller, Map<String, Object> headers) {
        this.pcipProperties = pcipProperties;
        this.pcipMarshaller = pcipMarshaller;
        this.headers = headers;

        this.transformerFactory = TransformerFactory.newInstance();
        this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
    }

    public void doWithMessage(WebServiceMessage message, String action) throws TransformerException {
        SoapMessage soapMessage = (SoapMessage) message;
        SoapHeader soapHeader = soapMessage.getSoapHeader();

        JAXBElement<AuthenticationHeader> authenticationHeader = pcipObjectFactory.createAuthentication(createHeaderElement());
        JAXBElement<Audit> auditHeader = esbAuditObjectFactory.createAuditHeaderElement(createAuditElement(soapMessage, headers));

        pcipMarshaller.marshal(authenticationHeader, soapHeader.getResult());
        pcipMarshaller.marshal(auditHeader, soapHeader.getResult());

        soapMessage.setSoapAction("http://xlogics.eu/blackbox/BlackBoxContract/" + action);
    }

    private AuthenticationHeader createHeaderElement() {
        AuthenticationHeader authHeaderElement = pcipObjectFactory.createAuthenticationHeader();
        authHeaderElement.setCulture(pcipObjectFactory.createAuthenticationHeaderCulture(pcipProperties.getAuthHeader().getCulture()));
        authHeaderElement.setUser(pcipObjectFactory.createAuthenticationHeaderUser(pcipProperties.getAuthHeader().getUser()));
        authHeaderElement.setPassword(pcipObjectFactory.createAuthenticationHeaderPassword(pcipProperties.getAuthHeader().getPassword()));
        return authHeaderElement;
    }

    private Audit createAuditElement(SoapMessage soapMessage, Map<String, Object> headers) throws TransformerException {
        Audit audit = new Audit();
        audit.setApplicationID(APPLICATION_ID);

        String uuid = UUID.randomUUID().toString(); // TODO: (String) headers.get(Headers.X_KN_SWIFT_WMS_UUID.name());
        audit.setCorrelationID(uuid);
        audit.setRequestID(uuid);

        Document document = getBodyPayloadDocument(soapMessage);
        audit.setBusinessKeys(createBusinessKeys(document));
        return audit;
    }

    private Document getBodyPayloadDocument(SoapMessage soapMessage) throws TransformerException {
        DOMSource domSource = (DOMSource) soapMessage.getSoapBody().getPayloadSource();

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        StringResult xml = new StringResult();
        transformer.transform(domSource, xml);

        DocumentBuilder builder;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new TransformerException("Could not create document builder", e);
        }
        try {
            return builder.parse(new ByteArrayInputStream(xml.toString().getBytes(StandardCharsets.UTF_8)));
        } catch (SAXException | IOException e) {
            throw new TransformerException("Could not create payload document", e);
        }
    }

    protected abstract Audit.BusinessKeys createBusinessKeys(Document document) throws TransformerException;

    public String getDocumentValue(Document document, String xPathExpression) throws TransformerException {
        try {
            XPath xpath = xPathFactory.newXPath();
            XPathExpression expression = xpath.compile(xPathExpression);
            return (String) expression.evaluate(document, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new TransformerException("Unable to parse document for value: " + xPathExpression, e);
        }
    }
}
