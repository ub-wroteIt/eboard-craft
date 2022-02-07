package org.ubwroteit.follower.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.ubwroteit.follower.model.FollowerEntity;
import org.ubwroteit.follower.model.FollowerId;
import org.ubwroteit.follower.model.FollowerIdDTO;
import org.ubwroteit.follower.repository.FollowerRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    FollowerRepository followerRepository;

    @CacheEvict(cacheNames = "followers", key = "#followerEntity.getDestinationId")
    @Override
    public FollowerEntity saveFollower(FollowerEntity followerEntity) {
        //TODO create Valid annotation for Entities
        return followerRepository.save(followerEntity);
    }

    @CacheEvict(cacheNames = "followers", key = "#followerEntity.getDestinationId")
    @Override
    public FollowerEntity createFollower(FollowerEntity followerEntity) {
        FollowerId followerId = new FollowerId(followerEntity.getSourceId(), followerEntity.getDestinationId());
        if (!followerRepository.existsById(followerId)) {
            return followerRepository.save(followerEntity);
        }
        return followerEntity;
    }

    @CacheEvict(cacheNames = "followers", key = "#followerId.getDestinationId")
    @Override
    public void deleteFollowerById(FollowerId followerId) {
        followerRepository.deleteById(followerId);
    }

    @CacheEvict(cacheNames = "followers", key = "#followerEntity.getDestinationId")
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

    @Cacheable("followers")
    @Override
    public Set<UUID> getFollowersId(UUID destinationId) {
        List<FollowerIdDTO> followerIdDTOS = followerRepository.getFollowersIdByDestinationId(destinationId, FollowerIdDTO.class);
        return followerIdDTOS.stream().map(FollowerIdDTO::getSourceId).collect(Collectors.toSet());
    }
}
