package org.ubwroteit.citizen.service;

import org.ubwroteit.citizen.model.CitizenEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CitizenService {
    CitizenEntity getCitizenById(UUID citizenId);

    CitizenEntity postCitizen(CitizenEntity citizenEntity);

    CitizenEntity saveCitizen(CitizenEntity citizenEntity);
    boolean isCitizenExist(UUID citizenId);
    void deleteCitizen(UUID citizenId);

    Map<UUID, String> getCitizenEmails(List<UUID> citizenIds);
}
