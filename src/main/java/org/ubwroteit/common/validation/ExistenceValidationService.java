package org.ubwroteit.common.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ExistenceValidationService {
    @Autowired
    RestTemplate restTemplate;

    public boolean isExist(String url,UUID resourceId, String errorMessage){
        boolean exist = restTemplate.getForObject(url + resourceId, Boolean.class);
        if(!exist){
            throw new IllegalArgumentException(errorMessage);
        }
        return true;
    }
}
