package org.ubwroteit.contender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ubwroteit.common.validation.DomainValidationService;
import org.ubwroteit.common.validation.ExistenceValidationService;
import org.ubwroteit.contender.model.Contender;

import java.util.UUID;

@Service("contenderValidation")
public class ContenderValidationServiceImpl implements DomainValidationService<Contender> {

    @Autowired
    ExistenceValidationService existenceValidationService;

    @Override
    public void validate(Contender contender) {
        UUID contenderId = contender.getId();
        existenceValidationService.isExist("http://localhost:8080/citizen/exist/",contenderId,"Invalid Contender Id" );
    }
}
