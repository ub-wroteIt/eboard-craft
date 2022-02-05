package org.ubwroteit.processors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.ubwroteit.common.model.FollowerMessage;
import org.ubwroteit.common.queue.Consumer;
import org.ubwroteit.follower.model.FollowerEntity;

import java.time.LocalDateTime;

@Slf4j
@Component
public class FollowerProcessor implements Consumer<FollowerMessage> {

    @Autowired
    RestTemplate restTemplate;

    @Value("${follower.resourceUrl:http://localhost:8080/follower}")
    String resourceUrl;

    @Override
    @KafkaListener(id = "myId", topics = "${follower.topic:followerTopic}")
    public void consumeMessage(FollowerMessage followerMessage) {
            FollowerEntity followerEntity = new FollowerEntity(followerMessage.getSourceId(), followerMessage.getDestinationId(), followerMessage.getTimestampInMilliseconds(), followerMessage.getStatus(), LocalDateTime.now());
            restTemplate.postForEntity(resourceUrl, followerEntity, FollowerEntity.class);
    }
}
