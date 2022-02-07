package org.ubwroteit.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubwroteit.idea.model.IdeaEntity;

import java.util.List;
import java.util.UUID;

public interface IdeaRepository extends JpaRepository<IdeaEntity, UUID> {

    int countByContenderId(UUID contenderId);
    List<IdeaEntity> findAllByContenderId(UUID contenderId);
    List<IdeaEntity> findAllByElectionId(UUID electionId);
}
