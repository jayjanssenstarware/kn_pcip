package kn.swift.pcip.service.common;


import kn.swift.pcip.configuration.properties.PcipProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Getter
@Setter
@Slf4j
public abstract class PcipServiceClient {

   @Autowired
    protected WebServiceTemplate pcipWebServiceTemplate;

    @Autowired
    protected Jaxb2Marshaller pcipMarshaller;

    @Autowired
    protected PcipProperties pcipProperties;

}