package org.ubwroteit.consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.ubwroteit.common.model.NotificationMessage;
import org.ubwroteit.common.queue.Consumer;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class NotificationConsumer implements Consumer<NotificationMessage> {

    @Autowired
    RestTemplate restTemplate;

    @Value("${citizen.resourceUrl:http://localhost:8080/citizen/batch/contacts}")
    String resourceUrl;


    @KafkaListener(id = "notificationConsumer1",topics = "${notification.topic:notificationTopic}", containerFactory = "kafkaListenerContainerFactoryForNotification")
    @Override
    public void consumeMessage(NotificationMessage notificationMessage) {
        Map<UUID, String> userEmailMap = fetchCitizenEmail(notificationMessage.getCitizenIds());
        for (Map.Entry<UUID, String> entry : userEmailMap.entrySet()) {
              log.info("Sending Mail to {} for Idea {}", entry.getValue(), notificationMessage.getIdeaMessage().getTitle());
        }
    }

    public Map<UUID, String> fetchCitizenEmail(List<UUID> citizenIds){
        ParameterizedTypeReference<Map<UUID, String>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(resourceUrl, citizenIds, Map.class);
        return responseEntity.getBody();
    }
}
