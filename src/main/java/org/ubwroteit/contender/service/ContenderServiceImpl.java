package org.ubwroteit.contender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ubwroteit.common.exception.ResourceNotFoundException;
import org.ubwroteit.common.model.ElectionCategory;
import org.ubwroteit.contender.model.Contender;
import org.ubwroteit.contender.repository.ContenderRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ContenderServiceImpl implements ContenderService {

    @Autowired
    ContenderRepository contenderRepository;

    @Override
    public Contender saveContender(Contender contender) {
        return contenderRepository.save(contender);
    }

    @Override
    public Contender getContender(UUID contenderId) {
        return contenderRepository.findById(contenderId)
                .orElseThrow(() -> new ResourceNotFoundException("Citizen does not exist with id " + contenderId));
    }

    @Override
    public void deleteContender(UUID contenderId) {
         contenderRepository.deleteById(contenderId);
    }

    @Override
    public List<Contender> findAllContenders(UUID electionId, int areaId, ElectionCategory electionCategory) {
        return contenderRepository.findAllByElectionIdAndAreaIdAndCategory(electionId, areaId, electionCategory);
    }
}
