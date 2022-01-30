package org.ubwroteit.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ubwroteit.citizen.entity.Citizen;
import org.ubwroteit.citizen.repository.CitizenRepository;
import org.ubwroteit.common.exception.ResourceNotFoundException;

import java.util.UUID;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Override
    public Citizen getCitizenById(UUID uuid){
        return citizenRepository.findById(uuid)
                .orElseThrow(()-> new ResourceNotFoundException("Citizen does not exist with id "+uuid));
    }

    @Override
    public Citizen saveCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    @Override
    public boolean isCitizenExist(UUID citizenId){
        return citizenRepository.existsById(citizenId);
    }

    @Override
    public void deleteCitizen(UUID citizenId){
        citizenRepository.deleteById(citizenId);
    }


}
