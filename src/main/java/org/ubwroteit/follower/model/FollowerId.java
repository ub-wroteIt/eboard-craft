package org.ubwroteit.follower.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class FollowerId implements Serializable {
    private UUID sourceId;
    private UUID destinationId;
}
