package org.ubwroteit.follower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubwroteit.follower.model.FollowerEntity;
import org.ubwroteit.follower.model.FollowerId;

import java.util.List;
import java.util.UUID;

public interface FollowerRepository extends JpaRepository<FollowerEntity, FollowerId> {
    List<FollowerEntity> getFollowerBySourceId(UUID sourceId);
    List<FollowerEntity> getFollowerByDestinationId(UUID destinationId);

}
