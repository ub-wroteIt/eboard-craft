package org.ubwroteit.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ubwroteit.follower.model.FollowerStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class FollowerMessage implements Serializable {
    private UUID sourceId;
    private UUID destinationId;
    private LocalDateTime creationTime;
    private FollowerStatus status;
}
