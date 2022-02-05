package org.ubwroteit.common.queue.config;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

abstract public class AbstractConsumerConfig <P, Q, K extends Deserializer<P>,V extends Deserializer<Q>>{

    @Autowired
    BaseSchemaConfig baseSchemaConfig;

    abstract protected K getKeyDeserializer();
    abstract protected V getValueDeserializer();
    abstract public KafkaListenerContainerFactory getBean();

    private ConsumerFactory<P, Q> consumerFactory(K k, V v) {
        return new DefaultKafkaConsumerFactory<>(baseSchemaConfig.consumerConfigs(), getKeyDeserializer(), getValueDeserializer());
    }

     private ConcurrentKafkaListenerContainerFactory<P,Q> kafkaListenerContainerFactory(K k, V v) {
        ConcurrentKafkaListenerContainerFactory<P,Q> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(k,v));
        return factory;
    }

     public KafkaListenerContainerFactory createContainerFactory(){
        return kafkaListenerContainerFactory(getKeyDeserializer(), getValueDeserializer());
     }

}
