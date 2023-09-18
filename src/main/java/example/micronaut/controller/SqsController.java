package example.micronaut.controller;

import example.micronaut.conf.sqs.SqsConfiguration;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class SqsController {
    private static final Logger LOG = LoggerFactory.getLogger(SqsController.class);

    private final SqsConfiguration sqsConfiguration;

    public SqsController(SqsConfiguration sqsConfiguration) {
        this.sqsConfiguration = sqsConfiguration;
    }

    @Post("/demo")
    @Status(HttpStatus.NO_CONTENT)
    public void publishDemoMessages() {
        LOG.info("Sending message");
        sqsConfiguration.send("Demo message body");
    }
}
