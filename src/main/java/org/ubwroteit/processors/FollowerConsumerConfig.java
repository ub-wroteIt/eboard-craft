package org.ubwroteit.processors;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.ubwroteit.common.model.FollowerMessage;
import org.ubwroteit.common.queue.config.AbstractConsumerConfig;

@Configuration
public class FollowerConsumerConfig extends AbstractConsumerConfig<String, FollowerMessage, StringDeserializer, JsonDeserializer<FollowerMessage>> {

    @Override
    protected StringDeserializer getKeyDeserializer() {
        return new StringDeserializer();
    }

    @Override
    protected JsonDeserializer getValueDeserializer() {
        return new JsonDeserializer<>(FollowerMessage.class);
    }

    @Bean(name = "kafkaListenerContainerFactoryForFollower")
    @Override
    public KafkaListenerContainerFactory getBean() {
        return super.createContainerFactory();
    }

}
