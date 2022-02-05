package org.ubwroteit.follower.service;

import org.ubwroteit.follower.model.FollowerEntity;
import org.ubwroteit.follower.model.FollowerId;

import java.util.List;
import java.util.UUID;

public interface FollowerService {

    FollowerEntity saveFollower(FollowerEntity followerEntity);
    void deleteFollowerById(FollowerId followerId);
    FollowerEntity updateFollower(FollowerEntity followerEntity);
    FollowerEntity getFollower(FollowerId followerId);
    List<FollowerEntity> getFollowerBySourceId(UUID sourceId);
    List<FollowerEntity> getFollowerByDestinationId(UUID sourceId);
}
