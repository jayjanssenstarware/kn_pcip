package kn.swift.pcip.flow;

import kn.swift.pcip.configuration.properties.WmsProperties;
import kn.swift.pcip.service.PcipHandler;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.messaging.MessageChannel;

import javax.annotation.PostConstruct;


@Configuration
@EnableIntegration
public class IntegrationSetup {



    private final PcipHandler handler;
    private final IntegrationFlowContext integrationFlowContext;
    private  final WmsProperties wmsProperties;

    @Autowired
    public IntegrationSetup(IntegrationFlowContext integrationFlowContext, PcipHandler handler, WmsProperties wmsProperties){
        this.integrationFlowContext = integrationFlowContext;
        this.handler = handler;
        this.wmsProperties = wmsProperties;
    }


    @PostConstruct
    public void flows(){
        this.integrationFlowContext.registration(amqInboundFlow(wmsProperties.getJms().getBroker(), wmsProperties.getJms().getDestination())).register();
        this.integrationFlowContext.registration(httpInboundFlow("/pcip")).register();
    }

    private IntegrationFlow httpInboundFlow(String endpoint){
        return IntegrationFlows.from(Http.inboundGateway(endpoint)
                .requestMapping(m -> m.methods(HttpMethod.POST))
                .requestPayloadType(String.class).replyChannel(directChannel())
        )
                .channel("httpRequest")
                .handle(handler, "handle")
                .get();
    }

    // TODO: Handle errors - this appears transactional and retries 'X' times - errorChannel()?
    private IntegrationFlow amqInboundFlow(String broker, String queue){
        return IntegrationFlows.from(Jms.inboundGateway(new ActiveMQConnectionFactory(broker))
                .destination(queue))
                .handle(handler, "handle")
                .get();
    }

    @Bean
    public MessageChannel directChannel(){
        return MessageChannels.direct().get();
    }
}
