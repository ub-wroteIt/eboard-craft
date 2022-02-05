package org.ubwroteit.common.queue.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.ubwroteit.common.model.FollowerMessage;

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    BaseSchemaConfig baseSchemaConfig;

    @Bean
    public ConsumerFactory<String, FollowerMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(baseSchemaConfig.consumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(FollowerMessage.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FollowerMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, FollowerMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
