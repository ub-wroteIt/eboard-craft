package org.ubwroteit.common.queue.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.ubwroteit.common.queue.Producer;

@Qualifier("producer/kafka")
@Component
public class ProducerKafkaImpl<T> implements Producer<T> {

    @Autowired
    private KafkaTemplate<Integer, T> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void produceMessage(String topic, T t) throws JsonProcessingException {
            kafkaTemplate.send(topic, t);
    }
}
