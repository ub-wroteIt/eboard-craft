package org.ubwroteit.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ubwroteit.contender.model.Contender;
import org.ubwroteit.rating.model.RatingEntity;
import org.ubwroteit.rating.model.RatingId;
import org.ubwroteit.rating.repository.RatingRepository;
import org.ubwroteit.common.validation.DomainValidationService;
import org.ubwroteit.common.exception.ResourceNotFoundException;
import org.ubwroteit.common.model.FollowerMessage;
import org.ubwroteit.common.queue.Producer;
import org.ubwroteit.follower.model.FollowerStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{

    @Value("${follower.topic:followerTopic}")
    private String topicName;

    @Value("${rating.threshold:5}")
    private int RATING_THRESHOLD;

    @Autowired
    private RatingRepository ratingRepository;

    @Qualifier("producer/kafka")
    @Autowired
    private Producer<FollowerMessage> producer;

    @Qualifier("RatingValidator")
    @Autowired
    DomainValidationService<RatingEntity> validationService;

    @Override
    public RatingEntity saveRating(RatingEntity ratingEntity) {
        validationService.validate(ratingEntity);
        if(ratingEntity.getRating()> RATING_THRESHOLD){
            publishFollowerMessage(ratingEntity);
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

    /**
     * select contenderId, IdeaId, avg(rating)
     * from rating
     * group by ContenderId, ideaId;
     *
     * @param contenderList
     * @return
     */
    @Override
    public Map<UUID, Float> getAverageRating(List<Contender> contenderList) {
        return null;
    }

    private void publishFollowerMessage(RatingEntity ratingEntity) {
        long timeStamp = Timestamp.from(Instant.now()).getTime();
        FollowerMessage followerMessage = new FollowerMessage(ratingEntity.getCitizenId(), ratingEntity.getContenderId(), timeStamp , FollowerStatus.POSITIVE);
        producer.produceMessage(topicName, followerMessage);
    }

}
