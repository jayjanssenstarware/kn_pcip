package kn.swift.pcip.flow;

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



    @Autowired
    Transformer transformer;

    @Autowired
    PcipHandler handler;

    @Autowired
    private IntegrationFlowContext integrationFlowContext;


    @PostConstruct
    public void flows(){
        this.integrationFlowContext.registration(amqInboundFlow("tcp://localhost:61616", "mailbox2")).register();
    }


    // TODO: Handle errors - this appears transactional and retries 'X' times - errorChannel()?
    public IntegrationFlow amqInboundFlow(String broker, String queue){
        return IntegrationFlows.from(Jms.inboundGateway(new ActiveMQConnectionFactory(broker))
                .destination(queue))
                .transform(Transformer::transform)
              //  .wireTap("loggingFlow.input")
                .handle(handler, "handle")
                .get();
    }

      // TODO: Cleanup
//    @Bean
//    public IntegrationFlow loggingFlow(){
//        return f -> f.log();
//    }

}
