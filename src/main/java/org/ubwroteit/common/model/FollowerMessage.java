package org.ubwroteit.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.ubwroteit.follower.model.FollowerStatus;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FollowerMessage implements Serializable {
    private UUID sourceId;
    private UUID destinationId;
    private long timestampInMilliseconds;
    private FollowerStatus status;
}
