package kn.swift.pcip.configuration;

import kn.swift.pcip.configuration.properties.EsbProperties;
import kn.swift.pcip.configuration.properties.PcipProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.apache.http.auth.UsernamePasswordCredentials;

@Configuration
public class PcipWebServiceConfig {

    @Autowired
    private PcipProperties pcipProperties;

    @Autowired
    private EsbProperties esbProperties;

    @Bean
    public Jaxb2Marshaller pcipMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(pcipProperties.getJaxb().getContextPath());
        return marshaller;
    }

    @Bean
    public WebServiceTemplate pcipWebServiceTemplate() {
        WebServiceTemplate client = new WebServiceTemplate();
        client.setMarshaller(pcipMarshaller());
        client.setUnmarshaller(pcipMarshaller());
        client.setDefaultUri(esbProperties.getUrl() + esbProperties.getPcipBasePath());
        client.setMessageSender(httpComponentsMessageSender());
        return client;
    }

    private HttpComponentsMessageSender httpComponentsMessageSender() {
        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());
        return httpComponentsMessageSender;
    }

    private UsernamePasswordCredentials usernamePasswordCredentials() {
        return new UsernamePasswordCredentials(esbProperties.getUser(), esbProperties.getPassword());
    }

}
