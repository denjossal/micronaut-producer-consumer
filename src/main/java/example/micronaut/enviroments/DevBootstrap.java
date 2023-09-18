package example.micronaut.enviroments;

import example.micronaut.repository.service.CounterRepository;
import example.micronaut.service.SqsService;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Singleton;

@Requires(property = "localstack-local.host")
@Requires(property = "sqs-local.port")
@Requires(property = "dynamodb-local.port")
@Requires(env = Environment.TEST)
@Singleton
public class DevBootstrap implements ApplicationEventListener<StartupEvent> {

    private final SqsService sqsService;

    private final CounterRepository counterRepository;

    public DevBootstrap(SqsService sqsService, CounterRepository counterRepository) {
        this.sqsService = sqsService;
        this.counterRepository = counterRepository;
    }



    @Override
    public void onApplicationEvent(StartupEvent event) {
        if (!sqsService.existQueue()) {
            sqsService.createQueue();
        }
        if (!counterRepository.existsTable()) {
            counterRepository.createTable();
        }
    }
}
