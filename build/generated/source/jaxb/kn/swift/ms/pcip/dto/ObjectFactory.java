//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.03.15 at 10:26:49 AM GMT 
//


package kn.swift.ms.pcip.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kn.swift.ms.pcip.dto package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _IdentifierType_QNAME = new QName("http://xlogics.eu/blackbox", "IdentifierType");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _Status_QNAME = new QName("http://xlogics.eu/blackbox", "Status");
    private final static QName _StatusDetails_QNAME = new QName("http://xlogics.eu/blackbox", "StatusDetails");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _StatusDetail_QNAME = new QName("http://xlogics.eu/blackbox", "StatusDetail");
    private final static QName _ShippingParameter_QNAME = new QName("http://xlogics.eu/blackbox", "ShippingParameter");
    private final static QName _Authentication_QNAME = new QName("http://xlogics.eu/blackbox", "Authentication");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _Fault_QNAME = new QName("http://xlogics.eu/blackbox", "Fault");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _PrintOperation_QNAME = new QName("http://xlogics.eu/blackbox", "PrintOperation");
    private final static QName _AuthenticationHeader_QNAME = new QName("http://xlogics.eu/blackbox", "AuthenticationHeader");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _ShippingParameterCollection_QNAME = new QName("http://xlogics.eu/blackbox", "ShippingParameterCollection");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _DeleteOperation_QNAME = new QName("http://xlogics.eu/blackbox", "DeleteOperation");
    private final static QName _ExitStatus_QNAME = new QName("http://xlogics.eu/blackbox", "ExitStatus");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _AuthenticationHeaderPassword_QNAME = new QName("http://xlogics.eu/blackbox", "Password");
    private final static QName _AuthenticationHeaderCulture_QNAME = new QName("http://xlogics.eu/blackbox", "Culture");
    private final static QName _AuthenticationHeaderUser_QNAME = new QName("http://xlogics.eu/blackbox", "User");
    private final static QName _DeleteParcelRequestInputParameters_QNAME = new QName("http://xlogics.eu/blackbox", "InputParameters");
    private final static QName _ShippingParameterValue_QNAME = new QName("http://xlogics.eu/blackbox", "Value");
    private final static QName _CloseShipmentsResponseOutputParameters_QNAME = new QName("http://xlogics.eu/blackbox", "OutputParameters");
    private final static QName _StatusDetailMessage_QNAME = new QName("http://xlogics.eu/blackbox", "Message");
    private final static QName _FaultInnerMessage_QNAME = new QName("http://xlogics.eu/blackbox", "InnerMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kn.swift.ms.pcip.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShippingParameterCollection }
     * 
     */
    public ShippingParameterCollection createShippingParameterCollection() {
        return new ShippingParameterCollection();
    }

    /**
     * Create an instance of {@link DeleteParcelRequest }
     * 
     */
    public DeleteParcelRequest createDeleteParcelRequest() {
        return new DeleteParcelRequest();
    }

    /**
     * Create an instance of {@link Audit }
     * 
     */
    public Audit createAudit() {
        return new Audit();
    }

    /**
     * Create an instance of {@link ShippingParameter }
     * 
     */
    public ShippingParameter createShippingParameter() {
        return new ShippingParameter();
    }

    /**
     * Create an instance of {@link CloseShipmentsResponse }
     * 
     */
    public CloseShipmentsResponse createCloseShipmentsResponse() {
        return new CloseShipmentsResponse();
    }

    /**
     * Create an instance of {@link StatusDetail }
     * 
     */
    public StatusDetail createStatusDetail() {
        return new StatusDetail();
    }

    /**
     * Create an instance of {@link PrintParcelResponse }
     * 
     */
    public PrintParcelResponse createPrintParcelResponse() {
        return new PrintParcelResponse();
    }

    /**
     * Create an instance of {@link ExitStatus }
     * 
     */
    public ExitStatus createExitStatus() {
        return new ExitStatus();
    }

    /**
     * Create an instance of {@link StatusDetails }
     * 
     */
    public StatusDetails createStatusDetails() {
        return new StatusDetails();
    }

    /**
     * Create an instance of {@link AuthenticationHeader }
     * 
     */
    public AuthenticationHeader createAuthenticationHeader() {
        return new AuthenticationHeader();
    }

    /**
     * Create an instance of {@link CloseShipmentsRequest }
     * 
     */
    public CloseShipmentsRequest createCloseShipmentsRequest() {
        return new CloseShipmentsRequest();
    }

    /**
     * Create an instance of {@link Audit.BusinessKeys }
     * 
     */
    public Audit.BusinessKeys createAuditBusinessKeys() {
        return new Audit.BusinessKeys();
    }

    /**
     * Create an instance of {@link Fault }
     * 
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link DeleteParcelResponse }
     * 
     */
    public DeleteParcelResponse createDeleteParcelResponse() {
        return new DeleteParcelResponse();
    }

    /**
     * Create an instance of {@link PrintParcelRequest }
     * 
     */
    public PrintParcelRequest createPrintParcelRequest() {
        return new PrintParcelRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentifierType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "IdentifierType")
    public JAXBElement<IdentifierType> createIdentifierType(IdentifierType value) {
        return new JAXBElement<IdentifierType>(_IdentifierType_QNAME, IdentifierType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Status }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "Status")
    public JAXBElement<Status> createStatus(Status value) {
        return new JAXBElement<Status>(_Status_QNAME, Status.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "StatusDetails")
    public JAXBElement<StatusDetails> createStatusDetails(StatusDetails value) {
        return new JAXBElement<StatusDetails>(_StatusDetails_QNAME, StatusDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "StatusDetail")
    public JAXBElement<StatusDetail> createStatusDetail(StatusDetail value) {
        return new JAXBElement<StatusDetail>(_StatusDetail_QNAME, StatusDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "ShippingParameter")
    public JAXBElement<ShippingParameter> createShippingParameter(ShippingParameter value) {
        return new JAXBElement<ShippingParameter>(_ShippingParameter_QNAME, ShippingParameter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticationHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "Authentication")
    public JAXBElement<AuthenticationHeader> createAuthentication(AuthenticationHeader value) {
        return new JAXBElement<AuthenticationHeader>(_Authentication_QNAME, AuthenticationHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "Fault")
    public JAXBElement<Fault> createFault(Fault value) {
        return new JAXBElement<Fault>(_Fault_QNAME, Fault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintOperation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "PrintOperation")
    public JAXBElement<PrintOperation> createPrintOperation(PrintOperation value) {
        return new JAXBElement<PrintOperation>(_PrintOperation_QNAME, PrintOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticationHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "AuthenticationHeader")
    public JAXBElement<AuthenticationHeader> createAuthenticationHeader(AuthenticationHeader value) {
        return new JAXBElement<AuthenticationHeader>(_AuthenticationHeader_QNAME, AuthenticationHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingParameterCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "ShippingParameterCollection")
    public JAXBElement<ShippingParameterCollection> createShippingParameterCollection(ShippingParameterCollection value) {
        return new JAXBElement<ShippingParameterCollection>(_ShippingParameterCollection_QNAME, ShippingParameterCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteOperation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "DeleteOperation")
    public JAXBElement<DeleteOperation> createDeleteOperation(DeleteOperation value) {
        return new JAXBElement<DeleteOperation>(_DeleteOperation_QNAME, DeleteOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExitStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "ExitStatus")
    public JAXBElement<ExitStatus> createExitStatus(ExitStatus value) {
        return new JAXBElement<ExitStatus>(_ExitStatus_QNAME, ExitStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "Password", scope = AuthenticationHeader.class)
    public JAXBElement<String> createAuthenticationHeaderPassword(String value) {
        return new JAXBElement<String>(_AuthenticationHeaderPassword_QNAME, String.class, AuthenticationHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "Culture", scope = AuthenticationHeader.class)
    public JAXBElement<String> createAuthenticationHeaderCulture(String value) {
        return new JAXBElement<String>(_AuthenticationHeaderCulture_QNAME, String.class, AuthenticationHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "User", scope = AuthenticationHeader.class)
    public JAXBElement<String> createAuthenticationHeaderUser(String value) {
        return new JAXBElement<String>(_AuthenticationHeaderUser_QNAME, String.class, AuthenticationHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingParameterCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "InputParameters", scope = DeleteParcelRequest.class)
    public JAXBElement<ShippingParameterCollection> createDeleteParcelRequestInputParameters(ShippingParameterCollection value) {
        return new JAXBElement<ShippingParameterCollection>(_DeleteParcelRequestInputParameters_QNAME, ShippingParameterCollection.class, DeleteParcelRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "Value", scope = ShippingParameter.class)
    public JAXBElement<String> createShippingParameterValue(String value) {
        return new JAXBElement<String>(_ShippingParameterValue_QNAME, String.class, ShippingParameter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingParameterCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "OutputParameters", scope = CloseShipmentsResponse.class)
    public JAXBElement<ShippingParameterCollection> createCloseShipmentsResponseOutputParameters(ShippingParameterCollection value) {
        return new JAXBElement<ShippingParameterCollection>(_CloseShipmentsResponseOutputParameters_QNAME, ShippingParameterCollection.class, CloseShipmentsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExitStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "ExitStatus", scope = CloseShipmentsResponse.class)
    public JAXBElement<ExitStatus> createCloseShipmentsResponseExitStatus(ExitStatus value) {
        return new JAXBElement<ExitStatus>(_ExitStatus_QNAME, ExitStatus.class, CloseShipmentsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingParameterCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "InputParameters", scope = CloseShipmentsRequest.class)
    public JAXBElement<ShippingParameterCollection> createCloseShipmentsRequestInputParameters(ShippingParameterCollection value) {
        return new JAXBElement<ShippingParameterCollection>(_DeleteParcelRequestInputParameters_QNAME, ShippingParameterCollection.class, CloseShipmentsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "Message", scope = StatusDetail.class)
    public JAXBElement<String> createStatusDetailMessage(String value) {
        return new JAXBElement<String>(_StatusDetailMessage_QNAME, String.class, StatusDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingParameterCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "OutputParameters", scope = DeleteParcelResponse.class)
    public JAXBElement<ShippingParameterCollection> createDeleteParcelResponseOutputParameters(ShippingParameterCollection value) {
        return new JAXBElement<ShippingParameterCollection>(_CloseShipmentsResponseOutputParameters_QNAME, ShippingParameterCollection.class, DeleteParcelResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExitStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "ExitStatus", scope = DeleteParcelResponse.class)
    public JAXBElement<ExitStatus> createDeleteParcelResponseExitStatus(ExitStatus value) {
        return new JAXBElement<ExitStatus>(_ExitStatus_QNAME, ExitStatus.class, DeleteParcelResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingParameterCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "OutputParameters", scope = PrintParcelResponse.class)
    public JAXBElement<ShippingParameterCollection> createPrintParcelResponseOutputParameters(ShippingParameterCollection value) {
        return new JAXBElement<ShippingParameterCollection>(_CloseShipmentsResponseOutputParameters_QNAME, ShippingParameterCollection.class, PrintParcelResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExitStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "ExitStatus", scope = PrintParcelResponse.class)
    public JAXBElement<ExitStatus> createPrintParcelResponseExitStatus(ExitStatus value) {
        return new JAXBElement<ExitStatus>(_ExitStatus_QNAME, ExitStatus.class, PrintParcelResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "InnerMessage", scope = Fault.class)
    public JAXBElement<String> createFaultInnerMessage(String value) {
        return new JAXBElement<String>(_FaultInnerMessage_QNAME, String.class, Fault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "Message", scope = Fault.class)
    public JAXBElement<String> createFaultMessage(String value) {
        return new JAXBElement<String>(_StatusDetailMessage_QNAME, String.class, Fault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShippingParameterCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "InputParameters", scope = PrintParcelRequest.class)
    public JAXBElement<ShippingParameterCollection> createPrintParcelRequestInputParameters(ShippingParameterCollection value) {
        return new JAXBElement<ShippingParameterCollection>(_DeleteParcelRequestInputParameters_QNAME, ShippingParameterCollection.class, PrintParcelRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xlogics.eu/blackbox", name = "StatusDetails", scope = ExitStatus.class)
    public JAXBElement<StatusDetails> createExitStatusStatusDetails(StatusDetails value) {
        return new JAXBElement<StatusDetails>(_StatusDetails_QNAME, StatusDetails.class, ExitStatus.class, value);
    }

}
