package org.ubwroteit.processors;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.ubwroteit.common.model.NotificationMessage;
import org.ubwroteit.common.queue.config.AbstractConsumerConfig;

@Configuration
public class NotificationConsumerConfig extends AbstractConsumerConfig<String, NotificationMessage, StringDeserializer, JsonDeserializer<NotificationMessage>> {
    @Override
    protected StringDeserializer getKeyDeserializer() {
        return new StringDeserializer();
    }

    @Override
    protected JsonDeserializer<NotificationMessage> getValueDeserializer() {
        return new JsonDeserializer<>(NotificationMessage.class);
    }

    @Bean(name = "kafkaListenerContainerFactoryForNotification")
    @Override
    public KafkaListenerContainerFactory getBean() {
        return super.createContainerFactory();
    }
}
