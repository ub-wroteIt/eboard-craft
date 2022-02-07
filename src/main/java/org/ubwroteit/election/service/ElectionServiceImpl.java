package org.ubwroteit.election.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ubwroteit.election.model.ElectionEntity;
import org.ubwroteit.election.repository.ElectionRepository;

import java.util.UUID;

@Service
public class ElectionServiceImpl implements ElectionService{

    @Autowired
    ElectionRepository electionRepository;

    @Override
    public void deleteElection(UUID electionId) {
        electionRepository.deleteById(electionId);
    }

    @Override
    public ElectionEntity saveElection(ElectionEntity electionEntity) {
        return electionRepository.save(electionEntity);
    }

    @Override
    public ElectionEntity updateElection(ElectionEntity electionEntity) {
        return electionRepository.save(electionEntity);
    }

    @Override
    public ElectionEntity getElection(UUID electionId) {
        return electionRepository.getById(electionId);
    }

    @Override
    public Boolean isElectionIdExist(UUID electionId) {
        return electionRepository.existsById(electionId);
    }


}
