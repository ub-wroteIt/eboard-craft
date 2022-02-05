package org.ubwroteit.common.queue.consumers;

import org.ubwroteit.common.queue.Consumer;

public class ConsumerKafkaImpl<T> implements Consumer<T> {

    @Override
    public void consumeMessage(T t) {
    }
}
