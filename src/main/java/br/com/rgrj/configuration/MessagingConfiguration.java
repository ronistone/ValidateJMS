package br.com.rgrj.configuration;

import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import java.util.logging.Logger;

@Configuration
public class MessagingConfiguration {

    private static Logger log = Logger.getLogger(MessagingConfiguration.class.getName());

    @Value("${server.jms.failover}")
    private String broker_failover;

    @Value("${jms.maxPoolSize}")
    private int maxPoolSize;

    public MessagingConfiguration(){
        super();
        log.info("MessagingConfiguration loaded broker_failover: " + broker_failover);
        log.info("MessagingConfiguration loaded maxPoolSize: " + maxPoolSize);
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        connectionFactory.setBrokerURL(broker_failover);
        connectionFactory.setMaxThreadPoolSize(maxPoolSize);

        RedeliveryPolicy policy = connectionFactory.getRedeliveryPolicy();
        policy.setInitialRedeliveryDelay(500);
        policy.setBackOffMultiplier(2);
        policy.setUseExponentialBackOff(true);
        policy.setMaximumRedeliveries(2);

        log.info("MessagingConfiguration loaded broker_failover: " + broker_failover);
        log.info("MessagingConfiguration loaded maxPoolSize: " + maxPoolSize);

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();

        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
        jmsTemplate.setSessionTransacted(Boolean.TRUE);
        jmsTemplate.setDeliveryPersistent(Boolean.TRUE);
        jmsTemplate.setExplicitQosEnabled(Boolean.TRUE);

        return jmsTemplate;
    }

}
