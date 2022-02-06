package org.ubwroteit.consumers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.ubwroteit.common.model.IdeaMessage;
import org.ubwroteit.common.model.NotificationMessage;
import org.ubwroteit.common.queue.Consumer;
import org.ubwroteit.common.queue.Producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
public class IdeaConsumer implements Consumer<IdeaMessage> {

    @Autowired
    RestTemplate restTemplate;

    @Qualifier("producer/kafka")
    @Autowired
    private Producer<NotificationMessage> producer;

    @Value ("${notification.chunk.size:100}")
    private int chunkSize;

    @Value("${notification.topic:notificationTopic}")
    private String notificationTopic;

    @Value("${follower.resourceUrl:http://localhost:8080/follower/allFollowers/}")
    String resourceUrl;

    @KafkaListener(id = "ideaConsumer1",topics = "${idea.topic:ideaTopic}", containerFactory = "kafkaListenerContainerFactoryForIdea")
    @Override
    public void consumeMessage(IdeaMessage ideaMessage) {
        log.info("Processing started for an Idea {} with title {} posted by {}",ideaMessage.getIdeaId(),ideaMessage.getTitle(), ideaMessage.getContenderId());
        Set<UUID> followers = getFollower(ideaMessage.getContenderId());
        List<List<UUID>> partition = ListUtils.partition(new ArrayList<>(followers), chunkSize);
        int i =0;
        for (List<UUID> citizenIds: partition) {
            NotificationMessage notificationMessage = new NotificationMessage(ideaMessage, citizenIds);
            producer.produceMessage(notificationTopic, notificationMessage);
            log.info("Publishing notification message {} for contenders {} idea ", i, ideaMessage.getContenderId());
        }
        log.info("Processing finished for an Idea {} posted by {}",ideaMessage.getIdeaId(), ideaMessage.getContenderId());
    }

    private Set<UUID> getFollower(UUID contenderId){
        String url = resourceUrl + contenderId.toString();
        ParameterizedTypeReference<Set<UUID>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<Set<UUID>> followerIds = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null), responseType);
        log.info("No of followers = {} found for contender={}",followerIds.getBody().size(), contenderId.toString());
        return followerIds.getBody();
    }
}
