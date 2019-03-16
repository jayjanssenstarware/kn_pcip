//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.03.15 at 10:26:49 AM GMT 
//


package kn.swift.ms.pcip.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExitStatus" type="{http://xlogics.eu/blackbox}ExitStatus" minOccurs="0"/>
 *         &lt;element name="OutputParameters" type="{http://xlogics.eu/blackbox}ShippingParameterCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "exitStatus",
    "outputParameters"
})
@XmlRootElement(name = "CloseShipmentsResponse")
public class CloseShipmentsResponse {

    @XmlElementRef(name = "ExitStatus", namespace = "http://xlogics.eu/blackbox", type = JAXBElement.class)
    protected JAXBElement<ExitStatus> exitStatus;
    @XmlElementRef(name = "OutputParameters", namespace = "http://xlogics.eu/blackbox", type = JAXBElement.class)
    protected JAXBElement<ShippingParameterCollection> outputParameters;

    /**
     * Gets the value of the exitStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ExitStatus }{@code >}
     *     
     */
    public JAXBElement<ExitStatus> getExitStatus() {
        return exitStatus;
    }

    /**
     * Sets the value of the exitStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ExitStatus }{@code >}
     *     
     */
    public void setExitStatus(JAXBElement<ExitStatus> value) {
        this.exitStatus = ((JAXBElement<ExitStatus> ) value);
    }

    /**
     * Gets the value of the outputParameters property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ShippingParameterCollection }{@code >}
     *     
     */
    public JAXBElement<ShippingParameterCollection> getOutputParameters() {
        return outputParameters;
    }

    /**
     * Sets the value of the outputParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ShippingParameterCollection }{@code >}
     *     
     */
    public void setOutputParameters(JAXBElement<ShippingParameterCollection> value) {
        this.outputParameters = ((JAXBElement<ShippingParameterCollection> ) value);
    }

}