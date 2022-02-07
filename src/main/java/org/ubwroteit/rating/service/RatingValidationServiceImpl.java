package org.ubwroteit.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ubwroteit.rating.model.RatingEntity;
import org.ubwroteit.common.validation.DomainValidationService;
import org.ubwroteit.common.validation.ExistenceValidationService;

import java.util.UUID;

@Service("RatingValidator")
public class RatingValidationServiceImpl implements DomainValidationService<RatingEntity> {

    @Autowired
    private ExistenceValidationService validationService;

    // TODO hard coded values can be made configurable
    @Override
    public void validate(RatingEntity ratingEntity) {
        UUID electionId = ratingEntity.getElectionId();
        validateExistence("http://localhost:8080/election/exist/",electionId,"Invalid Election Id" );
        UUID contenderId = ratingEntity.getContenderId();
        validateExistence("http://localhost:8080/contender/exist/",contenderId,"Invalid Contender Id" );
        UUID citizenId = ratingEntity.getCitizenId();
        validateExistence("http://localhost:8080/citizen/exist/",citizenId,"Invalid Citizen Id" );
    }

    private void validateExistence( String url, UUID electionId, String errorMessage) {
        validationService.isExist(url, electionId, errorMessage);
    }

}
