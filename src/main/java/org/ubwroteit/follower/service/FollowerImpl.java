package org.ubwroteit.follower.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ubwroteit.follower.model.FollowerEntity;
import org.ubwroteit.follower.model.FollowerId;
import org.ubwroteit.follower.model.FollowerIdDTO;
import org.ubwroteit.follower.repository.FollowerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class FollowerImpl implements FollowerService {

    @Autowired
    FollowerRepository followerRepository;

    @Override
    public FollowerEntity saveFollower(FollowerEntity followerEntity) {
        //TODO create Valid annotation for Entities
        return followerRepository.save(followerEntity);
    }

    @Override
    public FollowerEntity createFollower(FollowerEntity followerEntity) {
        FollowerId followerId = new FollowerId(followerEntity.getSourceId(), followerEntity.getDestinationId());
        if (!followerRepository.existsById(followerId)) {
            return followerRepository.save(followerEntity);
        }
        return followerEntity;
    }

    @Override
    public void deleteFollowerById(FollowerId followerId) {
        followerRepository.deleteById(followerId);
    }

    @Override
    public FollowerEntity updateFollower(FollowerEntity followerEntity) {
        return followerRepository.save(followerEntity);
    }

    @Override
    public FollowerEntity getFollower(FollowerId followerId) {
        return followerRepository.getById(followerId);
    }

    @Override
    public List<FollowerEntity> getFollowerBySourceId(UUID sourceId) {
        return followerRepository.getFollowerBySourceId(sourceId);
    }

    @Override
    public List<FollowerEntity> getFollowerByDestinationId(UUID destinationId) {
        return followerRepository.getFollowerByDestinationId(destinationId);
    }

    @Override
    public List<FollowerIdDTO> getFollowersId(UUID sourceId) {
        return followerRepository.getFollowersIdByDestinationId(sourceId, FollowerIdDTO.class);
    }
}
