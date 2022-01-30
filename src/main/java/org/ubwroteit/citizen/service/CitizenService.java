package org.ubwroteit.citizen.service;

import org.ubwroteit.citizen.entity.Citizen;

import java.util.UUID;

public interface CitizenService {
    Citizen getCitizenById(UUID citizenId);
    Citizen saveCitizen(Citizen citizen);
    boolean isCitizenExist(UUID citizenId);
    void deleteCitizen(UUID citizenId);

}
