package org.ubwroteit.common.queue.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class TopicConfig {

    @Value("${follower.topic:followerTopic}")
    private String followerTopicName;

    @Value("${idea.topic:ideaTopic}")
    private String ideaTopicName;

    @Value("${notification.topic:notificationTopic}")
    private String notificationTopic;

    @Bean
    public KafkaAdmin.NewTopics eboardTopics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(followerTopicName)
                        .build(),
                TopicBuilder.name(ideaTopicName)
                        .build(),
                TopicBuilder.name(notificationTopic)
                        .build()
                );
    }

}
