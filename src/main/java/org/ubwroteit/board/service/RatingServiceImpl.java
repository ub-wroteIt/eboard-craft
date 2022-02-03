package org.ubwroteit.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ubwroteit.board.model.RatingEntity;
import org.ubwroteit.board.model.RatingId;
import org.ubwroteit.board.repository.RatingRepository;
import org.ubwroteit.common.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public RatingEntity saveRating(RatingEntity ratingEntity) {
        return ratingRepository.save(ratingEntity);
    }

    @Override
    public RatingEntity getRating(RatingId ratingId) {
        return ratingRepository.findById(ratingId)
                .orElseThrow(()->new ResourceNotFoundException("No Ratings found for provided id"));
    }

    @Override
    public List<RatingEntity> getRatingsByIdea(UUID ideaId) {
        return ratingRepository.findByIdeaId(ideaId);
    }

    @Override
    public List<RatingEntity> getRatingsByCitizen(UUID citizenId) {
        return ratingRepository.findByCitizenId(citizenId);
    }

    @Override
    public List<RatingEntity> getRatingsByContender(UUID contenderId) {
        return ratingRepository.findByContenderId(contenderId);
    }
}
