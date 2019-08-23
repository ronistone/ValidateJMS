package br.com.rgrj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import br.com.rgrj.service.Consumers;
import org.springframework.jms.annotation.EnableJms;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

@Configuration
@ComponentScan
@EnableJms
@ImportResource({"classpath*:app-ctx.xml"})
public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    @Autowired
    private Consumers consumers;



    public void run() throws InterruptedException {

        sleep(5000);
        consumers.sendMessage("Olá");
        consumers.sendMessage("Tudo Bem?");
        consumers.sendMessage("Legal");
        consumers.sendMessage("=D");

    }


    public static void main(String[] args) throws InterruptedException {

        ApplicationContext annotationContext = new AnnotationConfigApplicationContext(Main.class);
        log.info("context loaded");

        Main main = annotationContext.getBean("main", Main.class);

        main.run();

        while(true){
            log.info("Server is alive");
            sleep(100000);
        }

    }

}

