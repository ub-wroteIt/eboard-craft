package org.ubwroteit.common.queue;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Producer<T> {
    void produceMessage(String topic,T t) throws JsonProcessingException;
}
