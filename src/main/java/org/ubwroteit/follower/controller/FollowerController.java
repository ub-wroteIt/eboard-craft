package org.ubwroteit.follower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.follower.model.FollowerEntity;
import org.ubwroteit.follower.model.FollowerIdDTO;
import org.ubwroteit.follower.service.FollowerService;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping("allFollowers/{sourceId}")
    public Set<UUID> getFollowersId(@PathVariable UUID sourceId){
        List<FollowerIdDTO> followers = followerService.getFollowersId(sourceId);
        return followers.stream().map(FollowerIdDTO::getSourceId).collect(Collectors.toSet());
    }

    @PostMapping
    public FollowerEntity postFollower(@RequestBody FollowerEntity followerEntity){
        return followerService.createFollower(followerEntity);
    }

    @PutMapping
    public FollowerEntity updateFollower(@RequestBody FollowerEntity followerEntity){
        return followerService.saveFollower(followerEntity);
    }

}
