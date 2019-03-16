package kn.swift.pcip.service.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.namespace.QName;

import org.springframework.ws.soap.SoapHeaderElement;

import kn.swift.ms.pcip.dto.Audit;

class EsbAuditObjectFactory {

	private static final  QName _AuditHeader_QNAME = new QName("http://knesb.int.kn/xsd/esb/audit/v01", "Audit");

	@XmlElementDecl(namespace = "http://knesb.int.kn/xsd/esb/audit/v01", name = "Audit", scope = SoapHeaderElement.class)
	public JAXBElement<Audit> createAuditHeaderElement(Audit audit) {
		return new JAXBElement<>(_AuditHeader_QNAME, Audit.class, SoapHeaderElement.class, audit);
	}

}
