package org.ubwroteit.rating.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubwroteit.rating.model.RatingEntity;
import org.ubwroteit.rating.model.RatingId;
import org.ubwroteit.rating.service.RatingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rating")
@Tag(name = "Rating service", description = "the rating API to manage citizen ratings")
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
    public RatingEntity saveRating(@RequestBody RatingEntity ratingEntity) {
        RatingEntity savedRatingEntity = ratingService.saveRating(ratingEntity);
        return savedRatingEntity;
    }


}
