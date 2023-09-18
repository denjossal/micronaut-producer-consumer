package example.micronaut.service.counter;


import java.util.concurrent.ConcurrentLinkedQueue;

public interface CounterService {

    void updateCounter();

    void saveRecords(ConcurrentLinkedQueue<String> concurrentLinkedQueue);

}
