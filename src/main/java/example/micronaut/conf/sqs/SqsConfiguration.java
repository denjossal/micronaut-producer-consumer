/*
 * Copyright 2023 denjossal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example.micronaut.conf.sqs;

import io.micronaut.jms.annotations.JMSProducer;
import io.micronaut.jms.annotations.Queue;
import io.micronaut.messaging.annotation.MessageBody;

import static io.micronaut.jms.sqs.configuration.SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSProducer(CONNECTION_FACTORY_BEAN_NAME)
public interface SqsConfiguration {

    @Queue("counter-sqs")
    void send(@MessageBody String body);
}