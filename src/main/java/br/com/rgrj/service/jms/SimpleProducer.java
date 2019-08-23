package br.com.rgrj.service.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class SimpleProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String payload) {

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createMessage();

                message.setStringProperty("payload", payload);

                return message;
            }
        });

    }

}
