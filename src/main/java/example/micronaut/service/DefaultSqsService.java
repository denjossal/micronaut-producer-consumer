package example.micronaut.service;

import example.micronaut.conf.sqs.SqsConfiguration;
import jakarta.inject.Singleton;
import software.amazon.awssdk.services.sqs.SqsClient;

@Singleton
public class DefaultSqsService extends SqsService {

    public DefaultSqsService(SqsClient sqsClient, SqsConfiguration sqsConfiguration) {
        super(sqsClient, sqsConfiguration);
    }

}
