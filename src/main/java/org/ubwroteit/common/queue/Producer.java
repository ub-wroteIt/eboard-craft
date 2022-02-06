package org.ubwroteit.common.queue;

public interface Producer<T> {
    void produceMessage(String topic,T t);
}
