package example.micronaut;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import io.micronaut.http.client.BlockingHttpClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import software.amazon.awssdk.services.sqs.SqsClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@MicronautTest
@TestInstance(PER_CLASS)
public class SqsControllerTest {
    @Inject
    @Client("/")
    HttpClient httpClient;

    @Inject
    SqsClient sqsClient;

    private static HttpRequest<?> saveRequest() {
        return HttpRequest.POST("/demo", "");
    }

    @Test
    void testHandler() {

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest
                .builder()
                .queueUrl("counter-sqs")
                .build();

        BlockingHttpClient client = httpClient.toBlocking();
        HttpResponse<?> saveResponse = client.exchange(saveRequest());
        assertEquals(HttpStatus.NO_CONTENT, saveResponse.status());



        assertEquals(1, sqsClient.receiveMessage(receiveMessageRequest).messages().size());
    }

}
