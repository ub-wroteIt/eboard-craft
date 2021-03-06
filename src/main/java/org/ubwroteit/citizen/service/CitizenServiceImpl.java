package org.ubwroteit.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ubwroteit.citizen.model.CitizenContactDTO;
import org.ubwroteit.citizen.model.CitizenEntity;
import org.ubwroteit.citizen.repository.CitizenRepository;
import org.ubwroteit.common.exception.ResourceExistException;
import org.ubwroteit.common.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Override
    public CitizenEntity getCitizenById(UUID uuid){
        return citizenRepository.findById(uuid)
                .orElseThrow(()-> new ResourceNotFoundException("Citizen does not exist with id "+uuid));
    }

    @Override
    public CitizenEntity postCitizen(CitizenEntity citizenEntity) {
        UUID citizenId = citizenEntity.getId();
        if (citizenId != null) {
            boolean citizenExist = isCitizenExist(citizenId);
            if (citizenExist) {
                throw new ResourceExistException("Citizen already Exist id=" + citizenId);
            }
        }
        return citizenRepository.save(citizenEntity);
    }

    @Override
    public CitizenEntity saveCitizen(CitizenEntity citizenEntity) {
        if (!isCitizenExist(citizenEntity.getId())) {
            throw new ResourceNotFoundException("Citizen Id provided for update does not exist");
        }
        return citizenRepository.save(citizenEntity);
    }

    @Override
    public boolean isCitizenExist(UUID citizenId){
        return citizenRepository.existsById(citizenId);
    }

    @Override
    public void deleteCitizen(UUID citizenId){
        citizenRepository.deleteById(citizenId);
    }

    @Override
    public Map<UUID, String> getCitizenEmails(List<UUID> citizenIds) {
        List<CitizenContactDTO> contactDTOS = citizenRepository.findAllCitizenContactDTOByIdIn(citizenIds, CitizenContactDTO.class);
        return contactDTOS.stream().collect(Collectors.toMap(CitizenContactDTO::getId, CitizenContactDTO::getEmailId));
    }


}
