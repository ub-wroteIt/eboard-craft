package org.ubwroteit.common.queue;

public interface Consumer<T> {
    void consumeMessage(T t);
}
