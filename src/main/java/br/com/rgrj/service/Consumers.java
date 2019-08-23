package br.com.rgrj.service;

import br.com.rgrj.service.jms.SimpleProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class Consumers {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private SimpleProducer producer;

    private Connection connection;

    public void sendMessage(final String payload) {

        Assert.notNull(jmsTemplate);

        jmsTemplate.send(new ActiveMQQueue("SIMPLE-QUEUE"), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createMessage();

                message.setStringProperty("payload", payload);
                message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);

                return message;
            }
        });

    }


}
