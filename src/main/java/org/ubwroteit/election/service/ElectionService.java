package org.ubwroteit.election.service;

import org.ubwroteit.election.model.ElectionEntity;

import java.util.UUID;

public interface ElectionService {

    void deleteElection(UUID electionId);
    ElectionEntity saveElection(ElectionEntity electionEntity);
    ElectionEntity updateElection(ElectionEntity electionEntity);
    ElectionEntity getElection(UUID electionId);
}
