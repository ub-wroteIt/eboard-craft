package org.ubwroteit.follower.model;

import java.util.UUID;


public class FollowerEntity {
    private UUID sourceId;
    private UUID destinationId;
    private int position;
    private FollowerState followerState;
}
