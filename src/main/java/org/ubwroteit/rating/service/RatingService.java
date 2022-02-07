package org.ubwroteit.rating.service;

import org.ubwroteit.rating.model.RatingEntity;
import org.ubwroteit.rating.model.RatingId;

import java.util.List;
import java.util.UUID;

public interface RatingService {

     RatingEntity saveRating(RatingEntity ratingEntity);
     RatingEntity getRating(RatingId ratingId);
     List<RatingEntity> getRatingsByIdea(UUID ideaId);
     List<RatingEntity> getRatingsByCitizen(UUID citizenId);
     List<RatingEntity> getRatingsByContender(UUID contenderId);
}
