package org.ubwroteit.follower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.follower.model.FollowerEntity;
import org.ubwroteit.follower.model.FollowerId;
import org.ubwroteit.follower.service.FollowerService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("follower")
public class FollowerController {

    @Autowired
    FollowerService followerService;

    @GetMapping("myFollowers/{sourceId}")
    public List<FollowerEntity> getMyFollower(@PathVariable UUID sourceId){
        return followerService.getFollowerBySourceId(sourceId);
    }

    @GetMapping("FollowedByMe/{destinationId}")
    public List<FollowerEntity> getFollowedByMe(@PathVariable UUID destinationId){
        return followerService.getFollowerByDestinationId(destinationId);
    }

    @PostMapping
    public FollowerEntity postFollower(@RequestBody FollowerEntity followerEntity){
        FollowerEntity existingFollowerEntity = followerService.getFollower(new FollowerId(followerEntity.getSourceId(), followerEntity.getDestinationId()));
        if(Objects.isNull(existingFollowerEntity)){
            return followerService.saveFollower(followerEntity);
        }
        return existingFollowerEntity;
    }

    @PutMapping
    public FollowerEntity updateFollower(@RequestBody FollowerEntity followerEntity){
        return followerService.saveFollower(followerEntity);
    }

}
