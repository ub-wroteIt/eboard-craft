package org.ubwroteit.board.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ubwroteit.board.model.RatingEntity;
import org.ubwroteit.board.model.RatingId;
import org.ubwroteit.board.repository.RatingRepository;
import org.ubwroteit.common.exception.ResourceNotFoundException;
import org.ubwroteit.common.model.FollowerMessage;
import org.ubwroteit.common.queue.Producer;
import org.ubwroteit.follower.model.FollowerStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Qualifier("producer/kafka")
    @Autowired
    private Producer<FollowerMessage> producer;

    @Value("${follower.topic:followerTopic}")
    private String topicName;

    @Override
    //@Transactional("dstm")
    public RatingEntity saveRating(RatingEntity ratingEntity) {
        if(ratingEntity.getRating()>5){
            //Citizen becomes a follower
            long timeStamp = Timestamp.from(Instant.now()).getTime();
            FollowerMessage followerMessage = new FollowerMessage(ratingEntity.getCitizenId(), ratingEntity.getContenderId(), timeStamp , FollowerStatus.POSITIVE);
            producer.produceMessage(topicName, followerMessage);

        }
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
