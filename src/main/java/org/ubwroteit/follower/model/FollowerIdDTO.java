package org.ubwroteit.follower.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class FollowerIdDTO {
    private UUID sourceId;
}
