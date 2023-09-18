package example.micronaut.controller;

import example.micronaut.service.counter.CounterService;
import io.micronaut.jms.annotations.JMSListener;
import io.micronaut.jms.annotations.Queue;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

import static io.micronaut.jms.sqs.configuration.SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
public class SqsConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqsConsumer.class);

    private final CounterService counterService;

    private static final ConcurrentLinkedQueue<String> globalQueue = new ConcurrentLinkedQueue<>();

    public SqsConsumer(CounterService counterService) {
        this.counterService = counterService;
    }

    @Queue(value = "counter-sqs")
    public void receive(@MessageBody String body) {
        LOGGER.info("Message has been consumed. Message body: {}", body);
        globalQueue.add(body);
        if(globalQueue.size()==100){
            counterService.saveRecords(globalQueue);
        }
    }
}