package org.ubwroteit.common.queue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class TopicConfig {

    @Bean
    public KafkaAdmin.NewTopics eboardTopics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("followerTopic")
                        .build(),
                TopicBuilder.name("ideaTopic")
                        .build());
    }
}
