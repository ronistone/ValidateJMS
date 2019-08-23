package br.com.rgrj.service.jms;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SimpleConsumer {

    private static Logger log = Logger.getLogger(SimpleConsumer.class.getName());

    @JmsListener( destination = "SIMPLE-QUEUE")
    public void listen(Message message){
        log.info("I have a message!");
        log.info("Message headers: " + message.getHeaders());
        log.info("Message Payload: " + message.getPayload());

        throw new RuntimeException("Falhou");
    }


}
