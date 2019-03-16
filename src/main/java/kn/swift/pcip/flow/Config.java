package kn.swift.pcip.flow;

import kn.swift.pcip.configuration.properties.WmsProperties;
import kn.swift.pcip.service.PcipHandler;
import kn.swift.pcip.service.Transformer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.jms.dsl.Jms;

import javax.annotation.PostConstruct;


@Configuration
@EnableIntegration
public class Config {



    private final PcipHandler handler;
    private final IntegrationFlowContext integrationFlowContext;
    private  final WmsProperties wmsProperties;

    @Autowired
    public Config (IntegrationFlowContext integrationFlowContext, PcipHandler handler, WmsProperties wmsProperties){
        this.integrationFlowContext = integrationFlowContext;
        this.handler = handler;
        this.wmsProperties = wmsProperties;
    }


    @PostConstruct
    @Autowired
    public void flows(){
        this.integrationFlowContext.registration(amqInboundFlow(wmsProperties.getJms().getBroker(), wmsProperties.getJms().getDestination())).register();
    }


    // TODO: Handle errors - this appears transactional and retries 'X' times - errorChannel()?
    private IntegrationFlow amqInboundFlow(String broker, String queue){
        return IntegrationFlows.from(Jms.inboundGateway(new ActiveMQConnectionFactory(broker))
                .destination(queue))
                .handle(handler, "handle")
                .get();
    }
}
