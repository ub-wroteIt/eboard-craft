package org.ubwroteit.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.ubwroteit.common.model.FollowerMessage;

import static org.junit.jupiter.api.Assertions.*;


class FollowerProcessorTest {

    ObjectMapper objectMapper= new ObjectMapper();
    @Test
    void consumeMessage() throws JsonProcessingException {
        String a = "{\"sourceId\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"destinationId\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"timestampInMilliseconds\":1644074920183,\"status\":\"POSITIVE\"}";
        objectMapper.registerModule(new JavaTimeModule());

        FollowerMessage followerMessage = this.objectMapper.readValue(a, FollowerMessage.class);

        assertTrue(true);
    }
}