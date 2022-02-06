package org.ubwroteit.processors;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.ubwroteit.common.model.IdeaMessage;
import org.ubwroteit.common.queue.config.AbstractConsumerConfig;

@Configuration
public class IdeaConsumerConfig extends AbstractConsumerConfig<String, IdeaMessage, StringDeserializer, JsonDeserializer<IdeaMessage>> {
    @Override
    protected StringDeserializer getKeyDeserializer() {
        return new StringDeserializer();
    }

    @Override
    protected JsonDeserializer<IdeaMessage> getValueDeserializer() {
        return new JsonDeserializer<>(IdeaMessage.class);
    }

    @Bean(name = "kafkaListenerContainerFactoryForIdea")
    @Override
    public KafkaListenerContainerFactory getBean() {
        return super.createContainerFactory();
    }
}
