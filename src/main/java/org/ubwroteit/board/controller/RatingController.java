package org.ubwroteit.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.board.model.RatingEntity;
import org.ubwroteit.board.model.RatingId;
import org.ubwroteit.board.service.RatingService;
import org.ubwroteit.common.model.FollowerMessage;
import org.ubwroteit.follower.model.FollowerStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping("{ideaId}/{citizenId}/{contenderId}")
    public RatingEntity getRating(@PathVariable UUID ideaId, @PathVariable UUID citizenId, @PathVariable UUID contenderId){
        return ratingService.getRating(new RatingId(citizenId, ideaId, contenderId));
    }

    @GetMapping("citizen/{citizenId}")
    public List<RatingEntity> getRatingByCitizenId(@PathVariable UUID citizenId){
        return ratingService.getRatingsByCitizen(citizenId);
    }

    @GetMapping("idea/{ideaId}")
    public List<RatingEntity> getRatingByIdeaId(@PathVariable UUID ideaId){
        return ratingService.getRatingsByIdea(ideaId);
    }

    @GetMapping("contender/{contenderId}")
    public List<RatingEntity> getRatingByContenderId(@PathVariable UUID contenderId){
        return ratingService.getRatingsByContender(contenderId);
    }

    @PostMapping
    public RatingEntity saveRating(@RequestBody RatingEntity ratingEntity){
        if(ratingEntity.getRating()>5){
            //Citizen becomes a follower
            FollowerMessage followerMessage = new FollowerMessage(ratingEntity.getCitizenId(), ratingEntity.getContenderId(), LocalDateTime.now(), FollowerStatus.POSITIVE);
        }
        return ratingService.saveRating(ratingEntity);
    }


}
